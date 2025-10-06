// Combined version: GeometricObject, Circle, Rectangle, and Test class in one file

abstract class GeometricObject implements Comparable<GeometricObject> {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    // Constructors
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

    // Abstract methods for subclasses
    public abstract double getArea();
    public abstract double getPerimeter();

    // Implement Comparable
    @Override
    public int compareTo(GeometricObject o) {
        if (getArea() > o.getArea())
            return 1;
        else if (getArea() < o.getArea())
            return -1;
        else
            return 0;
    }

    // Static max method
    public static GeometricObject max(GeometricObject o1, GeometricObject o2) {
        return (o1.compareTo(o2) >= 0) ? o1 : o2;
    }
}

// Circle subclass
class Circle extends GeometricObject {
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

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

    @Override
    public String toString() {
        return "Circle: radius = " + radius + ", area = " + getArea();
    }
}

// Rectangle subclass
class Rectangle extends GeometricObject {
    private double width;
    private double height;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle: width = " + width + ", height = " + height + ", area = " + getArea();
    }
}

// Test class (must be public)
public class TestGeometricObject {
    public static void main(String[] args) {
        // Create two Circle objects
        Circle c1 = new Circle(5);
        Circle c2 = new Circle(7);

        // Create two Rectangle objects
        Rectangle r1 = new Rectangle(4, 8);
        Rectangle r2 = new Rectangle(5, 6);

        // Compare circles
        GeometricObject largerCircle = GeometricObject.max(c1, c2);
        System.out.println("Larger circle: " + largerCircle);

        // Compare rectangles
        GeometricObject largerRectangle = GeometricObject.max(r1, r2);
        System.out.println("Larger rectangle: " + largerRectangle);
    }
}
