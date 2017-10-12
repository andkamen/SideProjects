package com.solver;

import com.algorithms.Algorithm;
import com.algorithms.BreadthFirst;
import com.algorithms.DepthFirst;
import com.algorithms.Dijkstra;

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
            default:
        }

        return algorithm;
    }
}
