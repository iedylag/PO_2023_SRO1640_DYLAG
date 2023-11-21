package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void animalsDontGoOffTheMap() {
        //given
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        WorldMap map = new RectangularMap(5, 5);
        map.place(new Animal());
        map.place(new Animal(new Vector2d(3, 4)));

        //when
        Simulation simulation = new Simulation(directions, map);
        simulation.run();

        // then
        Vector2d finalPosition1 = ((RectangularMap) map).getAnimals().get(0).getPosition();
        Vector2d finalPosition2 = ((RectangularMap) map).getAnimals().get(1).getPosition();

        assertTrue(finalPosition1.follows(new Vector2d(0, 0)) && finalPosition1.precedes(new Vector2d(4, 4)));
        assertTrue(finalPosition2.follows(new Vector2d(0, 0)) && finalPosition2.precedes(new Vector2d(4, 4)));
    }

    @Test
    void animalsCorrectOrientation() {
        //given
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        WorldMap map = new RectangularMap(5, 5);
        map.place(new Animal());
        map.place(new Animal(new Vector2d(3, 4)));

        //when
        Simulation simulation = new Simulation(directions, map);
        simulation.run();
        List<MapDirection> finalOrientations = Arrays.asList(((RectangularMap) map).getAnimals().get(0).getOrientation(), ((RectangularMap) map).getAnimals().get(1).getOrientation());
        List<MapDirection> correctOrientations = Arrays.asList(MapDirection.NORTH, MapDirection.SOUTH);

        // then
        assertTrue(finalOrientations.size() == correctOrientations.size() && finalOrientations.containsAll(correctOrientations) && correctOrientations.containsAll(finalOrientations));
    }

    @Test
    void testMovingToCorrectPositions() {
        //given
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "r", "f", "r", "f", "f", "f", "f"});
        WorldMap map = new RectangularMap(5, 5);
        map.place(new Animal());

        //when
        Simulation simulation = new Simulation(directions, map);
        List<Animal> animals = ((RectangularMap) map).getAnimals();

        // then
        //sprawdzam dla jednego zwierzatka
        List<Vector2d> correctPositions = List.of(new Vector2d(2, 3), new Vector2d(2, 3),
                new Vector2d(3, 3), new Vector2d(3, 3),
                new Vector2d(3, 2), new Vector2d(3, 1),
                new Vector2d(3, 0), new Vector2d(3, 0));
        List<MoveDirection> correctInterpretations = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD);

        for (int i = 0; i < directions.size(); i++) {
            animals.get(0).move(directions.get(i), map);
            assertTrue(animals.get(0).getPosition().equals(correctPositions.get(i))); //czy przemieszcza się na właściwe pozycje
            assertEquals(correctInterpretations.get(i), directions.get(i)); //czy dane wejściowe podane jako tablica łańcuchów znaków są poprawnie interpretowane.
        }

    }

    @Test
    void testDoNotGoOnOccupiedPosition(){
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2,3));
        map.place(sheep);
        map.place(sloth);

        //when
        map.move(sheep, MoveDirection.FORWARD);
        map.move(sloth, MoveDirection.BACKWARD);

        // then
        assertEquals(new Vector2d(2,2), sheep.getPosition());
        assertEquals(new Vector2d(2,3), sloth.getPosition());
    }
}