package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

import static agh.ics.oop.model.RectangularMap.LOWER_LEFT;

public class GrassField implements WorldMap {
    private final Map<Vector2d, Grass> trawki = new HashMap<>();
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    private Vector2d grassLowerLeft = LOWER_LEFT;
    private Vector2d grassUpperRight = LOWER_LEFT;
    private Vector2d animalLowerLeft = LOWER_LEFT;
    private Vector2d animalUpperRight = LOWER_LEFT;

    public GrassField(int countTrawki) {
        Vector2d upperRight = new Vector2d((int) Math.sqrt(countTrawki * 10), (int) Math.sqrt(countTrawki * 10));

        int X;
        int Y;
        int positionsCount = 0;

        while (positionsCount < countTrawki) {
            X = (int) (Math.random() * upperRight.getX());
            Y = (int) (Math.random() * upperRight.getY());
            Vector2d grassPosition = new Vector2d(X, Y);
            if (!trawki.containsKey(grassPosition)) {
                trawki.put(grassPosition, new Grass(grassPosition));

                /* wyznaczamy skrajne pozycje trawki */
                this.grassLowerLeft = grassPosition.lowerLeft(grassLowerLeft);
                this.grassUpperRight = grassPosition.upperRight(grassUpperRight);

                positionsCount++;
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
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
            animalLowerLeft = animalPosition.lowerLeft(animalLowerLeft);
            animalUpperRight = animalPosition.upperRight(animalUpperRight);
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
            animalLowerLeft = newPosition.lowerLeft(animalLowerLeft);
            animalUpperRight = newPosition.upperRight(animalUpperRight);
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
        }//(WorldElement) Arrays.asList(trawki.get(position), animals.get(position));
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(grassLowerLeft.lowerLeft(animalLowerLeft), grassUpperRight.upperRight(animalUpperRight));
    }
}
