package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {

    private final List<Simulation> simulations;

    List<Thread> simulationTreads = new ArrayList<>();

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
            simulationTreads.add(thread);
            thread.start();
        }
    }

    public void awaitSimulationsEnd() {
        for (Thread thread : simulationTreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
