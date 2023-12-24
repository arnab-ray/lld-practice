package org.example.battleship.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerChanceTarget {

    private final Player targetPlayer;
    private final Coordinate target;
}
