package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import agh.ics.oop.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<MoveDirection> directions;
    private List<Animal> animals = new ArrayList<>();

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions) {
        this.directions = directions;

        for (Vector2d position : positions) {
            Animal animal = new Animal(position);
            animals.add(animal);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void run() {
        int dirCount = 0;
        int numberOfLoops = directions.size() / animals.size();
        int lastLoop = directions.size() % animals.size();

        for (int j = 0; j < numberOfLoops; j++) {
            for (int i = 0; i < animals.size(); i++) {
                animals.get(i).move(directions.get(dirCount));
                dirCount++;
                System.out.println("Animal " + (i + 1) + ": " + animals.get(i).getPosition().toString());
            }
        }
        for (int i = 0; i < lastLoop; i++) {
            animals.get(i).move(directions.get(dirCount));
            dirCount++;
            System.out.println("Animal " + (i + 1) + ": " + animals.get(i).getPosition().toString());
        }
    }
}
