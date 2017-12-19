package com.utilities;

public enum CellState {

    EMPTY(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    BOMB(null),
    UNOPENED(null);

    private final Integer mineCount;

    CellState(Integer mineCount) {
        this.mineCount = mineCount;
    }

    public int getMineCount() {
        return mineCount;
    }

    /*
        Corner Pixel has coordinates row 1 col 1
        Bottom Pixel has coordinates row 11 col 9
     */
    public static CellState parseState(int bottomPixel, int cornerPixel) {
        switch (bottomPixel) {
            case Constants.COLOUR_ONE:
                return ONE;
            case Constants.COLOUR_TWO:
                return TWO;
            case Constants.COLOUR_THREE:
                return THREE;
            case Constants.COLOUR_FOUR:
                return FOUR;
            case Constants.COLOUR_FIVE:
                return FIVE;
            case Constants.COLOUR_SIX:
                return SIX;
            case Constants.COLOUR_EIGHT:
                return EIGHT;
            default:
                if (bottomPixel == Constants.COLOUR_BLACK && cornerPixel == Constants.COLOUR_WHITE) {
                    return BOMB;
                }
                if (bottomPixel == Constants.COLOUR_SEVEN && cornerPixel == Constants.COLOUR_LIGHT_GREY) {
                    return SEVEN;
                }
                if (bottomPixel == Constants.COLOUR_LIGHT_GREY && cornerPixel == Constants.COLOUR_WHITE) {
                    return UNOPENED;
                }
                if (bottomPixel == Constants.COLOUR_LIGHT_GREY && cornerPixel == Constants.COLOUR_LIGHT_GREY) {
                    return EMPTY;
                }
                throw new IllegalArgumentException(String.format("Selected colours (bottomPixel : %s and cornerPixel: %s) are not for a valid cell state.", bottomPixel, cornerPixel));
        }
    }
}
