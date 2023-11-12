package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class MapDirectionTest {

    @Test
    void nextDirection() {
        //given
        MapDirection dirSouth = MapDirection.SOUTH;
        MapDirection dirEast = MapDirection.EAST;
        MapDirection dirWest = MapDirection.WEST;
        MapDirection dirNorth = MapDirection.NORTH;

        //when
        MapDirection dirSouth2 = dirSouth.next();
        MapDirection dirEast2 = dirEast.next();
        MapDirection dirWest2 = dirWest.next();
        MapDirection dirNorth2 = dirNorth.next();

        //then
        assertSame(MapDirection.WEST, dirSouth2);
        assertSame(MapDirection.SOUTH, dirEast2);
        assertSame(MapDirection.NORTH, dirWest2);
        assertSame(MapDirection.EAST, dirNorth2);
    }

    @Test
    void prevDirection() {
        //given
        MapDirection dirSouth = MapDirection.SOUTH;
        MapDirection dirEast = MapDirection.EAST;
        MapDirection dirWest = MapDirection.WEST;
        MapDirection dirNorth = MapDirection.NORTH;

        //when
        MapDirection dirSouth2 = dirSouth.previous();
        MapDirection dirEast2 = dirEast.previous();
        MapDirection dirWest2 = dirWest.previous();
        MapDirection dirNorth2 = dirNorth.previous();

        //then
        assertSame(MapDirection.EAST, dirSouth2);
        assertSame(MapDirection.NORTH, dirEast2);
        assertSame(MapDirection.SOUTH, dirWest2);
        assertSame(MapDirection.WEST, dirNorth2);
    }

}