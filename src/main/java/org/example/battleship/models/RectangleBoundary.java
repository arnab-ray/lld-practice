package org.example.battleship.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RectangleBoundary implements Boundary {
    private final Coordinate topLeft;
    private final Coordinate bottomRight;

    @Override
    public boolean contains(@NonNull Coordinate coordinate) {
        return topLeft.x <= coordinate.x && bottomRight.x >= coordinate.x &&
                topLeft.y >= coordinate.y && bottomRight.y <= coordinate.y;
    }

    @Override
    public List<Coordinate> getAllCoordinates() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = topLeft.x; i <= bottomRight.x; i++) {
            for (int j = bottomRight.y; j <= topLeft.y; j++) {
                coordinates.add(new Coordinate(i, j));
            }
        }

        return coordinates;
    }
}
