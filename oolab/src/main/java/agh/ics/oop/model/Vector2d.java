package agh.ics.oop.model;

import java.util.Objects;

public class Vector2d {

    private final int x;

    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(%d,%d)".formatted(x, y);
    }

    public boolean precedes(Vector2d other) {
        return other.getX() <= this.x && other.getY() <= this.y;
    }

    public boolean follows(Vector2d other) {
        return other.x >= this.x && other.y >= this.y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other) {
        int upperRightX;
        int upperRightY;
        upperRightX = other.x > this.x ? other.x : this.x;
        upperRightY = other.y > this.y ? other.y : this.y;

        return new Vector2d(upperRightX, upperRightY);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int lowerLeftX;
        int lowerLeftY;
        lowerLeftX = other.x < this.x ? other.x : this.x;
        lowerLeftY = other.y < this.y ? other.y : this.y;

        return new Vector2d(lowerLeftX, lowerLeftY);
    }

    public Vector2d opposite(Vector2d other) {
        return new Vector2d(-other.x, -other.y);
    }

    public boolean equals(Object other) {
        return this == other;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
