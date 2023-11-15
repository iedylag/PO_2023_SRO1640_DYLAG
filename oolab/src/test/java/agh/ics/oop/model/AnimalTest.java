package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testToString() {
        //given
        Vector2d position1 = new Vector2d(1, 2);
        Animal sloth = new Animal(position1);
        //when
        String string1 = sloth.toString();

        //then
        assertEquals("Jest na pozycji (1,2) i zmierza na Północ", string1);
    }

    @Test
    void isAt() {
        //given
        Vector2d position1 = new Vector2d(1, 2);
        Vector2d position2 = new Vector2d(1, 2);
        Vector2d position3 = new Vector2d(2, 2);
        Animal sloth = new Animal(position1);

        //when-then
        assertTrue(sloth.isAt(position2)); //same coordinates
        assertFalse(sloth.isAt(position3));
    }

    @Test
    void testMove() {
        //given
        Vector2d position1 = new Vector2d(0, 0);
        Animal sloth = new Animal(position1);
        Vector2d position2 = new Vector2d(2, 2);
        Animal sheep = new Animal(position2);
        Vector2d position3 = new Vector2d(2, 2);
        Animal beardedDragon = new Animal(position2);

        //when
        sloth.move(MoveDirection.BACKWARD);
        sheep.move(MoveDirection.RIGHT); //stay in this position, change direction to EAST
        beardedDragon.move(MoveDirection.FORWARD);

        // then
        assertEquals(0, sloth.getPosition().getX());
        assertEquals(0, sloth.getPosition().getY()); //stay on the map
        assertEquals(2, sheep.getPosition().getX());
        assertEquals(2, sheep.getPosition().getY());
        assertEquals(2, beardedDragon.getPosition().getX());
        assertEquals(3, beardedDragon.getPosition().getY());
        assertEquals(MapDirection.NORTH, sloth.getOrientation());
        assertEquals(MapDirection.EAST, sheep.getOrientation());
    }
}