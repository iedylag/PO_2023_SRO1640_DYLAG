package agh.ics.oop;

import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        ConsoleMapDisplay display = new ConsoleMapDisplay();
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Simulation> simulations = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                WorldMap grassField = new GrassField(10);
                grassField.subscribe(display);
                grassField.subscribe((worldMap, message) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.now();
                    System.out.println(formatter.format(dateTime) + " " + message);
                });
                simulations.add(new Simulation(directions, positions, grassField));
            }

            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runAsyncInThreadPool();
            engine.awaitSimulationsEnd();
        } catch (IllegalArgumentException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Stop");
    }
}