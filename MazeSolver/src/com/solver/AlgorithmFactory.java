package com.solver;

import com.algorithms.Algorithm;
import com.algorithms.BreadthFirst;
import com.algorithms.DepthFirst;

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
            default:

        }


        return algorithm;
    }

}
