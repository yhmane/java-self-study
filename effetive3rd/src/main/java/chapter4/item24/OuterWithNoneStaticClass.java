package chapter4.item24;

public class OuterWithNoneStaticClass {

    private final String name;

    public OuterWithNoneStaticClass(String name) {
        this.name = name;
    }

    public String getName() {
        NoneStaticClass nonStaticClass = new NoneStaticClass("noneStatic-class : ");
        return nonStaticClass.getNameWithOuter();
    }

    private class NoneStaticClass {
        private final String noneStaticName;

        public NoneStaticClass(String noneStaticName) {
            this.noneStaticName = noneStaticName;
        }

        public String getNameWithOuter() {
            return noneStaticName + OuterWithNoneStaticClass.this.name;
        }
    }
}
