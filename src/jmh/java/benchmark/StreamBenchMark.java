package benchmark;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

/**
 * ./gradlew jmh
 * Benchmark                          Mode  Cnt    Score   Error  Units
 * StreamBenchMark.iterativeSum       avgt   10    4.536 ± 0.062  ms/op
 * StreamBenchMark.parallelRangedSum  avgt   10    3.098 ± 0.715  ms/op
 * StreamBenchMark.rangedSum          avgt   10    9.064 ± 0.205  ms/op
 * StreamBenchMark.sequentialSum      avgt   10  110.349 ± 1.378  ms/op
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs={"-Xms4G", "-Xmx4G"})
public class StreamBenchMark {

    private static final long N = 10_000_000L;

    @Benchmark
    public long sequentialSum() {
        return Stream.iterate(1L, i -> i +1)
            .limit(N)
            .reduce(0L, Long::sum);
    }

    @Benchmark
    public long iterativeSum() {
        long result = 0;
        for (long i = 1L; i <= N; i++) {
            result += i;
        }
        return result;
    }

    @Benchmark
    public long rangedSum() {
        return LongStream.rangeClosed(1, N)
            .reduce(0L, Long::sum);
    }

    @Benchmark
    public long parallelRangedSum() {
        return LongStream.rangeClosed(1, N)
            .parallel()
            .reduce(0L, Long::sum);
    }
}
