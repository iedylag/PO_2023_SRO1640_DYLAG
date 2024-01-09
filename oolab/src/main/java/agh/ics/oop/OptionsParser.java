package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {

       /* for (String arg : args) {
            if (arg == null) {
                throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }*/

        return Stream.of(args)
                .filter(Objects::nonNull)  //stwierdziłam, że będzie lepiej pozbyc sie null
                .map(arg -> switch (arg) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "l" -> MoveDirection.LEFT;
                    case "r" -> MoveDirection.RIGHT;
                    default -> throw new IllegalArgumentException(arg + " is not legal move specification"); //unchecked
                })
                .collect(Collectors.toList());
    }
}
