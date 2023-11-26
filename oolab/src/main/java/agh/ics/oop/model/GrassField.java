package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

import static agh.ics.oop.model.RectangularMap.LOWER_LEFT;

public class GrassField implements WorldMap {
    private final Map<Vector2d, Grass> trawki = new HashMap<>();
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private Vector2d lowBoundary = LOWER_LEFT;
    private Vector2d upBoundary = LOWER_LEFT;

    public GrassField(int countTrawki) {
        Vector2d grassBoundary = new Vector2d((int) Math.sqrt(countTrawki * 10), (int) Math.sqrt(countTrawki * 10));

        int positionsCount = 0;
        while (positionsCount < countTrawki) {
            int X = (int) (Math.random() * grassBoundary.getX());
            int Y = (int) (Math.random() * grassBoundary.getY());
            Vector2d grassField = new Vector2d(X, Y);
            if (!trawki.containsKey(grassField)) {
                trawki.put(grassField, new Grass(grassField));
                positionsCount++;

                /* wyznaczamy skrajne pozycje trawki */
                this.lowBoundary = grassField.lowerLeft(lowBoundary);
                this.upBoundary = grassField.upperRight(upBoundary);
            }
        }
    }

    public Map<Vector2d, Grass> getTrawki() {
        return trawki;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public List<Animal> getAnimals() {
        return List.copyOf(animals.values());
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if (canMoveTo(animalPosition)) {
            animals.put(animalPosition, animal);
            lowBoundary = animalPosition.lowerLeft(lowBoundary);
            upBoundary = animalPosition.upperRight(upBoundary);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (!Objects.equals(oldPosition, newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);

            /* przy kazdym przesuwaniu sprawdzamy czy mapa sie nie zwieksza */
            lowBoundary = newPosition.lowerLeft(lowBoundary);
            upBoundary = newPosition.upperRight(upBoundary);
        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return trawki.containsKey(position) || animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        } else if (trawki.containsKey(position)) {
            return trawki.get(position);
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowBoundary, upBoundary);
    }
}
