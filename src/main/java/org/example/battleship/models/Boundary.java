package org.example.battleship.models;

import lombok.NonNull;

import java.util.List;

public interface Boundary {
    boolean contains(@NonNull Coordinate coordinate);

    List<Coordinate> getAllCoordinates();
}
