package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        grassFieldGenerate(grassCount);
    }

    private void grassFieldGenerate(int grassCount) {
        Vector2d grassBoundary = new Vector2d((int) Math.sqrt(grassCount * 10), (int) Math.sqrt(grassCount * 10));
        int maxWidth = grassBoundary.getX() + 1; //zakladam, ze np. przy n=10, obszar jest od (0,0) do (10,10) wlacznie
        int maxHeight = grassBoundary.getY() + 1;

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    public int getGrassesSize() {
        return grasses.size();
    }

    @Override
    public Collection<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(super.getElements());
        elements.addAll(grasses.values());
        return elements;
    }

    private Vector2d lowLeftCornerCalculate() {
        Vector2d lowLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (Vector2d elementPosition : grasses.keySet()) {
            lowLeftCorner = elementPosition.lowerLeft(lowLeftCorner);
        }
        for (Vector2d elementPosition : animals.keySet()) {
            lowLeftCorner = elementPosition.lowerLeft(lowLeftCorner);
        }
        return lowLeftCorner;
    }

    private Vector2d upRightCornerCalculate() {
        Vector2d upRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Vector2d elementPosition : grasses.keySet()) {
            upRightCorner = elementPosition.upperRight(upRightCorner);
        }
        for (Vector2d elementPosition : animals.keySet()) {
            upRightCorner = elementPosition.upperRight(upRightCorner);
        }
        return upRightCorner;
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
        return visualizer.draw(lowLeftCornerCalculate(), upRightCornerCalculate());
    }
}
