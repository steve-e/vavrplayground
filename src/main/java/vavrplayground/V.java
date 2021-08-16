package vavrplayground;

import io.vavr.Function1;
import io.vavr.Lazy;
import io.vavr.Value;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public class V {
    static void log(Object o) {
        System.out.println(o);
    }
    public static void main(String[] args) throws InterruptedException {
        lazy();
        trying();
        opty();
        Either<String, Boolean> either = Either.left("false");
        Either<Boolean, Boolean> lm = either.bimap(Boolean::valueOf, Function.identity());
        log(lm);
    }

    private static void opty() {
        Option<List<String>> strings = Option.of("123")
                .map(s -> Arrays.asList(s.substring(0, 1), s.substring(1, 2), s.substring(2, 3)));
        strings.forEach(System.out::println);
        Option<Integer> integerOption = Try.of(() -> 1 / 0).toOption();
        Integer integer = integerOption.getOrElse(666);
        log(integer);
        Option<Object> n = Option.of(null);
        log(" Option of null "+n);

        try {
            Optional.of(null);
        } catch (Exception e) {
            log("NPE for Optional.of(null)");
        }
        Optional<Object> e = Optional.ofNullable(null);
        log(e);
    }

    private static void trying() {
        Try<String[]> listing = Try.of(() -> new File(".")).map(File::list);
        listing.map(Arrays::toString).forEach(System.out::println);
        Try<String> afterLast = listing.map(dir -> dir[dir.length]);
        log(afterLast.isFailure());
        String s = afterLast.recover(t -> "eRror").get();
        log(s);
    }

    private static void lazy() throws InterruptedException {
        Value<Long> s = Lazy.of(System::currentTimeMillis);
        long l = System.currentTimeMillis();
        Thread.sleep(100L);
        boolean lazyIsLater = s.exists(v -> l < v);
        log(lazyIsLater);
    }
}
