package agh.ics.oop.model;

public class Animal {
    private static final MapDirection DEFAULT_MAP_DIRECTION = MapDirection.NORTH;
    private MapDirection orientation = DEFAULT_MAP_DIRECTION;
    private Vector2d position;

    //domyslny
    public Animal() {
        this(new Vector2d(2, 2));
    }

    //ustawiajacy pozycje
    public Animal(Vector2d position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Jest na pozycji " + position + " i zmierza na " + orientation;
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position); //Objects.equals(this.position , position);
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public void move(MoveDirection direction) {
        orientation = switch (direction) {
            case RIGHT -> orientation.next();
            case LEFT -> orientation.previous();
            case FORWARD, BACKWARD -> orientation;
        };

        Vector2d newPosition = switch (direction) {
            case FORWARD -> position.add(orientation.toUnitVector());
            case BACKWARD -> position.subtract(orientation.toUnitVector());
            case RIGHT, LEFT -> position;
        };

        if (newPosition.precedes(new Vector2d(0, 0)) && newPosition.follows(new Vector2d(4, 4))) {
            position = newPosition;
        }
    }
}
