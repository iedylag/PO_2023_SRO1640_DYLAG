package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseCorrectArray() {
        //given
        String[] args1 = {"b", "f", "f", "l"};
        String[] args2 = {"a", "b", "x"};
        String[] args3 = {null, "b", "r", null};

        List<MoveDirection> expected1 = Arrays.asList(MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT);
        List<MoveDirection> expected2 = List.of(MoveDirection.BACKWARD);
        List<MoveDirection> expected3 = Arrays.asList(MoveDirection.BACKWARD, MoveDirection.RIGHT);

        //when
        List<MoveDirection> actual1 = OptionsParser.parse(args1);
        List<MoveDirection> actual2 = OptionsParser.parse(args2);
        List<MoveDirection> actual3 = OptionsParser.parse(args3);

        //then
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

}