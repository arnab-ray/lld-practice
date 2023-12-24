package org.example.ridesharing.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.ridesharing.service.RideOfferService;
import org.example.ridesharing.strategy.DefaultRideSelectionStrategy;
import org.example.ridesharing.strategy.RideSelectionStrategy;

@Data
@AllArgsConstructor
public class User {
    private final String name;
    private final Vehicle vehicle;
    private final RideOfferService rideOfferService;
    private RideSelectionStrategy rideSelectionStrategy;

    public User(String name, Vehicle vehicle, RideOfferService rideOfferService) {
        this.name = name;
        this.vehicle = vehicle;
        this.rideOfferService = rideOfferService;
        this.rideSelectionStrategy = new DefaultRideSelectionStrategy(rideOfferService.getRideHistory(), rideOfferService);
    }

    public void bookRide(String destination, int numOfSeats) {
        rideSelectionStrategy.bookRide(this, destination, numOfSeats);
    }

    public int getRidesOfferedCount() {
        return rideOfferService.getRideHistory().getRidesOffered().get(this).size();
    }

    public int getRidesAvailedCount() {
        return rideSelectionStrategy.getRideAvailedCount(this);
    }
}
