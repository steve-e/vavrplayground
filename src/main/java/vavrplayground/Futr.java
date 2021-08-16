package vavrplayground;

import io.vavr.CheckedFunction0;
import io.vavr.concurrent.Future;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class Futr {

    static CountDownLatch latch = new CountDownLatch(1);
    static final  AtomicReference<String> atomicReference = new AtomicReference<>();
    static volatile String stringHolder = null;

    public static void main(String[] args) throws InterruptedException {
        Future<String> future = Future.of((CheckedFunction0<String>) () -> {
            Thread.sleep(1000);
            return LocalDateTime.now().toString();
        });
        future.forEach(System.out::println);

        Future<String> mapped = future.map(d -> "completed at " + d);

        System.out.println("mapped completed? "+mapped.isCompleted());

        String v = mapped.get();
        System.out.println("returned value "+v);
    }
}
