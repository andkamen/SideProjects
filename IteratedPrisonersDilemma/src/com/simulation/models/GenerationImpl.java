package com.simulation.models;

import com.simulation.contracts.Strategy;
import com.simulation.contracts.Generation;
import com.utilities.Constants;
import com.utilities.Messages;

import java.util.*;

public class GenerationImpl implements Generation {

    private String name;
    private List<Strategy> strategies;
    private Map<String, Integer> strategyScores;
    private Map<String, Integer> strategyCount;
    private int roundCount;
    private Random rand;

    /**
     * Creates a generation that can store various strategies and test them vs one another
     */
    public GenerationImpl() {
        this.strategies = new ArrayList<>();
        this.strategyScores = new HashMap<>();
        this.strategyCount = new HashMap<>();
        this.rand = new Random();
        this.name = null;
        this.setNewRoundCount();
    }

    public GenerationImpl(String name) {
        this.strategies = new ArrayList<>();
        this.strategyScores = new HashMap<>();
        this.strategyCount = new HashMap<>();
        this.rand = new Random();
        this.name = name;

        this.setNewRoundCount();
    }

    private void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public Map<String, Integer> getStrategyScores() {
        return strategyScores;
    }

    public int getTotalStrategyCount() {
        return this.strategies.size();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String addStrategy(Strategy strategy) {
        //add strategy to list
        this.strategies.add(strategy);

        //increment strategy Type counter and strategy Type total score counter
        if (!strategyCount.containsKey(strategy.getClass().getSimpleName())) {
            this.strategyCount.put(strategy.getClass().getSimpleName(), 0);
            this.strategyScores.put(strategy.getClass().getSimpleName(), 0);
        }
        this.strategyCount.put(strategy.getClass().getSimpleName(), this.strategyCount.get(strategy.getClass().getSimpleName()) + 1);

        return String.format(Messages.SUCCESSFULLY_REGISTERED_STRATEGY, strategy.getClass().getSimpleName());
    }

    @Override
    public String removeStrategy(String simpleName) {
        boolean foundMatch = false;
        Strategy strategyToRemove = null;

        for (Strategy strategy : this.strategies) {
            if (strategy.getClass().getSimpleName().equals(simpleName)) {
                foundMatch = true;
                strategyToRemove = strategy;
            }
        }
        //remove strategy from list and decrement counters. If 0 strategies of given type are left, remove them from dictionaries
        if (foundMatch) {
            this.strategies.remove(strategyToRemove);
            this.strategyCount.put(simpleName, this.strategyCount.get(strategyToRemove.getClass().getSimpleName()) - 1);
            int count = this.strategyCount.get(simpleName);
            if (this.strategyCount.get(simpleName) == 0) {
                this.strategyCount.remove(simpleName);
                this.strategyScores.remove(simpleName);
            }

            return String.format(Messages.SUCCESSFULLY_REMOVED_STRATEGY, simpleName);
        }

        return String.format(Messages.ITEM_NOT_REGISTERED, simpleName);
    }

    @Override
    public void playOut() {

        //TODO exception if <2 strats registered
        Strategy stratA = null;
        Strategy stratB = null;
        int tempValue;
        int[] matchUpResult;

        for (int i = 0; i < this.strategies.size(); i++) {
            stratA = this.strategies.get(i);
            for (int j = 0; j < this.strategies.size(); j++) {
                if (j <= i) {
                    continue;
                }
                stratB = this.strategies.get(j);

                matchUpResult = matchUp(stratA, stratB);
                tempValue = this.strategyScores.get(stratA.getClass().getSimpleName()) + matchUpResult[0];
                this.strategyScores.put(stratA.getClass().getSimpleName(), tempValue);

                tempValue = this.strategyScores.get(stratB.getClass().getSimpleName()) + matchUpResult[1];
                this.strategyScores.put(stratB.getClass().getSimpleName(), tempValue);
            }
        }
    }

    private int[] matchUp(Strategy stratA, Strategy stratB) {
        boolean stratAMove;
        boolean stratBMove;
        int[] roundResult;
        int[] matchUpResult = new int[2];

        for (int i = 0; i < this.roundCount; i++) {
            stratAMove = stratA.makeMove();
            stratBMove = stratB.makeMove();

            stratA.updateOpponentMove(stratBMove);
            stratB.updateOpponentMove(stratAMove);

            roundResult = determineRoundResult(stratAMove, stratBMove);
            matchUpResult[0] += roundResult[0];
            matchUpResult[1] += roundResult[1];
        }
        return matchUpResult;
    }

    private int[] determineRoundResult(boolean stratAMove, boolean stratBMove) {
        int[] roundResult = new int[2];

        if (stratAMove && stratBMove) {
            //Both cooperate
            roundResult[0] = Constants.COOPERATE_COOPERATE_SCORE;
            roundResult[1] = Constants.COOPERATE_COOPERATE_SCORE;
        } else if (!stratAMove && !stratBMove) {
            //both defect
            roundResult[0] = Constants.DEFECT_DEFECT_SCORE;
            roundResult[1] = Constants.DEFECT_DEFECT_SCORE;
        } else if (!stratAMove) {
            //A defects B cooperates
            roundResult[0] = Constants.DEFECT_COOPERATE_SCORE;
            roundResult[1] = Constants.COOPERATE_DEFECT_SCORE;
        } else {
            //B defects A cooperates
            roundResult[0] = Constants.COOPERATE_DEFECT_SCORE;
            roundResult[1] = Constants.DEFECT_COOPERATE_SCORE;
        }
        return roundResult;
    }

    private void setNewRoundCount() {
        int newRoundCount = this.rand.nextInt((Constants.MAX_GENERATION_ROUND_COUNT - Constants.MIN_GENERATION_ROUND_COUNT) + 1)
                + Constants.MIN_GENERATION_ROUND_COUNT;
        this.setRoundCount(newRoundCount);
    }
}
