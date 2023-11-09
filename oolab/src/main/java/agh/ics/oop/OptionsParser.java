package agh.ics.oop;

import java.util.Arrays;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] directions = new MoveDirection[args.length];

        int optionsCount = 0;
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
                    directions[optionsCount] = direction;
                    optionsCount++;
                }
            }
        }
        return Arrays.copyOfRange(directions, 0, optionsCount);
    }
}
