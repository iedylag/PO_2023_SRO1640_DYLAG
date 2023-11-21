package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;


public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        WorldMap map = new RectangularMap(5, 5);
        List<MoveDirection> directions = OptionsParser.parse(args);
        map.place(new Animal());
        map.place(new Animal(new Vector2d(3, 4)));

        Simulation simulation = new Simulation(directions, map);
        simulation.run();
        System.out.println("Stop");
    }
}
