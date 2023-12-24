package org.example.battleship;

import org.example.battleship.models.BattleShip;
import org.example.battleship.models.Board;
import org.example.battleship.models.Boundary;
import org.example.battleship.models.Coordinate;
import org.example.battleship.models.Player;
import org.example.battleship.models.RectangleBoundary;
import org.example.battleship.policies.RoundRobinPlayerPickingPolicy;
import org.example.battleship.policies.WinnerPolicyImpl;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private static List<Player> initGame(int N) {
        if (N % 2 != 0) throw new RuntimeException("Please input even number");
        Board boardA = new Board(new RectangleBoundary(new Coordinate(N / 2 - 1, 0), new Coordinate(N - 1, 0)));
        Player playerA = new Player(boardA, 0);

        Board boardB = new Board(new RectangleBoundary(new Coordinate(N - 1, 0), new Coordinate(N - 1, N / 2)));
        Player playerB = new Player(boardB, 1);

        List<Player> players = new ArrayList<>();
        players.add(playerA);
        players.add(playerB);
        return players;
    }

    private static void addShip(String name, Player player, Boundary boundary) {
        player.getBoard().addShip(new BattleShip(player.getId() + "-" + name, boundary));
    }

    private static void startGame(List<Player> players, int N) {
        new GameLoop(new RoundRobinPlayerPickingPolicy(), new WinnerPolicyImpl(), players, N).start();
    }

    private static void viewBoard(List<Player> players) {
        for (Player player : players) {
            Board board = player.getBoard();
            List<Coordinate> coordinates = player.getBoard().getBoundary().getAllCoordinates();
            for (Coordinate coordinate : coordinates) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int N = 4;
        List<Player> players = initGame(N);
        addShip("SH1", players.get(0), new RectangleBoundary(new Coordinate(0, 0), new Coordinate(0, 1)));
        addShip("SH2", players.get(0), new RectangleBoundary(new Coordinate(1, 3), new Coordinate(0, 3)));

        addShip("SH3", players.get(1), new RectangleBoundary(new Coordinate(0, 3), new Coordinate(0, 2)));
        addShip("SH4", players.get(1), new RectangleBoundary(new Coordinate(2, 3), new Coordinate(2, 2)));

        startGame(players, N);
    }
}
