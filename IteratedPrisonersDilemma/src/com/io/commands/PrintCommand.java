package com.io.commands;

import com.core.contracts.SimulationData;
import com.exceptions.InvalidInputException;
import com.simulation.contracts.Generation;
import com.simulation.contracts.Simulation;
import com.utilities.Constants;
import com.utilities.Messages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrintCommand extends BaseCommand {


    public PrintCommand(SimulationData data, String[] arguments) {
        super(data, arguments);
    }

    @Override
    public String execute() throws IOException {
        String[] arguments = this.getArguments();
        StringBuilder consoleOutput = new StringBuilder();
        String simulationType = arguments[0];
        String simulationName = arguments[1];
        boolean printMode = false;
        if (arguments.length > 2 && arguments[2].toLowerCase().equals("verbose")) {
            printMode = true;
        }

        switch (simulationType.toLowerCase()) {
            case Constants.SIMULATION_TYPE_SIMULATION:
                consoleOutput.append(printSimulationData(this.getData().getSimulation(simulationName), printMode))
                        .append(System.lineSeparator());
                break;
            case Constants.SIMULATION_TYPE_TOURNAMENT:
                consoleOutput.append(printTournamentData(this.getData().getTournament(simulationName), printMode))
                        .append(System.lineSeparator());
                break;
            default:
                throw new InvalidInputException(simulationType);
        }

        return consoleOutput.toString();
    }

    private String printTournamentData(Generation tournament, boolean isVerboseMode) {
        String fileName = tournament.getName() + "TournamentResults.txt";
        try {
            File file = new File(Constants.OUTPUT_FOLDER_PATH + fileName);

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(String.format("%s TOURNAMENT RESULTS %s", tournament.getName().toUpperCase(), System.lineSeparator()));
            bw.write(tournament.print(isVerboseMode));
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format(Messages.SUCCESSFULLY_WROTE_RESULTS_TO_FILE, fileName);
    }

    private String printSimulationData(Simulation simulation, boolean isVerboseMode) {
        String fileName = simulation.getName() + "SimulationResults.txt";
        try {
            File file = new File(Constants.OUTPUT_FOLDER_PATH + fileName);

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(String.format("%s SIMULATION RESULTS %s", simulation.getName().toUpperCase(), System.lineSeparator()));
            for (int i = 0; i < simulation.getGenerationSize() - 1; i++) {
                bw.write(simulation.print(i, isVerboseMode));
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format(Messages.SUCCESSFULLY_WROTE_RESULTS_TO_FILE, fileName);
    }

}
