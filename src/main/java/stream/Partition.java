package stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import stream.Partition.Dish.DishType;


public class Partition {

    public static void main(String[] args) {
        Dish prawns = new Dish(DishType.FISH, "prawns");
        Dish salmon = new Dish(DishType.FISH, "salmon");
        Dish frenchFries = new Dish(DishType.OTHER, "frech fries");
        Dish rice = new Dish(DishType.OTHER, "rice");
        Dish fruit = new Dish(DishType.OTHER, "season fruit");
        Dish pizza = new Dish(DishType.OTHER, "pizza");
        Dish pork = new Dish(DishType.MEAT, "pork");
        Dish beef = new Dish(DishType.MEAT, "beef");
        Dish chicken = new Dish(DishType.MEAT, "chicken");

        List<Dish> menu = Arrays.asList(prawns, salmon, frenchFries, rice, fruit, pizza, pork, beef, chicken);
        Map<Boolean, List<Dish>> collect = menu.stream().collect(partitioningBy(Dish::isVegetarian));

        for (Map.Entry<Boolean, List<Dish>> entry : collect.entrySet()) {
            System.out.println(
                "key : " + entry.getKey() +
                    ", value : " + entry.getValue().stream().map(Dish::getName).collect(Collectors.joining(", "))
            );
        }
    }

    static class Dish {
        private DishType dishType;
        private String name;

        public Dish(DishType dishType, String name) {
            this.dishType = dishType;
            this.name = name;
        }

        public DishType getDishType() {
            return dishType;
        }

        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return this.dishType == DishType.OTHER ? true : false;
        }

        public enum DishType {
            MEAT, FISH, OTHER
        }
    }
}
