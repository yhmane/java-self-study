package thread;

public class MyThreadMain {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread myThread = new MyThread(i);
            myThread.start();
        }

        for (int i = 10; i < 20; i++) {
            Runnable runnable = new MyRunnable(i);
            Thread runnableThread = new Thread(runnable);
            runnableThread.start();
        }

        new Thread() {
            @Override
            public void run() {
                System.out.println("anonymous class thread");
            }
        }.start();

        new Thread(() -> System.out.println("lambda thread")).start();
    }
}
