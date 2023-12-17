package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> directions = new ArrayList<>();

        for (String arg : args) {
            if (arg == null) {
                throw new IllegalArgumentException(arg + " is not legal move specification");
            }
            MoveDirection direction = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> throw new IllegalArgumentException(arg + " is not legal move specification"); //unchecked
            };
            directions.add(direction);
        }
        return directions;
    }
}
