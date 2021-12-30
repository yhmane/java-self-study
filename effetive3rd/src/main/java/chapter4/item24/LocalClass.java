package chapter4.item24;

public class LocalClass {

    public void hello() {
        class LocalExample {
            private String name;

            public LocalExample(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        }

        LocalExample localExample = new LocalExample("윤호호");
        System.out.println(localExample.getName());
    }
}
