package agh.ics.oop;

public class World {
    public static void main(String[] args) {

        System.out.println("Start");
        run(args);
        System.out.println("Stop");
    }

    public static void run(String[] args) {
        for (String arg : args) {
            String move = switch (arg) {
                case "f" -> "zwierzak idzie do przodu";
                case "b" -> "zwierzak idzie do tylu";
                case "l" -> "zwierzak skreca w lewo";
                case "r" -> "zwierzak skreca w prawo";
                default -> null;
            };

            System.out.println(move);
        }
    }
}
