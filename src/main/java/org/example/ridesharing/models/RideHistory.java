package org.example.ridesharing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@AllArgsConstructor
public class RideHistory {
    private final ConcurrentHashMap<User, List<Ride>> ridesAvailed;
    private final ConcurrentHashMap<User, List<Ride>> ridesOffered;
}
