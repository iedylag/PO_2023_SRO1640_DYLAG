package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractWorldMap implements WorldMap {
    public static final Vector2d LOWER_LEFT = new Vector2d(0, 0);
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    @Override
    public List<Animal> getAnimals() {
        return List.copyOf(animals.values());
    }

    @Override
    public Collection<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public boolean place(Animal animal) throws PositionAlreadyOccupiedException {
        Vector2d animalPosition = animal.getPosition();
        if (canMoveTo(animalPosition)) {
            animals.put(animalPosition, animal);
            return true;
        }
        throw new PositionAlreadyOccupiedException(animalPosition);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (!Objects.equals(oldPosition, newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        Boundary boundary = getCurrentBounds();
        return visualizer.draw(boundary.lowLeftCorner(), boundary.upRightCorner());
    }
}
