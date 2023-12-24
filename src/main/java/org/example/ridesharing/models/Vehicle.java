package org.example.ridesharing.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private final String vehicleNumber;
    private final VehicleType type;
    private final int numberOfSeats;
}
