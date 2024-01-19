package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        grassFieldGenerate(grassCount);
    }

    private void grassFieldGenerate(int grassCount) {
        Vector2d grassBoundary = new Vector2d((int) Math.sqrt(grassCount * 10), (int) Math.sqrt(grassCount * 10));
        int maxWidth = grassBoundary.getX();
        int maxHeight = grassBoundary.getY();

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d lowLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (WorldElement element : getElements()) {
            upRightCorner = element.getPosition().upperRight(upRightCorner);
            lowLeftCorner = element.getPosition().lowerLeft(lowLeftCorner);
        }

        return new Boundary(lowLeftCorner, upRightCorner);
    }

    @Override
    public Collection<WorldElement> getElements() {
        List<WorldElement> animalsList = new ArrayList<>(super.getElements());
        List<Grass> grassesList = new ArrayList<>(grasses.values());
        return Stream.concat(animalsList.stream(), grassesList.stream()).toList();
    }

    public int getGrassesSize() {
        return grasses.size();
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        return super.objectAt(position)
                .or(() -> Optional.ofNullable(grasses.get(position)));
    }
}
