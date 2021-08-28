package stream;

import static java.util.stream.Collectors.reducing;

import java.util.Arrays;
import java.util.List;

public class Reduce {

    public static void main(String[] args) {
        Dish hamburger = new Dish("hamburger", 500);
        Dish ramyun = new Dish("ramyun", 200);
        Dish frenchFries = new Dish("frenchFries", 400);

        List<Dish> menu = Arrays.asList(hamburger, ramyun, frenchFries);
        Integer totalCalories = menu.stream()
            .collect(reducing(0, Dish::getCalory, Integer::sum));

        System.out.println(totalCalories);
    }

    static class Dish {
        private String name;
        private int calory;

        public Dish(String name, int calory) {
            this.name = name;
            this.calory = calory;
        }

        public int getCalory() {
            return calory;
        }
    }
}