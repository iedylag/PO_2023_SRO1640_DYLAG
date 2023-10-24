package agh.ics.oop;

import java.util.Arrays;

import agh.ics.oop.model.MoveDirection;
public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] directions = new MoveDirection[args.length];

        int i = 0;
        for (String arg : args) {
            MoveDirection direction = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };

            if(direction != null) {
                directions[i] = direction;
                i++;
            }
        }
        return Arrays.copyOfRange(directions, 0, i);
    }
}
