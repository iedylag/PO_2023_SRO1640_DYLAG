package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void placeAnimalOnOccupiedPosition() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 2));
        //then
        assertDoesNotThrow(() -> map.place(sheep));
        assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(sloth));
    }

    @Test
    void moveToOccupiedPosition() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 3));

        //when
        assertDoesNotThrow(() -> map.place(sheep));
        assertDoesNotThrow(() -> map.place(sloth));
        map.move(sloth, MoveDirection.BACKWARD);

        //then
        assertEquals(new Vector2d(2, 3), sloth.getPosition());
        assertEquals(sheep, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void returnObjectAt() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal(new Vector2d(3, 4));

        //when - then
        assertDoesNotThrow(() -> map.place(sheep));
        assertEquals(sheep, map.objectAt(new Vector2d(3, 4)));
        assertNull(map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void elementsAreCorrectlyAdded() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 3));

        //when
        try {
            map.place(sheep);
            map.place(sloth);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        }
        Collection<WorldElement> elements = map.getElements();
        List<Animal> elementsExpected = List.of(sheep, sloth);

        //then
        assertEquals(2, elements.size());
        assertTrue(elements.containsAll(elementsExpected));
    }
}