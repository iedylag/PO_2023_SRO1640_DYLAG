package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updateCount = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        ++updateCount;
        System.out.println("Update: " + message);
        System.out.println(worldMap.toString());
        System.out.printf("Number of updates so far: %d\n%n", updateCount);
    }
}
