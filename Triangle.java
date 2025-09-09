public class Triangle extends GeometricObject {
    private double side1;
    private double side2;
    private double side3;

    // Default constructor
    public Triangle() {
        this.side1 = 1.0;
        this.side2 = 1.0;
        this.side3 = 1.0;
    }

    // Constructor with sides and color
    public Triangle(double side1, double side2, double side3, String color, boolean filled) {
        super(color, filled); // Call the superclass constructor
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // Getters and Setters for the sides
    public double getSide1() {
        return side1;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public double getSide3() {
        return side3;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }

    // Implement the abstract methods
    @Override
    public double getArea() {
        // Use Heron's formula to calculate area
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    // toString() to display the triangle's properties
    @Override
    public String toString() {
        return "Triangle with sides: " + side1 + ", " + side2 + ", " + side3 +
               ", Color: " + getColor() + ", Filled: " + isFilled();
    }
}
