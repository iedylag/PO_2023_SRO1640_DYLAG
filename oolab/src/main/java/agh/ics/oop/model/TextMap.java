package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class TextMap implements WorldMap<String, Integer> {
    private static final MapDirection DEFAULT_MAP_DIRECTION = MapDirection.NORTH;
    private MapDirection orientation = DEFAULT_MAP_DIRECTION;

    //ten opis mapy troche smierdzi ArrayListą
    private final List<String> objects = new ArrayList<>();

    @Override
    public boolean canMoveTo(Integer position) {
        return position >= 0 && position < objects.size();
    }

    @Override
    public boolean place(String object) {
        objects.add(object);
        return true;
    }

    @Override
    public void move(String object, MoveDirection direction) {

        int oldPosition = objects.indexOf(object);
        int newPosition = oldPosition;
        orientation = switch (direction){
            case RIGHT -> orientation.next();
            case LEFT -> orientation.previous();
            case FORWARD, BACKWARD -> orientation;
        };
        if(orientation == MapDirection.EAST) {
            newPosition = switch (direction) {
                case FORWARD -> oldPosition + 1;
                case BACKWARD -> oldPosition - 1;
                case RIGHT, LEFT -> oldPosition;
            };
        } else if (orientation == MapDirection.WEST) {
            newPosition = switch (direction) {
                case FORWARD -> oldPosition - 1;
                case BACKWARD -> oldPosition + 1;
                case RIGHT, LEFT -> oldPosition;
            };
        }
        objects.remove(oldPosition);

        if (newPosition >= objects.size()) {
            objects.add(object);
        } else {
            objects.add(newPosition, object);
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return position >= 0 && objects.size() >= position;
    }

    @Override
    public String objectAt(Integer position) {
        if (isOccupied(position)) {
            return objects.get(position);
        }
        return null;
    }
    @Override
    public String toString() {
        return String.join(" ", objects);
    }
}
