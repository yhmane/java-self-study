package thread.state;

public class TargetThread extends Thread {

    public void run() {
        for (long i = 0; i < 1000000000; i++) {

        }

        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (long i = 0; i < 1000000000; i++) {

        }
    }
}
