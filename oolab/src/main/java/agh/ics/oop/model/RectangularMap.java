package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

public class RectangularMap implements WorldMap {
    public final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int height, int width) {
        upperRight = new Vector2d(width - 1, height - 1);
    }

    public List<Animal> getAnimals() {
        return List.copyOf(animals.values());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if (canMoveTo(animalPosition)) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (!oldPosition.equals(newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }
}
