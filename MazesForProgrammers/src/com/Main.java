package com;

public class Main {

    public static void main(String[] args) {
        // write your code here


        Grid grid = new Grid(4, 4);
        System.out.println(grid.size());
        grid.getGrid()[1][1].link(grid.getGrid()[0][1]);
        grid.getGrid()[1][2].link(grid.getGrid()[1][3]);
        System.out.println(grid.toString());
    }
}
