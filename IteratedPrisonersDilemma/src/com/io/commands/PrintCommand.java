package com.io.commands;

import com.core.contracts.SimulationData;
import com.exceptions.InvalidInputException;
import com.io.contracts.OutputWriter;
import com.simulation.contracts.Generation;
import com.simulation.contracts.Simulation;
import com.utilities.Constants;
import com.utilities.Messages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrintCommand extends BaseCommand {

    private OutputWriter consoleWriter;

    public PrintCommand(SimulationData data, OutputWriter consoleWriter, String[] arguments) {
        super(data, arguments);
        this.consoleWriter = consoleWriter;
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


    //TODO merge print data files?
    private String printTournamentData(Generation tournament, boolean isVerboseMode) {
        String fileName = tournament.getName() + "TournamentResults.txt";
        try {
            File file = new File(Constants.RESOURCES_FOLDER_PATH + fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
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
            File file = new File(Constants.RESOURCES_FOLDER_PATH + fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(simulation.print(isVerboseMode));
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format(Messages.SUCCESSFULLY_WROTE_RESULTS_TO_FILE, fileName);
    }

}
