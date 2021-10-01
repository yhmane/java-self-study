package thread.state;

public class ThreadStateExample {

    public static void main(String[] args) {
        PrintThread printThread = new PrintThread(new TargetThread());
        printThread.start();
    }
}

//    타겟 스레드 상태 NEW
//    타겟 스레드 상태 RUNNABLE
//    타겟 스레드 상태 RUNNABLE
//    타겟 스레드 상태 TIMED_WAITING
//    타겟 스레드 상태 TIMED_WAITING
//    타겟 스레드 상태 TIMED_WAITING
//    타겟 스레드 상태 RUNNABLE
//    타겟 스레드 상태 TERMINATED