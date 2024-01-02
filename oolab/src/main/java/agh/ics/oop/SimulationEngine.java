package agh.ics.oop;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> simulationTreads = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    // private final BooleanProperty stopped = new SimpleBooleanProperty(true);

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

    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread thread : simulationTreads) {
            thread.join();
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void runAsyncInThreadPool() {
        for (Simulation simulation : simulations) {
            executorService.submit(simulation);
        }
    }

   /* public boolean isStopped() {
        return stopped.get();
    }

    public BooleanProperty stoppedProperty() {
        return stopped;
    }

    public synchronized void stopSimulation() {
        executorService.shutdown();
        stopped.set(true);
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
*/
    public synchronized void startSimulation() {
        runAsyncInThreadPool();

        /*if (!executorService.isShutdown()) {
            executorService.shutdownNow();  // Zamyka poprzedni executor, jeśli jest aktywny
        }
        if (isStopped()) {
            stopped.set(false);
            notifyAll();
        }*/
    }
}
