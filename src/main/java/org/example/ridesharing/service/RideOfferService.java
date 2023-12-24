package org.example.ridesharing.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.ridesharing.models.Ride;
import org.example.ridesharing.models.RideHistory;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class RideOfferService {
    private final List<Ride> rides;
    private final RideHistory rideHistory;

    public synchronized List<Ride> getAvailableRides() {
        List<Ride> availableRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.getAvailableSeats() > 0) {
                availableRides.add(ride);
            }
        }

        return availableRides;
    }

    public void addRide(Ride ride) {
        rides.add(ride);
        List<Ride> ridesOffered = rideHistory.getRidesOffered().getOrDefault(ride.getUser(), new ArrayList<>());
        ridesOffered.add(ride);
        rideHistory.getRidesOffered().put(ride.getUser(), ridesOffered);
    }
}
