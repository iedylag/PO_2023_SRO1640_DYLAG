package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void grassTuftsAreInCorrectAmount() {
        //given
        GrassField map = new GrassField(10);

        //when - then
        assertEquals(10, map.getTrawki().size());
    }

    @Test
    void testPlaceAnimalOnOccupiedPosition() {
        //given
        WorldMap map = new GrassField(5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 2));

        //when - then
        assertTrue(map.place(sheep));
        assertFalse(map.place(sloth));
        assertEquals(sheep, map.objectAt(new Vector2d(2, 2)));
        assertNotEquals('*', map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void testMoveToOccupiedPosition() {
        //given
        WorldMap map = new GrassField(10);
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
    void testReturnsObjectAt() {
        //given
        WorldMap map = new GrassField(10);
        Animal sheep = new Animal(new Vector2d(3, 4));

        //when
        map.place(sheep);

        //then
        assertEquals(sheep, map.objectAt(new Vector2d(3, 4)));
    }

    @Test
    void testGrassFieldReturnsCorrectNumberOfElements() {
        //given
        WorldMap map = new GrassField(5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(1, 1));

        //when
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

        Collection<WorldElement> elements = map.getElements();
        List<WorldElement> elementsExpected = Arrays.asList(sheep, sloth);

        //then
        assertEquals(15, elements.size());  //czy jest 5 traw i 10 zwierzatek
        assertTrue(elements.containsAll(elementsExpected));
    }
}