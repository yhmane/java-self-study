package thread.block;

public class User2 extends Thread {

    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.setName("User2"); // set thread name
        this.calculator = calculator;
    }

    public void run() {
        calculator.setMemory(50);
    }
}
