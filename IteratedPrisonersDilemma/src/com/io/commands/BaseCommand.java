package com.io.commands;

import com.io.commands.contracts.Command;
import com.core.contracts.SimulationData;

public abstract class BaseCommand implements Command {

    private SimulationData data;

    public BaseCommand(SimulationData data) {
        this.setData(data);
    }

    protected SimulationData getData() {
        return this.data;
    }

    protected void setData(SimulationData data) {
        this.data = data;
    }

    @Override
    public abstract String execute();
}
