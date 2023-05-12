package com.damian.elevatorsystem;

public class Request {
    int pickupFloor;
    int targetFloor;

    Request(int pickupFloor, int targetFloor) {
        this.pickupFloor = pickupFloor;
        this.targetFloor = targetFloor;
    }
    @Override
    public String toString() {
        return "Request: Pickup floor = " + pickupFloor + ", Target floor = " + targetFloor;
    }
}
