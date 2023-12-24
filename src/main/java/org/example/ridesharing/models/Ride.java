package org.example.ridesharing.models;

import lombok.Getter;

import java.util.List;
import java.util.Random;

@Getter
public class Ride {
    private final int id;
    private final User user;
    private final Vehicle vehicle;
    private final String origin;
    private final List<String> dropLocations;
    private final String destination;
    private int availableSeats;

    public Ride(User user, String origin, String destination, List<String> dropLocations) {
        this.id = new Random().nextInt(100);
        this.user = user;
        this.vehicle = user.getVehicle();
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = user.getVehicle().getNumberOfSeats();
        this.dropLocations = dropLocations;
    }

    public synchronized void bookSeat(int numberOfSeats) {
        if (numberOfSeats <= availableSeats && numberOfSeats > 0 && numberOfSeats <= 2) {
            availableSeats -= numberOfSeats;
        } else {
            throw new RuntimeException("Invalid number of seats requested");
        }
    }
}
