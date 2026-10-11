package org.dicegame.calculations;

import org.apache.commons.math3.distribution.BinomialDistribution;

import static org.dicegame.GameConstants.DIE_SIDES;

public class DiceProbabilityUtils {

//    public static double probabilityExact(int m, int n, int r, int s) {
//        double p = (double) r / s;
//        BinomialDistribution binom = new BinomialDistribution(m, p);
//        return binom.probability(n);
//    }


    /**
     *
     * @param allDice total number of dice in game
     * @param atLeastAmount at least X amount of dice from all dice having a desirable side
     * @param isSpecialRound during normal rounds, 1 is a wildcard and total desired sides is 2. During special rounds desired sides are 1.
     * @return
     */
    public static double probabilityAtLeast(int allDice, int atLeastAmount, boolean isSpecialRound) {
        double p = isSpecialRound ? (double) 2 / DIE_SIDES : (double) 1 / DIE_SIDES;
        BinomialDistribution binom = new BinomialDistribution(allDice, p);
        return 1.0 - binom.cumulativeProbability(atLeastAmount - 1);
    }
}
