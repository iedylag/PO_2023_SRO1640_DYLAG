package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import agh.ics.oop.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<MoveDirection> directions;
    private final List<Animal> animals = new ArrayList<>();

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions) {
        this.directions = directions;
        createAnimals(positions);
    }

    private void createAnimals(List<Vector2d> positions) {
        for (Vector2d position : positions) {
            Animal animal = new Animal(position);
            animals.add(animal);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void run() {
        for (int i = 0; i < directions.size(); i++) {
            int animalIndex = i % animals.size();
            animals.get(animalIndex).move(directions.get(i));
            System.out.println("Animal " + (animalIndex + 1) + ": " + animals.get(animalIndex).getPosition().toString());
        }
    }
}
