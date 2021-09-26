package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.LongStream;

public class CompletableFutureAsync {

    public static void main(String[] args) {
        // addByForkJoinPool();
        // addByExecutorService();
    }

    public static void addByForkJoinPool() {

        CompletableFuture<?> addFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println("future1 : " + LongStream.rangeClosed(0, 1_000_000_000L)
                .reduce(0, Long::sum));
        });

        CompletableFuture<?> addFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println("future2 : " + LongStream.rangeClosed(0, 500_000_000L)
                .reduce(0, Long::sum));
        });

        CompletableFuture<?> addFuture3 = CompletableFuture.runAsync(() -> {
            System.out.println("future3 : " + LongStream.rangeClosed(0, 700_000_000L)
                .reduce(0, Long::sum));
        });

        CompletableFuture<?> addFuture4 = CompletableFuture.runAsync(() -> {
            System.out.println("future4 : " + LongStream.rangeClosed(0, 200_000_000L)
                .reduce(0, Long::sum));
        });

        CompletableFuture completableFutures = CompletableFuture.allOf(addFuture1, addFuture2, addFuture3, addFuture4);
        completableFutures.join();
    }

    public static void addByExecutorService() {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<?> addFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println("future1 : " + LongStream.rangeClosed(0, 1_000_000_000L)
                .reduce(0, Long::sum));
        }, executorService);

        CompletableFuture<?> addFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println("future2 : " + LongStream.rangeClosed(0, 500_000_000L)
                .reduce(0, Long::sum));
        }, executorService);

        CompletableFuture<?> addFuture3 = CompletableFuture.runAsync(() -> {
            System.out.println("future3 : " + LongStream.rangeClosed(0, 700_000_000L)
                .reduce(0, Long::sum));
        }, executorService);

        CompletableFuture<?> addFuture4 = CompletableFuture.runAsync(() -> {
            System.out.println("future4 : " + LongStream.rangeClosed(0, 200_000_000L)
                .reduce(0, Long::sum));
        }, executorService);

        CompletableFuture completableFutures = CompletableFuture.allOf(addFuture1, addFuture2, addFuture3, addFuture4);
        completableFutures.join();
        executorService.shutdown();
    }
}
