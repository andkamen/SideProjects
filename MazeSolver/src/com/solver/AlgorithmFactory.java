package com.solver;

import com.algorithms.*;

public class AlgorithmFactory {

    public Algorithm getAlgorithm(String name) {
        Algorithm algorithm = null;

        switch (name) {
            case "BFS":
                algorithm = new BreadthFirst();
                break;
            case "DFS":
                algorithm = new DepthFirst();
                break;
            case "Dijkstra":
                algorithm = new Dijkstra();
                break;
            case "AStar":
                algorithm = new AStar();
                break;
            default:
        }

        return algorithm;
    }
}
