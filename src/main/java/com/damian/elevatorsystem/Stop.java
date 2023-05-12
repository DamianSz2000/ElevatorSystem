package com.damian.elevatorsystem;

public class Stop {
    enum StopType {
        PICKUP, TARGET
    }

    int floor;
    StopType type;

    Stop(int floor, StopType type) {
        this.floor = floor;
        this.type = type;
    }
}