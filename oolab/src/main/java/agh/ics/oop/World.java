package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {

        System.out.println("Start");
        MoveDirection[] directions = OptionsParser.parse(args);
        run(directions);
        System.out.println("Stop");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }

    public static void run(MoveDirection[] directions) {
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
