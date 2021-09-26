package thread;

public class MyRunnable implements Runnable {

    private int index;

    public MyRunnable(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println(this.index + " thread start");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(this.index + " thread end");
    }
}
