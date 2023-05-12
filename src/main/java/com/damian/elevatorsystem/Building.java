package com.damian.elevatorsystem;

import java.util.ArrayList;
import java.util.List;

public class Building {
    List<Elevator> elevators;

    Building(int elevatorCount) {
        this.elevators = new ArrayList<>();
        for (int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator(i));
        }
    }

    Elevator requestElevator(int floor) {
        Elevator bestElevator = null;
        for (Elevator elevator : elevators) {
            if (elevator.status == Elevator.Status.IDLE) {
                bestElevator = elevator;
                break;
            } else if ((elevator.status == Elevator.Status.MOVING_UP && floor >= elevator.currentFloor) ||
                    (elevator.status == Elevator.Status.MOVING_DOWN && floor <= elevator.currentFloor)) {
                if (bestElevator == null || Math.abs(floor - elevator.currentFloor) < Math.abs(floor - bestElevator.currentFloor)) {
                    bestElevator = elevator;
                }
            }
        }
        if (bestElevator != null) {
            bestElevator.status = bestElevator.currentFloor < floor ? Elevator.Status.MOVING_UP : Elevator.Status.MOVING_DOWN;
        }
        return bestElevator;
    }

}