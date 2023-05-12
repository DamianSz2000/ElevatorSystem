package com.damian.elevatorsystem;

import java.util.*;

public class ControlSystem {
    Building building;
    Queue<Request> requests;



    public ControlSystem(int elevatorCount) {
        this.building = new Building(elevatorCount);
        this.requests = new LinkedList<>();
    }

    public void addRequest(int pickupFloor, int targetFloor) {
        this.requests.add(new Request(pickupFloor, targetFloor));
    }
    public void addRandomRequests(int numRequests, int numFloors) {
        Random random = new Random();
        for (int i = 0; i < numRequests; i++) {
            int pickupFloor = random.nextInt(numFloors) + 1;
            int targetFloor;
            do {
                targetFloor = random.nextInt(numFloors) + 1;
            } while (targetFloor == pickupFloor);

            addRequest(pickupFloor, targetFloor);
        }
    }
    public boolean allRequestsProcessed() {
        List<Elevator> elevators = building.elevators;
        Boolean allIdle = elevators.stream().allMatch(elevator -> elevator.status == Elevator.Status.IDLE);
        Boolean allProcessed = requests.isEmpty();
        return allIdle && allProcessed;
    }

    public void startSimulation(int steps) {
        for (int i = 0; i < steps; i++) {
            Iterator<Request> iterator = requests.iterator();
            while (iterator.hasNext()) {
                Request request = iterator.next();
                Elevator elevator = building.requestElevator(request.pickupFloor);
                if (elevator != null) {
                    elevator.addStop(new Stop(request.pickupFloor, Stop.StopType.PICKUP));
                    elevator.addStop(new Stop(request.targetFloor, Stop.StopType.TARGET));
                    iterator.remove();
                }
            }

            for (Elevator elevator : building.elevators) {
                elevator.move();
            }
        }
    }

    public String getRequests() {
        StringBuilder sb = new StringBuilder();
        for (Request request : requests) {
            sb.append(request.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    public String getSystemStatus() {
        StringBuilder sb = new StringBuilder();
        for (Elevator elevator : building.elevators) {
            sb.append("Elevator ID: ").append(elevator.id)
                    .append(", Current Floor: ").append(elevator.currentFloor)
                    .append(", Target Floor: ").append(elevator.stops.isEmpty() ? "N/A" : elevator.stops.peek().floor)
                    .append(", Status: ").append(elevator.status)
                    .append("\n");
        }
        return sb.toString();
    }
}

