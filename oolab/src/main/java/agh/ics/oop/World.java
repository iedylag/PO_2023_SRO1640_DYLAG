package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.PositionAlreadyOccupiedException;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        WorldMap map = new GrassField(10);
        ConsoleMapDisplay display = new ConsoleMapDisplay();
        map.subscribe(display);

        try {
            map.place(new Animal());
            map.place(new Animal(new Vector2d(3, 4)));
            map.place(new Animal());
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        }

        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            Simulation simulation = new Simulation(directions, map);
            simulation.run();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println("Stop");
    }
}