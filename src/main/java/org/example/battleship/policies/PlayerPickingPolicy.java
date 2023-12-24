package org.example.battleship.policies;

import org.example.battleship.models.Player;

import java.util.List;

public interface PlayerPickingPolicy {

    int getFirstPlayer(List<Player> allPlayers);

    int getNextPlayer(List<Player> allPlayers, int currentPlayerIndex);
}
