package org.example.ridesharing.strategy;

import lombok.AllArgsConstructor;
import org.example.ridesharing.models.Ride;
import org.example.ridesharing.models.RideHistory;
import org.example.ridesharing.models.User;
import org.example.ridesharing.service.RideOfferService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DefaultRideSelectionStrategy implements RideSelectionStrategy {
    private final RideHistory rideHistory;
    private final RideOfferService rideOfferService;

    @Override
    public synchronized void bookRide(User user, String destination, int numOfSeats) {
        List<Ride> rideList = rideOfferService.getAvailableRides();
        List<Ride> matchingRides = new ArrayList<>();
        for (Ride ride : rideList) {
            boolean matchesDestination = ride.getDestination().equals(destination)
                    || ride.getDropLocations().contains(destination);
            if (matchesDestination && ride.getAvailableSeats() >= numOfSeats) {
                matchingRides.add(ride);
            }
        }

        Ride ride = matchingRides.get(0);
        ride.bookSeat(numOfSeats);
        List<Ride> ridesAvailed = rideHistory.getRidesAvailed().getOrDefault(ride.getUser(), new ArrayList<>());
        ridesAvailed.add(ride);
        rideHistory.getRidesAvailed().put(user, ridesAvailed);
    }

    @Override
    public int getRideAvailedCount(User user) {
        return rideHistory.getRidesAvailed().size();
    }
}
