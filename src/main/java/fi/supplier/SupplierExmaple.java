package fi.supplier;

import java.util.function.Supplier;

import fi.supplier.model.Student;

public class SupplierExmaple {

    public static void main(String[] args) {
        Supplier<String> supplier= () -> "hello world";
        System.out.println(supplier.get());

        Supplier<Student> studentSupplier = () -> new Student("황윤호", 20);
        System.out.println(studentSupplier.get());
    }
}
