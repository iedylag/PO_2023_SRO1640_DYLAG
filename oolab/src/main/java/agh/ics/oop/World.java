package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;


public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        WorldMap<Animal, Vector2d> map = new RectangularMap(5, 5);
        List<MoveDirection> directions = OptionsParser.parse(args);
        map.place(new Animal());
        map.place(new Animal(new Vector2d(3, 4)));

        Simulation simulation = new Simulation(directions, map);
        simulation.run();
        System.out.println("Stop");

        TextMap textMap = new TextMap();
        textMap.place("Inga");
        textMap.place("Pati");
        textMap.place("Agamson");
        textMap.move("Agamson", MoveDirection.LEFT);
        textMap.move("Agamson", MoveDirection.FORWARD);
        System.out.println(textMap);
    }
}
