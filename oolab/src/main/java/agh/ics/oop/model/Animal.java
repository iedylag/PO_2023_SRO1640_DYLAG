package agh.ics.oop.model;

public class Animal {
    private static final MapDirection DEFAULT_MAPDIRECTION = MapDirection.NORTH;
    private MapDirection currentMapDirection = DEFAULT_MAPDIRECTION;
    private Vector2d position;

    public Animal(Vector2d position) {
        this(position, DEFAULT_MAPDIRECTION);
    }

    public Animal(Vector2d position, MapDirection currentMapDirection) {
        this.currentMapDirection = currentMapDirection;
        this.position = new Vector2d(2, 2);
    }

    @Override
    public String toString() {
        return "Jest na pozycji " + position + " i zmierza na " + currentMapDirection;
    }

    boolean isAt(Vector2d position) {
        return position.equals(this.position); //Objects.equals(this.position , position);
    }

    public void move(MoveDirection direction) {
        currentMapDirection = switch (direction) {
            case RIGHT -> currentMapDirection.next();
            case LEFT -> currentMapDirection.previous();
            default -> currentMapDirection;
        };

        position = switch (direction) {
            case FORWARD -> position.add(currentMapDirection.toUnitVector());
            case BACKWARD -> position.subtract(currentMapDirection.toUnitVector());
            default -> position;
        };

        Vector2d bottomVector = new Vector2d(0, 0);
        Vector2d topVector = new Vector2d(4, 4);

        if (position.getX() < bottomVector.getX() || position.getY() < bottomVector.getY()) {
            position.upperRight(bottomVector);
        } else if (position.getX() > topVector.getX() || position.getY() > topVector.getY()) {
            position.lowerLeft(bottomVector);
        }
    }

}
