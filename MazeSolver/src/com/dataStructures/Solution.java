package com.dataStructures;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Queue;

@Data
@AllArgsConstructor
public class Solution {
    private Queue<Node> path;
    private int pathLength;
    private int nodesExplored;
    private boolean completed;
}
