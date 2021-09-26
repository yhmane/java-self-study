package thread;

public class MyThread extends Thread {

    private int index;

    public MyThread(int index) {
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
