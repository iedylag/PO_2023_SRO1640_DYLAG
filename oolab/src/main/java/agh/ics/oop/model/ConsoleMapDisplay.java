package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {

    private int updateCount = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        update();
        System.out.println("\nMap ID: " + worldMap.getId());
        System.out.println(worldMap);
        System.out.printf("Update %d: ", howManyUpdates());

        /*StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nMap ID: ").append(worldMap.getId());
        stringBuilder.append(message).append("\n");
        stringBuilder.append(worldMap);
        stringBuilder.append("Update: ").append(howManyUpdates());

        System.out.println(stringBuilder);*/
    }

    private void update() {
        updateCount++;
    }

    private int howManyUpdates() {
        return updateCount;
    }
}
