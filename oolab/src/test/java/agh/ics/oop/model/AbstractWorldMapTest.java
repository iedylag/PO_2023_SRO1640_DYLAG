package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void areTheAnimalsSortedByPosition() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        List<MoveDirection> directions = List.of(MoveDirection.RIGHT, MoveDirection.RIGHT);
        List<Vector2d> positions = List.of(new Vector2d(2, 0), new Vector2d(2, 3), new Vector2d(0, 1));
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        //when
        Map<Vector2d, Animal> orderedAnimals = map.getOrderedAnimals();
        Vector2d[] expectedOrder = new Vector2d[]{new Vector2d(0, 1), new Vector2d(2, 0), new Vector2d(2, 3)};

        //then
        assertEquals(3, orderedAnimals.size());
        assertArrayEquals(expectedOrder, orderedAnimals.keySet().toArray());
    }
}