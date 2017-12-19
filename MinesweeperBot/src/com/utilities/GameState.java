package com.utilities;

public enum GameState {

    GAME_OVER(false),
    WIN(false),
    IN_PROGRESS(true);

    private boolean shouldContinue;

    GameState(boolean shouldContinue) {
        this.shouldContinue = shouldContinue;
    }

    public boolean shouldContinue() {
        return shouldContinue;
    }

    /*
        Top Left Pixel has coordinates row 6 col 6
        Bottom Right Pixel has coordinates row 7 col 7
     */
    public static GameState parseState(int topLeft, int bottomRight) {
        GameState state = null;
        if (topLeft == Constants.COLOUR_YELLOW && bottomRight == Constants.COLOUR_BLACK) {
            state = IN_PROGRESS;
        }
        if (topLeft == Constants.COLOUR_YELLOW && bottomRight == Constants.COLOUR_YELLOW) {
            state = GAME_OVER;
        }
        if (topLeft == Constants.COLOUR_BLACK && bottomRight == Constants.COLOUR_BLACK) {
            state = WIN;
        }
        return state;
    }
}