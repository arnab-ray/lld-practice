package org.example.ridesharing;

import org.example.ridesharing.models.Ride;
import org.example.ridesharing.models.RideHistory;
import org.example.ridesharing.models.User;
import org.example.ridesharing.models.Vehicle;
import org.example.ridesharing.models.VehicleType;
import org.example.ridesharing.service.RideOfferService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Drive {

    public static void main(String[] args) {
        RideHistory rideHistory = new RideHistory(new ConcurrentHashMap<>(), new ConcurrentHashMap<>());
        RideOfferService rideOfferService = new RideOfferService(new ArrayList<>(), rideHistory);

        User user = new User("Alpha", new Vehicle("KA01", VehicleType.SEDAN, 3), rideOfferService);
        User user1 = new User("Beta", null, rideOfferService);

        List<String> dropPoints = new ArrayList<>();
        dropPoints.add("b");
        dropPoints.add("c");
        rideOfferService.addRide(new Ride(user, "a", "d", dropPoints));

        dropPoints = new ArrayList<>();
        dropPoints.add("e");
        dropPoints.add("f");
        rideOfferService.addRide(new Ride(user, "a", "g", dropPoints));

        user1.bookRide("c", 2);

        System.out.println("Rides offered by " + user.getName() + ": " + user.getRidesOfferedCount());
        System.out.println("Rides availed by " + user1.getName() + ": " + user1.getRidesAvailedCount());
    }
}
