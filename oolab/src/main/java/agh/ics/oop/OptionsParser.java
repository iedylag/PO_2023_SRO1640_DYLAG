package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> directions = new ArrayList<>();

        for (String arg : args) {
            if (arg != null) {
                MoveDirection direction = switch (arg) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "l" -> MoveDirection.LEFT;
                    case "r" -> MoveDirection.RIGHT;
                    default -> null;
                };

                if (direction != null) {
                    directions.add(direction);
                }
            }
        }
        return directions;
    }
}
