package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.PositionAlreadyOccupiedException;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class Simulation implements Runnable {
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions, WorldMap map) {
        this.directions = directions;
        this.map = map;

        for (Vector2d position : positions) {
            try {
                map.place(new Animal(position));
            } catch (PositionAlreadyOccupiedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        List<Animal> animals = map.getAnimals();

        for (int i = 0; i < directions.size(); i++) {
            Animal animal = animals.get(i % animals.size());
            map.move(animal, directions.get(i));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}