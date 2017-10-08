package com.utilities;

/**
 * Node neighbour directions.
 */
public enum Directions {

    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    private int direction;

    Directions(int direction) {
        this.direction = direction;
    }

    public int getValue() {
        return direction;
    }

    private Directions valueOf(int direction) {
        switch (direction) {
            case 0:
                return UP;
            case 1:
                return RIGHT;
            case 2:
                return DOWN;
            case 3:
                return LEFT;
            default:
                throw new IllegalArgumentException("Not a valid direction: " + direction);
        }
    }
}
