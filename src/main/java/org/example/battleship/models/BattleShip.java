package org.example.battleship.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
public class BattleShip {
    private final String name;
    private final Boundary boundary;

    public boolean containsCoordinate(@NonNull Coordinate coordinate) {
        return this.boundary.contains(coordinate);
    }

    public boolean isDestroyed(@NonNull List<Coordinate> hitCoordinates) {
        List<Coordinate> coordinates = boundary.getAllCoordinates();
        for (Coordinate coordinate : coordinates) {
            if (hitCoordinates.contains(coordinate)) {
                return true;
            }
        }

        return false;
    }
}
