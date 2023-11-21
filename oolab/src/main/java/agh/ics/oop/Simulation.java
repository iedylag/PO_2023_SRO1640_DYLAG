package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Simulation {
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public Simulation(List<MoveDirection> directions, WorldMap map) {
        this.directions = directions;
        this.map = map;
    }

    public void run() {
        List<Animal> animals = ((RectangularMap) map).getAnimals();

        for (int i = 0; i < directions.size(); i++) {
            Animal animal = animals.get(i % animals.size());
            map.move(animal, directions.get(i));
            System.out.println(map);
        }
    }
}
