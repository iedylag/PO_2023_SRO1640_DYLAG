package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void grassTuftsAreInCorrectAmount() {
        //given
        GrassField map = new GrassField(10);

        //when - then
        assertEquals(10, map.getGrassesSize());
    }

    @Test
    void placeAnimalOnOccupiedPosition() {
        //given
        WorldMap map = new GrassField(5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 2));

        //when - then
        assertDoesNotThrow(() -> map.place(sheep));
        assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(sloth));
        assertEquals(sheep, map.objectAt(new Vector2d(2, 2)));
        assertNotEquals('*', map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void moveToOccupiedPosition() {
        //given
        WorldMap map = new GrassField(10);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 3));

        //when
        try {
            map.place(sheep);
            map.place(sloth);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        }
        map.move(sloth, MoveDirection.BACKWARD);

        //then
        assertEquals(new Vector2d(2, 3), sloth.getPosition());
        assertEquals(sheep, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void returnsObjectAt() {
        //given
        WorldMap map = new GrassField(10);
        Animal sheep = new Animal(new Vector2d(3, 4));

        //when - then
        assertDoesNotThrow(() -> map.place(sheep));
        assertEquals(sheep, map.objectAt(new Vector2d(3, 4)));
    }

    @Test
    void grassFieldReturnsCorrectNumberOfElements() {
        //given
        WorldMap map = new GrassField(5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(1, 1));

        //when
        try {
            map.place(sloth);
            map.place(sheep);
            map.place(new Animal(new Vector2d(3, 3)));  //3
            map.place(new Animal(new Vector2d(4, 4)));  //4
            map.place(new Animal(new Vector2d(5, 5)));  //5
            map.place(new Animal(new Vector2d(5, 1)));  //6
            map.place(new Animal(new Vector2d(4, 2)));  //7
            map.place(new Animal(new Vector2d(2, 1)));  //8
            map.place(new Animal(new Vector2d(2, 3)));  //9
            map.place(new Animal(new Vector2d(4, 3)));  //10
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        }
        Collection<WorldElement> elements = map.getElements();
        List<WorldElement> elementsExpected = Arrays.asList(sheep, sloth);

        //then
        assertEquals(15, elements.size());  //czy jest 5 traw i 10 zwierzatek
        assertTrue(elements.containsAll(elementsExpected));
    }
}