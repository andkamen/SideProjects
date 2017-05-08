package com.solver;

import com.dataStructures.Maze;

import java.io.IOException;

public interface ImageHandler {

    Maze parseImage(String name) throws IOException;

    void drawPath();
}
