package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {

        System.out.println("Start");
        MoveDirection[] directions = OptionsParser.parse(args);
        run(directions);
        System.out.println("Stop");
    }

    public static void run (MoveDirection[] directions) {
        for (MoveDirection direction : directions) {
            String move = switch (direction) {
                case FORWARD -> "zwierzak idzie do przodu";
                case BACKWARD -> "zwierzak idzie do tylu";
                case LEFT -> "zwierzak skreca w lewo";
                case RIGHT -> "zwierzak skreca w prawo";
            };
            System.out.println(move);
        }
    }
}
