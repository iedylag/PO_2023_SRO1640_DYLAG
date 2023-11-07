package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseCorrectArray() {
        //given
        String[] args1 = {"b", "f", "f", "l"};
        String[] args2 = {"a", "b", "x"};
        String[] args3 = {null, "b", "r", null};

        MoveDirection[] expected1 = {MoveDirection.BACKWARD, MoveDirection.FORWARD,MoveDirection.FORWARD, MoveDirection.LEFT};
        MoveDirection[] expected2 = {MoveDirection.BACKWARD};
        MoveDirection[] expected3 = {MoveDirection.BACKWARD, MoveDirection.RIGHT};

        //when
        MoveDirection[] actual1 = OptionsParser.parse(args1);
        MoveDirection[] actual2 = OptionsParser.parse(args2);
        MoveDirection[] actual3 = OptionsParser.parse(args3);

        //then
        assertArrayEquals(expected1, actual1);
        assertArrayEquals(expected2, actual2);
        assertArrayEquals(expected3, actual3);
    }

}