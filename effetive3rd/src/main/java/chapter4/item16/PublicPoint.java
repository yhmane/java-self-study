package chapter4.item16;

public class PublicPoint {

    public static void main(String[] args) {
        Point p = new Point();
        p.x = 123;
        System.out.println(p.x);

    }

}

class Point {
    public double x;
    public double y;
}
