package org.example.battleship;

import lombok.AllArgsConstructor;
import org.example.battleship.models.Coordinate;
import org.example.battleship.models.Player;
import org.example.battleship.models.PlayerChanceTarget;
import org.example.battleship.policies.PlayerPickingPolicy;
import org.example.battleship.policies.WinnerPolicy;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class GameLoop {
    private final PlayerPickingPolicy playerPickingPolicy;
    private final WinnerPolicy winnerPolicy;
    private final List<Player> players;
    private final int N;

    public void start() {
        int currentPlayerIndex = playerPickingPolicy.getFirstPlayer(players);
        System.out.println("Starting game");
        while (true) {
            Player player = players.get(currentPlayerIndex);
            Random random = new Random();
            PlayerChanceTarget playerChanceTarget = player.getTarget(players, new Coordinate(random.nextInt(N), random.nextInt(N)));
            playerChanceTarget.getTargetPlayer().takeHit(playerChanceTarget.getTarget());

            Player winner = winnerPolicy.getWinner(players);
            if (winner != null) {
                System.out.println(winner.getId() + " is the winner!");
                break;
            }

            currentPlayerIndex = playerPickingPolicy.getNextPlayer(this.players, currentPlayerIndex);
        }
    }
}
