package org.example.battleship.policies;

import org.example.battleship.models.Player;

import java.util.List;

public interface WinnerPolicy {
    Player getWinner(List<Player> players);
}
