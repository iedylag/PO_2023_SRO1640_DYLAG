package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {

        return Stream.of(args)
                .map(arg -> switch (arg) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "l" -> MoveDirection.LEFT;
                    case "r" -> MoveDirection.RIGHT;
                    default -> throw new IllegalArgumentException(arg + " is not legal move specification"); //unchecked
                })
                .toList();   //collect(Collectors.toList());
    }
}
