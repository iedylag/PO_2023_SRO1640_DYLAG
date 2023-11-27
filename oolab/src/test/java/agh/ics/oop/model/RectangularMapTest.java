package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void testPlaceAnimalOnOccupiedPosition() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 2));

        //when - then
        assertTrue(map.place(sheep));
        assertFalse(map.place(sloth));
    }

    @Test
    void testMoveToOccupiedPosition() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 3));

        //when
        map.place(sheep);
        map.place(sloth);
        map.move(sloth, MoveDirection.BACKWARD);

        //then
        assertEquals(new Vector2d(2, 3), sloth.getPosition());
        assertEquals(sheep, map.objectAt(new Vector2d(2, 2)));
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

    @Test
    void testGetElements() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 3));

        //when
        map.place(sheep);
        map.place(sloth);
        Collection<WorldElement> elements = map.getElements();
        List<Animal> elementsExpected = Arrays.asList(sheep, sloth);

        //then
        assertEquals(2, elements.size());
        assertTrue(elements.containsAll(elementsExpected));
    }
}