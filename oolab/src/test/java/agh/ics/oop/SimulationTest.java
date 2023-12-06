package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.PositionAlreadyOccupiedException;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void animalsStayTheMap() {
        //given
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        WorldMap map = new RectangularMap(5, 5);

        //when
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        // then
        Vector2d finalPosition1 = map.getAnimals().get(0).getPosition();
        Vector2d finalPosition2 = map.getAnimals().get(1).getPosition();

        assertTrue(finalPosition1.follows(new Vector2d(0, 0)) && finalPosition1.precedes(new Vector2d(4, 4)));
        assertTrue(finalPosition2.follows(new Vector2d(0, 0)) && finalPosition2.precedes(new Vector2d(4, 4)));
    }

    @Test
    void animalHasCorrectOrientation() {
        //given
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        WorldMap map = new RectangularMap(5, 5);

        //when
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();
        List<MapDirection> finalOrientations = Arrays.asList(map.getAnimals().get(0).getOrientation(), map.getAnimals().get(1).getOrientation());
        List<MapDirection> correctOrientations = Arrays.asList(MapDirection.NORTH, MapDirection.SOUTH);

        // then
        assertTrue(finalOrientations.size() == correctOrientations.size() && finalOrientations.containsAll(correctOrientations) && correctOrientations.containsAll(finalOrientations));
    }

    @Test
    void animalMovesToCorrectPositions() {
        //given
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "r", "f", "r", "f", "f", "f", "f"});
        WorldMap map = new RectangularMap(5, 5);
        try {
            map.place(new Animal());
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        }
        //when
        List<Animal> animals = map.getAnimals();

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
            assertEquals(animals.get(0).getPosition(), correctPositions.get(i)); //czy przemieszcza się na właściwe pozycje
            assertEquals(correctInterpretations.get(i), directions.get(i)); //czy dane wejściowe podane jako tablica łańcuchów znaków są poprawnie interpretowane.
        }

    }

    @Test
    void animalDoesNotMoveToOccupiedPosition() {
        //given
        WorldMap map = new RectangularMap(5, 5);
        Animal sheep = new Animal();
        Animal sloth = new Animal(new Vector2d(2, 3));

        try {
            map.place(sheep);
            map.place(sloth);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        }

        //when
        map.move(sheep, MoveDirection.FORWARD);
        map.move(sloth, MoveDirection.BACKWARD);

        // then
        assertEquals(new Vector2d(2, 2), sheep.getPosition());
        assertEquals(new Vector2d(2, 3), sloth.getPosition());
    }
}