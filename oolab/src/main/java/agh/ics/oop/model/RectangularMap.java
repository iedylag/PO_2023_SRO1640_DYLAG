package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    private final Vector2d upperRight;

    public RectangularMap(int height, int width) {
        upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(LOWER_LEFT) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(LOWER_LEFT, upperRight);
    }
}