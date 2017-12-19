package com.dataStructures;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Position {
    private int row;
    private int col;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position key = (Position) o;
        return row == key.row && col == key.col;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + row;
        result = 31 * result + col;
        return result;
    }
}
