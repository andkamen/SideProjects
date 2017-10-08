package com.dataStructures;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Position {
    private int r;
    private int c;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position key = (Position) o;
        return r == key.r && c == key.c;
    }

    @Override
    public int hashCode() {
        int result = r;
        result = 31 * result + c;
        return result;
    }
}
