package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseCorrectArray() {
        //given
        String[] args1 = {"b", "f", "f", "l"};
        String[] args2 = {"a", "b", "x"};
        String[] args3 = {null, "b", "r", null};
        //when - then
        assertDoesNotThrow(() -> OptionsParser.parse(args1));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args2));
        //assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args3));
    }

}