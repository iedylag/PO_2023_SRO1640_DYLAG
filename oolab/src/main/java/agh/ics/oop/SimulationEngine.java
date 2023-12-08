package agh.ics.oop;

import java.util.List;

public class SimulationEngine {

    private final List<Simulation> simulations;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            thread.start();
        }
    }
}
