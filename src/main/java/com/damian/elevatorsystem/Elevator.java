package com.damian.elevatorsystem;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator {
        int id;
        int currentFloor;
        Queue<Stop> stops;
        Elevator.Status status;

enum Status {
    IDLE, MOVING_UP, MOVING_DOWN, UNLOADING, LOADING
}

    Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.stops = new LinkedList<>();
        this.status = Status.IDLE;
    }

    void move() {
        if (!stops.isEmpty()) {
            Stop stop = stops.peek();
            if (this.currentFloor < stop.floor) {
                this.currentFloor++;
                this.status = Status.MOVING_UP;
            } else if (this.currentFloor > stop.floor) {
                this.currentFloor--;
                this.status = Status.MOVING_DOWN;
            } else if (stop.type == Stop.StopType.PICKUP && this.status != Status.LOADING) {
                this.status = Status.LOADING;
            } else if (stop.type == Stop.StopType.TARGET && this.status != Status.UNLOADING) {
                this.status = Status.UNLOADING;
            } else {
                stops.poll();
                if (!stops.isEmpty()) {
                    stop = stops.peek();
                    this.status = this.currentFloor < stop.floor ? Status.MOVING_UP : Status.MOVING_DOWN;
                } else {
                    this.status = Status.IDLE;
                }
            }
        } else {
            this.status = Status.IDLE;
        }
    }

    void addStop(Stop stop) {
        this.stops.add(stop);
    }
}

