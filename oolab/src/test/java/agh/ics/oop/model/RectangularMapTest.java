package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void testPlaceAnimalOnOccupiedPosition() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal();

        //when
        Animal sloth = new Animal(new Vector2d(2, 2));

        //then
        assertTrue(map.place(sheep));
        assertFalse(map.place(sloth));
    }

    @Test
    void testReturnObjectAt() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal(new Vector2d(3, 4));

        //when
        map.place(sheep);

        //then
        assertEquals(sheep, map.objectAt(new Vector2d(3, 4)));
        assertNull(map.objectAt(new Vector2d(2, 2)));
    }
}