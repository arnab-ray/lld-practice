package org.example.battleship.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Player {
    private final Board board;
    private final int id;


    public boolean allShipsDestroyed() {
        return board.areAllShipsDestroyed();
    }

    public void takeHit(@NonNull Coordinate coordinate) {
        System.out.print("Hit player " + id + " ");
        board.hitLocation(coordinate);
    }

    public PlayerChanceTarget getTarget(List<Player> allPlayers, Coordinate target) {
        List<Player> opponents = new ArrayList<>();
        for (Player player : allPlayers) {
            if (player.getId() != this.getId()) {
                opponents.add(player);
            }
        }

        return new PlayerChanceTarget(opponents.get(0), target);
    }
}
