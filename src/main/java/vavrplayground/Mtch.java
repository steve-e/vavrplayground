package vavrplayground;

import static io.vavr.API.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import static io.vavr.Patterns.*;
import static io.vavr.Predicates.instanceOf;


import io.vavr.control.Try;
public class Mtch {

    static Number addNumber(Number value) {
        return Match(value).of(
                Case($(instanceOf(BigDecimal.class)), bigDecimal -> bigDecimal.add(BigDecimal.ONE)),
                Case(MyPatterns.$BigInteger($("10")), d -> BigInteger.ZERO ),
                Case(MyPatterns.$BigInteger($()), d -> new BigInteger(d).add(BigInteger.ONE) ),
                Case($(instanceOf(Integer.class)), i -> i + 1),
                Case($(instanceOf(Float.class)), f -> f + 1.0f));
    }

    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2017, 2, 13);

        String result = Match(date).of(
                Case(MyPatterns.$LocalDate($(2016), $(3), $(13)),
                        () -> "2016-02-13"),
                Case(MyPatterns.$LocalDate($(2016), $(), $()),
                        (y, m, d) -> "month " + m + " in 2016"),
                Case(MyPatterns.$LocalDate($(), $(), $()),
                        (y, m, d) -> "month " + m + " in " + y),
                Case($(),
                        () -> "(catch all)")
        );

        System.out.println(result);

        String ss = Match(Optional.ofNullable(null)).of(
                Case(MyPatterns.$Optional($(v -> v != null)), "defined"),
                Case(MyPatterns.$Optional($(v -> v == null)), "empty")
        );
        System.out.println(ss);
        Try tRy = Try.of(() -> "trying");
        String r = Match(tRy).of(
                Case($Success($()), value -> "777"),
                Case($Failure($()), x -> "666")
        );

        System.out.println(r);

        Number bd = addNumber(new BigDecimal("999999999999999999999999999.9"));
        System.out.println(bd);

        Number bi = addNumber(BigInteger.TEN);
        System.out.println(bi);
        Number bi2 = addNumber(new BigInteger("123456789012345678901234567890"));
        System.out.println(bi2);

        Number i = addNumber(9);
        System.out.println(i);

        Number f = addNumber(9.1F);
        System.out.println(f);


    }
}
