package org.example.battleship.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Board {
    private final List<BattleShip> battleShips;
    private final Boundary boundary;
    private final List<Coordinate> hitCoordinates;

    public Board(Boundary boundary) {
        this.boundary = boundary;
        this.battleShips = new ArrayList<>();
        this.hitCoordinates = new ArrayList<>();
    }

    public void addShip(BattleShip battleShip) {
        this.battleShips.add(battleShip);
    }

    public void hitLocation(@NonNull Coordinate coordinate) {
        if (!boundary.contains(coordinate)) {
            System.out.println("Missile fired at (" + coordinate.x + ", " + coordinate.y + "). Miss");
        } else {
            hitCoordinates.add(coordinate);
            System.out.println("Missile fired at (" + coordinate.x + ", " + coordinate.y + "). Hit");
        }
    }

    public boolean areAllShipsDestroyed() {
        for (BattleShip ship : battleShips) {
            if (!ship.isDestroyed(hitCoordinates))
                return false;
        }

        return true;
    }

    public List<Coordinate> allHitLocations() {
        List<Coordinate> result = new ArrayList<>();
        for (Coordinate coordinate : hitCoordinates) {
            for (BattleShip ship : battleShips) {
                if (ship.containsCoordinate(coordinate)) {
                    result.add(coordinate);
                    break;
                }
            }
        }

        return result;
    }

    public List<Coordinate> missLocations() {
        final List<Coordinate> result = new ArrayList<>();

        for (Coordinate coordinate: hitCoordinates) {
            boolean doesBelongToShip = false;
            for (BattleShip ship: battleShips) {
                if (ship.containsCoordinate(coordinate)) {
                    doesBelongToShip = true;
                    break;
                }
            }
            if (!doesBelongToShip) {
                result.add(coordinate);
            }
        }

        return result;
    }
}
