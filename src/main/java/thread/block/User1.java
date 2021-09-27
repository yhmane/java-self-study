package thread.block;

public class User1 extends Thread {

    private Calculator calculator;

    public void setCalculator(Calculator calculator) {
        this.setName("User1"); // set thread name
        this.calculator = calculator;
    }

    public void run() {
        calculator.setMemory(100);
    }
}
