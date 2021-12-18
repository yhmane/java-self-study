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
        if (this.age > o.age) {
            return 1;
        } else if (this.age == o.age) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "StudentComparable{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
