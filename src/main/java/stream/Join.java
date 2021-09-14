package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Join {

    public static void main(String[] args) {
        String[] menus = {"pork", "beef", "chicken", "french fries", "season fruit", "pizza", "prawns", "salmon"};

        String collect = Arrays.asList(menus)
            .stream()
            .collect(Collectors.joining());
        System.out.println(collect);

        List<Dish> dish = Arrays.asList(menus)
            .stream()
            .map(Dish::new)
            .collect(Collectors.toList());
        String collect1 = dish.stream()
            .map(Dish::getName)
            .collect(Collectors.joining(", "));
        System.out.println(collect1);

    }

    static class Dish {
        private String name;

        public Dish(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
