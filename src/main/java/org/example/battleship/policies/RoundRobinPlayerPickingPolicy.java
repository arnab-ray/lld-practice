package org.example.battleship.policies;

import org.example.battleship.models.Player;

import java.util.List;

public class RoundRobinPlayerPickingPolicy implements PlayerPickingPolicy {
    @Override
    public int getFirstPlayer(List<Player> allPlayers) {
        if (allPlayers.isEmpty())
            throw new RuntimeException("No players present");
        return 0;
    }

    @Override
    public int getNextPlayer(List<Player> allPlayers, int currentPlayerIndex) {
        return (currentPlayerIndex + 1) % allPlayers.size();
    }
}
