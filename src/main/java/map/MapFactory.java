package map;

import java.util.Map;

public class MapFactory {

    public static void of() {
        Map<String, Integer> ageOfFriends
            = Map.of("Raul", 30, "Terry", 28, "John", 26);

        System.out.println("==================================");
        System.out.println("of");
        System.out.println(ageOfFriends);
        System.out.println("==================================\n");
    }

    public static void entry() {
        Map<String, Integer> ageOfFriends = Map.ofEntries(
            Map.entry("Raul", 30),
            Map.entry("Terry", 28),
            Map.entry("John", 26));

        System.out.println("==================================");
        System.out.println("entry");
        System.out.println(ageOfFriends);
        System.out.println("==================================\n");
    }

    public static void forEach() {
        Map<String, Integer> ageOfFriends
            = Map.of("Raul", 30, "Terry", 28, "John", 26);

        System.out.println("==================================");
        System.out.println("for");
        for (Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old");
        }
        System.out.println("==================================\n");

        System.out.println("==================================");
        System.out.println("forEach biconsumer");
        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));
        System.out.println("==================================\n");
    }

    public static void getOrDefault() {
        Map<String, Integer> ageOfFriends = Map.ofEntries(
            Map.entry("Raul", 30),
            Map.entry("Terry", 28),
            Map.entry("John", 26));

        System.out.println("==================================");
        System.out.println("getOrDefault");
        System.out.println(ageOfFriends.getOrDefault("Raul", 30));
        System.out.println(ageOfFriends.getOrDefault("Toby", 31));
        System.out.println("==================================\n");
    }

    public static void main(String[] args) {
        of();
        entry();
        forEach();
        getOrDefault();
    }
}
