package agh.ics.oop.model;

import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleMapDisplay implements MapChangeListener {

    //private final AtomicInteger updateCount = new AtomicInteger(0);
    private int updateCount = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        //update();
        updateCount++;
        System.out.println("Map ID:" + worldMap.getId());
        System.out.println("Update: " + message);
        System.out.println(worldMap);
        System.out.printf("Number of updates so far: %d\n%n", updateCount);//howManyUpdates());
    }

    /*private void update(){
        updateCount.incrementAndGet();
    }

    private int howManyUpdates(){
        return updateCount.get();
    }*/
}
