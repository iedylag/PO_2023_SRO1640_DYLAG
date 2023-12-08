package agh.ics.oop;

import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

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

            for (int i = 0; i < 1000; i++) {
                WorldMap grassField = new GrassField(10);
                grassField.subscribe(display);
                simulations.add(new Simulation(directions, positions, grassField));
            }

            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runAsync();
            engine.awaitSimulationsEnd();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println("Stop");
    }
}