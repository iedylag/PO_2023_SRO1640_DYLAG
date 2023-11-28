package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;


public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private Vector2d lowBoundary = LOWER_LEFT;
    private Vector2d upBoundary;

    public GrassField(int grassCount) {
        Vector2d grassBoundary = new Vector2d((int) Math.sqrt(grassCount * 10), (int) Math.sqrt(grassCount * 10));
        int maxWidth = grassBoundary.getX() + 1; //zakladam, ze np. przy n=10, obszar jest od (0,0) do (10,10) wlacznie
        int maxHeight = grassBoundary.getY() + 1;

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }

        upBoundary = grassBoundary;
    }

    public Map<Vector2d, Grass> getTrawki() {
        return grasses;
    }

    @Override
    public Collection<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(super.getElements());
        elements.addAll(grasses.values());
        return elements;
    }

    private void mapBoundary(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        lowBoundary = animalPosition.lowerLeft(lowBoundary);
        upBoundary = animalPosition.upperRight(upBoundary);
    }

    @Override
    public boolean place(Animal animal) {
        boolean placed = super.place(animal);
        mapBoundary(animal);
        return placed;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        super.move(animal, direction);
        mapBoundary(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement element = super.objectAt(position);
        if (element != null) {
            return element;
        }
        return grasses.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowBoundary, upBoundary);
    }
}
