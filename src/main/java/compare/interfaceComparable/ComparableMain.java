package compare.interfaceComparable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ComparableMain {

    public static void main(String[] args) {
        StudentComparable s1 = new StudentComparable("영희", 15);
        StudentComparable s2 = new StudentComparable("철수", 17);
        StudentComparable s3 = new StudentComparable("길동", 15);
        StudentComparable s4 = new StudentComparable("순자", 16);

        List list = Arrays.asList(s1, s2, s3, s4);


        Collections.sort(list);
        list.forEach(System.out::println);

    }
}
