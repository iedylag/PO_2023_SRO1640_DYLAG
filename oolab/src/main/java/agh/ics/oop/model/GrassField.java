package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;


public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> trawki = new HashMap<>();
    private Vector2d lowBoundary = LOWER_LEFT;
    private Vector2d upBoundary;

    public GrassField(int countTrawki) {
        Vector2d grassBoundary = new Vector2d((int) Math.sqrt(countTrawki * 10), (int) Math.sqrt(countTrawki * 10));

        int positionsCount = 0;
        while (positionsCount < countTrawki) {
            int X = (int) (Math.random() * (grassBoundary.getX() + 1)); //zakladam, ze np. przy n=10, obszar jest od (0,0) do (10,10) wlacznie
            int Y = (int) (Math.random() * (grassBoundary.getY() + 1));
            Vector2d grassField = new Vector2d(X, Y);
            if (!trawki.containsKey(grassField)) {
                trawki.put(grassField, new Grass(grassField));
                positionsCount++;
            }
        }
        upBoundary = grassBoundary;
        /* uprosciłam, bo wydaje mi sie, ze malo wydajne bylo porownywanie po kazdym dodaniu trawy,
        szczegolnie, ze sama sobie wczesniej wymyslilam, ze mapa nie ma byc wieksza niz pole trawy */
    }

    public Map<Vector2d, Grass> getTrawki() {
        return trawki;
    }

    @Override
    public Collection<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(super.getElements());
        elements.addAll(trawki.values());
        return elements;
    }

    @Override
    public boolean place(Animal animal) {
        boolean placed = super.place(animal);
        Vector2d animalPosition = animal.getPosition();

        /* moze byc poza if bo jesli nie mozna postawic zwierzatka to oznacza, ze jest tam juz inne zwierzatko wiec pozycja ta byla juz rozwazana wczesniej */
        lowBoundary = animalPosition.lowerLeft(lowBoundary);
        upBoundary = animalPosition.upperRight(upBoundary);

        return placed;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        super.move(animal, direction);
        Vector2d newPosition = animal.getPosition();

        /* tutaj ta sama logika, wiec poza if */
        lowBoundary = newPosition.lowerLeft(lowBoundary);
        upBoundary = newPosition.upperRight(upBoundary);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || trawki.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement element = super.objectAt(position);
        if (element != null) {
            return element;
        }
        return trawki.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowBoundary, upBoundary);
    }
}
