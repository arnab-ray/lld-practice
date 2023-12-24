package org.example.battleship.policies;

import org.example.battleship.models.Player;

import java.util.ArrayList;
import java.util.List;

public class WinnerPolicyImpl implements WinnerPolicy {
    @Override
    public Player getWinner(List<Player> players) {
        List<Player> alivePlayers = new ArrayList<>();
        for (Player player : players) {
            if (!player.allShipsDestroyed()) {
                alivePlayers.add(player);
            }
        }

        if (alivePlayers.size() == 1) {
            return alivePlayers.get(0);
        } else {
            return null;
        }
    }
}
