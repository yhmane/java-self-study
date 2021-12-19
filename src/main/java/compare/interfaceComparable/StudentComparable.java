package compare.interfaceComparable;

public class StudentComparable implements Comparable<StudentComparable> {

    private String name;
    private int age;

    public StudentComparable() {
    }

    public StudentComparable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(StudentComparable o) {
       return Integer.compare(this.age, o.age);
    }

    @Override
    public String toString() {
        return "StudentComparable{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
