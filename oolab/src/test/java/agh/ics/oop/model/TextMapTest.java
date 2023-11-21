package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {
    @Test
    void testPlace() {
        //given
        TextMap textMap = new TextMap();
        textMap.place("Inga");
        textMap.place("Pati");

        //when - then
        assertEquals("Inga Pati", textMap.toString());
    }

    @Test
    void stayOnTheMap() {
        //given
        TextMap textMap = new TextMap();
        textMap.place("Inga");

        //when - then
        textMap.move("Inga", MoveDirection.LEFT);
        textMap.move("Inga", MoveDirection.BACKWARD); //nie powinien isc na pole z indeksem 1
        assertEquals("Inga", textMap.objectAt(0));
    }
    @Test
    void moveToCorrectPosition(){
        //given
        TextMap textMap = new TextMap();
        textMap.place("Inga");
        textMap.place("Pati");
        System.out.println(textMap);
        //when - then
        textMap.move("Pati", MoveDirection.RIGHT);
        textMap.move("Pati", MoveDirection.BACKWARD);
        System.out.println(textMap);
        assertEquals("Pati", textMap.objectAt(0));
        assertEquals("Inga", textMap.objectAt(1));
    }
}