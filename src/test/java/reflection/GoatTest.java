package reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GoatTest {

    @DisplayName("클래스 name 찾기, getClass")
    @Test
    public void classNameTest() {
        Object goat = new Goat();
        Class<?> clazz = goat.getClass();

        assertAll(
            () -> assertThat(clazz.getSimpleName()).isEqualTo("Goat"),
            () -> assertThat(clazz.getName()).isEqualTo("reflection.Goat"),
            () -> assertThat(clazz.getCanonicalName()).isEqualTo("reflection.Goat")
        );
    }

    @DisplayName("클래스 name 찾기, forName")
    @Test
    public void classNameTest2() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("reflection.Goat");

        assertAll(
            () -> assertThat(clazz.getSimpleName()).isEqualTo("Goat"),
            () -> assertThat(clazz.getName()).isEqualTo("reflection.Goat"),
            () -> assertThat(clazz.getCanonicalName()).isEqualTo("reflection.Goat")
        );
    }

    @DisplayName("클래스 constructor 찾기")
    @Test
    public void constructorTest() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("reflection.Goat");
        Constructor constructor = clazz.getDeclaredConstructor();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor con : constructors) {
            System.out.println("Goat constructors : " + con);
        }

        assertAll(
            () -> assertThat(constructor.getName()).isEqualTo("reflection.Goat"),
            () -> assertThat(constructors.length).isEqualTo(2)
        );
    }

    @DisplayName("클래스 method 찾기")
    @Test
    public void methodTest() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("reflection.Animal");
        Method[] methods = clazz.getDeclaredMethods();
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods) {
            methodNames.add(method.getName());
        }

        assertAll(
            () -> assertThat(methods.length).isEqualTo(4),
            () -> assertThat(methodNames).containsAll(List.of("getName", "getSound", "getCATEGORY", "setCATEGORY"))
        );
    }

    @DisplayName("클래스 method 호출")
    @Test
    public void methodTest2() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("reflection.Goat");
        Method publicMethod = clazz.getDeclaredMethod("eats");
        Method privateMethod = clazz.getDeclaredMethod("test", int.class);
        privateMethod.setAccessible(true);

        assertAll(
            () -> assertThat(publicMethod.invoke(new Goat())).isEqualTo("grass"),
            () -> assertThat(privateMethod.invoke(new Goat(), 10)).isEqualTo(100)
        );
    }

    @DisplayName("클래스 field 찾기")
    @Test
    public void fieldTest() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName("reflection.Animal");
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        Field field = clazz.getDeclaredField("name");

        assertAll(
            () -> assertThat(fields.length).isEqualTo(2),
            () -> assertThat(fieldNames).containsAll(List.of("name", "CATEGORY")),
            () -> assertThat(field.getType()).isEqualTo(String.class)
        );
    }
}
