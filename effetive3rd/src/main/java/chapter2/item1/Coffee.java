package chapter2.item1;

public class Coffee {

    private boolean hasCaffeine;

    public static Coffee coffeeWithCaffeine(boolean hasCaffeine) {
        if (hasCaffeine) {
            return new CaffeineCoffee();
        }
        return new NoneCaffeineCoffee();
    }

    private static class NoneCaffeineCoffee extends Coffee {
        private NoneCaffeineCoffee() {
            System.out.println("NoneCaffeineCoffee");
        }
    }

    private static class CaffeineCoffee extends Coffee {
        private CaffeineCoffee() {
            System.out.println("CaffeineCoffee");
        }
    }
}