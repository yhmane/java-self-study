package chapter4.item23.tag;

class Figure {

    enum Shape {
        RECTANGLE, CIRCLE
    }

    // 태그 필드
    final Shape shape;

    // RECTANGLE 용 필드
    double length;
    double width;

    // CIRCLE 용 필드
    double radius;

    // RECTANGLE
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    // CIRCLE
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    double area() {
        switch(shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }
}
