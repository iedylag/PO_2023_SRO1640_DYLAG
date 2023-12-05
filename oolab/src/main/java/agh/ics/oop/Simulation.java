package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class Simulation {
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public Simulation(List<MoveDirection> directions, WorldMap map) {
        this.directions = directions;
        this.map = map;
    }

    public void run() {
        List<Animal> animals = map.getAnimals();

        for (int i = 0; i < directions.size(); i++) {
            Animal animal = animals.get(i % animals.size());
            map.move(animal, directions.get(i));
        }
    }
}