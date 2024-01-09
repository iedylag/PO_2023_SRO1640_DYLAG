package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap {

    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final BlockingDeque<MapChangeListener> observers = new LinkedBlockingDeque<>(); //lista obserwatorów
    private final UUID mapId = UUID.randomUUID();

    @Override
    public UUID getId() {
        return mapId;
    }

    @Override
    public void subscribe(MapChangeListener observer) {  //rejestrowanie obserwatora
        observers.add(observer);
    }

    @Override
    public void unsubscribe(MapChangeListener observer) {  //wyrejestrowanie obserwatora
        observers.remove(observer);
    }

    private void mapChanged(String message) {
        observers.forEach(observer -> observer.mapChanged(this, message));
    }

    @Override
    public List<Animal> getAnimals() {
        return List.copyOf(animals.values());
    }

    @Override
    public Map<Vector2d, Animal> getOrderedAnimals() {
        Comparator<Vector2d> positionComparator =
                Comparator.comparingInt(Vector2d::getX).thenComparingInt(Vector2d::getY);
        return animals.keySet().stream()
                .sorted(positionComparator)
                .collect(Collectors.toMap(Function.identity(), animals::get, (key1, key2) -> key1, LinkedHashMap::new));
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
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        Vector2d animalPosition = animal.getPosition();
        if (canMoveTo(animalPosition)) {
            animals.put(animalPosition, animal);
            mapChanged("Animal placed at " + animalPosition + " and is heading " + animal.getOrientation());
        } else {
            throw new PositionAlreadyOccupiedException(animalPosition);
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (!Objects.equals(oldPosition, newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
            mapChanged("Animal moved from " + oldPosition + " to " + newPosition + " and is heading " + animal.getOrientation());
        } else {
            mapChanged("Animal remains in position, but heads " + animal.getOrientation());
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
