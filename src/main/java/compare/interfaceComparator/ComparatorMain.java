package compare.interfaceComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorMain {

    public static void main(String[] args) {
        Student s1 = new Student("영희", 15);
        Student s2 = new Student("철수", 17);
        Student s3 = new Student("길동", 15);
        Student s4 = new Student("순자", 16);

        List list = Arrays.asList(s1, s2, s3, s4);

        Collections.sort(list, Comparator.comparingInt(Student::getAge));

        list.forEach(System.out::println);
    }
}
