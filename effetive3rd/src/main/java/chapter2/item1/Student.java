package chapter2.item1;

public class Student {

    private String name;
    private int age;
    private int grade;

    public Student() {
    }

    // 첫번째, 이름을 가질수 있다
    public static Student studentWithNameAge(String name, int age) {
        Student student = new Student();
        student.name = name;
        student.age = age;
        return student;
    }
}
