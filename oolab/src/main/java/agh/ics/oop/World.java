package agh.ics.oop;

import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        WorldMap map = new GrassField(10);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        ConsoleMapDisplay display = new ConsoleMapDisplay();
        map.subscribe(display);

        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            Simulation simulation = new Simulation(directions, positions, map);
            simulation.run();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println("Stop");
    }
}