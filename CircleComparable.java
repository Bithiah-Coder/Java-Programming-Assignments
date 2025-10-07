// CircleComparable.java
// Liang, Chapter 13 - Programming Exercise 13.9
// Author: Bithiah Lloyd
// Description: Circle class extends GeometricObject and implements Comparable interface

// ---------- Superclass ----------
abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    // Constructor
    protected GeometricObject() {
        dateCreated = new java.util.Date();
    }

    protected GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    // Getters and setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color + " and filled: " + filled;
    }

    // Abstract methods
    public abstract double getArea();

    public abstract double getPerimeter();
}

// ---------- Subclass ----------
public class CircleComparable extends GeometricObject implements Comparable<CircleComparable> {
    private double radius;

    // Constructors
    public CircleComparable() {
    }

    public CircleComparable(double radius) {
        this.radius = radius;
    }

    public CircleComparable(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    // Getters and setters
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getDiameter() {
        return 2 * radius;
    }

    // Implement compareTo method
    @Override
    public int compareTo(CircleComparable other) {
        if (this.radius > other.radius)
            return 1;
        else if (this.radius < other.radius)
            return -1;
        else
            return 0;
    }

    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CircleComparable) {
            CircleComparable other = (CircleComparable) obj;
            return this.radius == other.radius;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Circle: radius = " + radius;
    }

    // ---------- Test Program ----------
    public static void main(String[] args) {
        CircleComparable c1 = new CircleComparable(5);
        CircleComparable c2 = new CircleComparable(3);
        CircleComparable c3 = new CircleComparable(5);

        System.out.println("Circle 1 area: " + c1.getArea());
        System.out.println("Circle 2 area: " + c2.getArea());

        System.out.println("\nComparing c1 and c2: " + c1.compareTo(c2));
        System.out.println("Comparing c1 and c3: " + c1.compareTo(c3));

        System.out.println("\nIs c1 equal to c3? " + c1.equals(c3));
        System.out.println("Is c1 equal to c2? " + c1.equals(c2));
    }
}
