package chapter2.item3.staticfactory;

public class Elvis {

    private static final Elvis INSTANCE = new Elvis();

    private Elvis() { }
    public static Elvis getInstance() { return INSTANCE; }

    public void leaveTheBuilding() {
        System.out.println("leave the building");
    }

    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
        elvis.leaveTheBuilding();
    }
}
