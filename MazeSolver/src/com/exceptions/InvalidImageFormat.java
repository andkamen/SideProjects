package com.exceptions;

public class InvalidImageFormat extends RuntimeException {

    private static final String IMAGE_USES_INVALID_COLORS =
            "The image uses invalid colors. Only white and black are allowed";

    public InvalidImageFormat() {
        super(IMAGE_USES_INVALID_COLORS);
    }
}