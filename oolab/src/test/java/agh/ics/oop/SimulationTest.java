package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void testRun() {
        //given
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

        //when
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        // then
        Vector2d finalPosition1 = animals.get(0).getPosition();
        Vector2d finalPosition2 = animals.get(1).getPosition();

        assertEquals(MapDirection.SOUTH, animals.get(0).getOrientation()); //czy ma właściwą orientację
        assertEquals(MapDirection.NORTH, animals.get(1).getOrientation());

        assertTrue(finalPosition1.follows(new Vector2d(4, 4)) && finalPosition1.precedes(new Vector2d(0, 0))); //czy nie wychodzi poza mape
        assertTrue(finalPosition2.follows(new Vector2d(4, 4)) && finalPosition2.precedes(new Vector2d(0, 0)));

        assertTrue(finalPosition1.equals(new Vector2d(3, 0))); //czy konczy na właściwych pozycjach
        assertTrue(finalPosition2.equals(new Vector2d(2, 4)));
    }

    @Test
    void testMovingToCorrectPositions() {
        //given
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "r", "f", "r", "f", "f", "f", "f"});
        List<Vector2d> positions = List.of(new Vector2d(2, 2));

        //when
        Simulation simulation = new Simulation(directions, positions);
        List<Animal> animals = simulation.getAnimals();

        // then
        List<Vector2d> correctPositions = List.of(new Vector2d(2, 3), new Vector2d(2, 3),
                new Vector2d(3, 3), new Vector2d(3, 3),
                new Vector2d(3, 2), new Vector2d(3, 1),
                new Vector2d(3, 0), new Vector2d(3, 0));
        List<MoveDirection> correctInterpretations = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD);

        for (int i = 0; i < directions.size(); i++) {
            animals.get(0).move(directions.get(i));
            assertTrue(animals.get(0).getPosition().equals(correctPositions.get(i))); //czy przemieszcza się na właściwe pozycje
            assertEquals(correctInterpretations.get(i), directions.get(i)); //czy dane wejściowe podane jako tablica łańcuchów znaków są poprawnie interpretowane.
        }

    }

}