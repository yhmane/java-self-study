package chapter4.item16;

public class PrivatePoint {

    public static void main(String[] args) {

        Point2 p2 = new Point2(12, 34);
        p2.setX(123);
        System.out.println(p2.getX());
    }
}

class Point2 {
    private double x;
    private double y;

    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
}

