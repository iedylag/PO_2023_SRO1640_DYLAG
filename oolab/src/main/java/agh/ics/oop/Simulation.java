package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Simulation {
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public Simulation(List<MoveDirection> directions, WorldMap map) {
        this.directions = directions;
        this.map = map;
    }

    public void run() {
        Map<Vector2d, Animal> animals = ((RectangularMap) map).getAnimals();
        List<Animal> animalsValue = new ArrayList<>(animals.values());  //chyba zle, bo run() miala dzialac na mapie

        for (int i = 0; i < directions.size(); i++) {
            System.out.println(directions.get(i));
            int animalIndex = i % animals.size();
            Animal animalMoving = animalsValue.get(animalIndex);  //może uzyć objectAt?
            map.move(animalMoving, directions.get(i));
            System.out.println(map);
        }
    }
}
