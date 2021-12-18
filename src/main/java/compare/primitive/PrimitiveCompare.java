package compare.primitive;

public class PrimitiveCompare {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;

        if (a > b) {
            System.out.println("a가 b 보다 큰 숫자입니다");
        } else if (a == b) {
            System.out.println("a와 b는 같은 숫자입니다");
        } else {
            System.out.println("a는 b보다 작습니다.");
        }
    }
}
