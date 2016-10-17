package com.simulation.models.strategies;

import com.simulation.contracts.Strategy;

public abstract class BaseStrategy implements Strategy {

    //TODO without unique strategy names this class might be obsolete?? and strats can just implement Strategy themselves

//    private String name;

    public BaseStrategy() {
        //this.setName(name);
    }

//    @Override
//    public String getName() {
//        return this.name;
//    }

//    private void setName(String name) {
//        this.name = name;
//    }
}
