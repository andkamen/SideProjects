package com.solver;

import com.dataStructures.Maze;
import com.dataStructures.Node;

import java.io.IOException;
import java.util.Queue;

public interface ImageHandler {

    Maze parseImage(String name) throws IOException;

    void drawPath(Queue<Node> path, String name) throws IOException;
}
