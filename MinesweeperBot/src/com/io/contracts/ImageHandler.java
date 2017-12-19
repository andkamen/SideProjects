package com.io.contracts;

import com.dataStructures.Minefield;
import com.dataStructures.Position;
import com.utilities.GameState;

public interface ImageHandler {

    Position findCorner();

    GameState updateGameState(Minefield minefield) throws InterruptedException;

    void updateMinefield(Minefield minefield) throws InterruptedException;
}
