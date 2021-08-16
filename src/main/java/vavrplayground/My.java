package vavrplayground;

import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;

import java.math.BigInteger;
import java.time.LocalDate;

@Patterns
class My {

    @Unapply
    static Tuple2<String, String> Employee(Example.Employee Employee) {
        return Tuple.of(Employee.getName(), Employee.getId());
    }

    @Unapply
    static Tuple3<Integer, Integer, Integer> LocalDate(LocalDate date) {
        return Tuple.of(
                date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    @Unapply
    static <T> Tuple1<T> Optional(java.util.Optional<T> optional) {
        return Tuple.of(optional.orElse(null));
    }

    @Unapply
    static Tuple1<String> BigInteger(BigInteger bi) {
        return Tuple.of(bi.toString());
    }
}
