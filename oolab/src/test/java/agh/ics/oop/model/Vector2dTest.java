package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void equalsWorksCorrectly() {
        //given
        Vector2d vector1 = new Vector2d(1, 2);
        Vector2d vector2 = new Vector2d(1, 2);
        Vector2d vector3 = new Vector2d(2, 2);
        Vector2d vector4 = new Vector2d(2, 3);

        //when-then
        assertTrue(vector1.equals(vector1)); //same vector
        assertTrue(vector1.equals(vector2)); //same coordinates
        assertFalse(vector1.equals(vector3)); //different x
        assertFalse(vector3.equals(vector4)); //different y
        assertFalse(vector1.equals(vector4)); //different x & y
    }

    @Test
    void toStringCorrectFormat() {
        //given
        Vector2d vector1 = new Vector2d(1, 2);

        //when
        String string1 = vector1.toString();

        //then
        assertEquals("(1,2)", string1);

    }

    @Test
    void followsWorksCorrectly() {
        //given
        Vector2d vector1 = new Vector2d(1, 2);
        Vector2d vector1a = new Vector2d(1, 2); //same as vector1
        Vector2d vector2 = new Vector2d(3, 5); //x & y greater than in vector1
        Vector2d vector3 = new Vector2d(2, 2); //x greater than in vector1
        Vector2d vector4 = new Vector2d(1, 3); //y greater than in vector1

        //when-then
        assertTrue(vector1.follows(vector1)); //same vector
        assertTrue(vector1a.follows(vector1)); //same coordinates
        assertTrue(vector2.follows(vector1)); //1 follows 2
        assertFalse(vector1.follows(vector2)); //2 does not precede 1
        assertTrue(vector3.follows(vector1)); //1 follows 3
        assertTrue(vector4.follows(vector1)); //1 follows 4
    }

    @Test
    void precedesWorksCorrectly() {
        //given
        Vector2d vector1 = new Vector2d(1, 2);
        Vector2d vector1a = new Vector2d(1, 2); //same as vector1
        Vector2d vector2 = new Vector2d(3, 5); //x & y greater than in vector1
        Vector2d vector3 = new Vector2d(2, 2); //x greater than in vector1
        Vector2d vector4 = new Vector2d(1, 3); //y greater than in vector1

        //when-then
        assertTrue(vector1.precedes(vector1)); //same vector
        assertTrue(vector1a.precedes(vector1)); //same coordinates
        assertFalse(vector2.precedes(vector1)); //1 does not follow 2
        assertTrue(vector1.precedes(vector2)); //2 follows 1
        assertTrue(vector1.precedes(vector3)); //3 follows 1
        assertTrue(vector1.precedes(vector4)); //4 follows 1
    }

    @Test
    void upperRightWorksCorrectly() {
        //given
        Vector2d vector1 = new Vector2d(2, 5);
        Vector2d vector2 = new Vector2d(3, 4);

        //when
        Vector2d vector3 = vector1.upperRight(vector2);

        // then
        assertEquals(3, vector3.getX());
        assertEquals(5, vector3.getY());
    }

    @Test
    void lowerLeftWorksCorrectly() {
        //given
        Vector2d vector1 = new Vector2d(2, 5);
        Vector2d vector2 = new Vector2d(3, 4);

        //when
        Vector2d vector3 = vector1.lowerLeft(vector2);

        // then
        assertEquals(2, vector3.getX());
        assertEquals(4, vector3.getY());
    }

    @Test
    void vectorsCanBeAdded() {
        //given
        Vector2d vector1 = new Vector2d(2, 5);
        Vector2d vector2 = new Vector2d(3, 4);

        //when
        Vector2d vector3 = vector1.add(vector2);

        // then
        assertEquals(5, vector3.getX());
        assertEquals(9, vector3.getY());
    }

    @Test
    void vectorsCanBeSubtract() {
        //given
        Vector2d vector1 = new Vector2d(2, 5);
        Vector2d vector2 = new Vector2d(3, 4);

        //when
        Vector2d vector3 = vector1.subtract(vector2);

        // then
        assertEquals(-1, vector3.getX());
        assertEquals(1, vector3.getY());
    }

    @Test
    void oppositeWorksCorrectly() {
        //given
        Vector2d vector1 = new Vector2d(2, -5);
        Vector2d vector2 = new Vector2d(0, 0);

        //when
        Vector2d vector3 = vector1.opposite();
        Vector2d vector4 = vector2.opposite();

        // then
        assertEquals(-2, vector3.getX());
        assertEquals(5, vector3.getY());
        assertEquals(0, vector4.getX());
        assertEquals(0, vector4.getY());
    }
}