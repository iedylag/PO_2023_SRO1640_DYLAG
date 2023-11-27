package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testReturnCorrectString() {
        //given
        Vector2d position1 = new Vector2d(1, 2);
        Animal sloth = new Animal(position1);
        //when
        String string1 = sloth.toString();

        //then
        assertEquals("N", string1);
    }

    @Test
    void testAnimalIsAtGivenPosition() {
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
    void testMoveToCorrectPosition() {
        //given
        MoveValidator validator = new MoveValidator() { //dummy object
            @Override
            public boolean canMoveTo(Vector2d position) {
                return position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(4,4));
            }
        };

        Animal sloth = new Animal(new Vector2d(0, 0));
        Animal sheep = new Animal();
        Animal beardedDragon = new Animal(new Vector2d(2,3));

        //when
        sloth.move(MoveDirection.BACKWARD, validator); // stay on the map
        sheep.move(MoveDirection.RIGHT, validator); //stay in this position, change direction to EAST
        beardedDragon.move(MoveDirection.FORWARD, validator);

        // then
        assertEquals(0, sloth.getPosition().getX());
        assertEquals(0, sloth.getPosition().getY()); //stay on the map
        assertEquals(2, sheep.getPosition().getX());
        assertEquals(2, sheep.getPosition().getY());
        assertEquals(2, beardedDragon.getPosition().getX());
        assertEquals(4, beardedDragon.getPosition().getY());
        assertEquals(MapDirection.NORTH, sloth.getOrientation());
        assertEquals(MapDirection.EAST, sheep.getOrientation());
    }
}