package org.example.ridesharing.strategy;

import org.example.ridesharing.models.User;

public interface RideSelectionStrategy {
    void bookRide(User user, String destination, int numOfSeats);

    int getRideAvailedCount(User user);
}
