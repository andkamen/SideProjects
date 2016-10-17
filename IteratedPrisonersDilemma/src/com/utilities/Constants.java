package com.utilities;

public class Constants {

    public static final int MAX_GENERATION_ROUND_COUNT = 210;
    public static final int MIN_GENERATION_ROUND_COUNT = 190;

    //Round Points depending on outcome. From A's perspective. A vs B . points awarded to A
    public static final int COOPERATE_COOPERATE_SCORE = 3;
    public static final int COOPERATE_DEFECT_SCORE = 0;
    public static final int DEFECT_COOPERATE_SCORE = 5;
    public static final int DEFECT_DEFECT_SCORE = 1;


    public static final String INPUT_SPLIT_DELIMITER = " ";
    public static final String STRATEGY_SPLIT_DELIMITER = "\\|"; //Name_Count|OtherName_OtherCount| ...
    public static final String STRATEGY_COUNT_SPLIT_DELIMITER = "_";
    public static final String INPUT_TERMINATING_COMMAND = "quitsim";

    //TODO enums?
    public static final String SIMULATION_TYPE_SIMULATION = "sim";
    public static final String SIMULATION_TYPE_TOURNAMENT = "tournament";

    public static final String FILL_MODE_FILE = "file";
    public static final String FILL_MODE_MANUAL = "manual";




}
