package vavrplayground;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.control.Option;

import java.util.function.Function;
import java.util.function.Supplier;

public class Fs {
    public static void main(String[] args) {
        Function2<Integer, String, Boolean> f1 = (i, s) -> s.length() >= i;
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        Function1<Integer, Integer> incAndDouble = plusOne.andThen(multiplyByTwo);
        Function1<Integer, Integer> doubleAndInc = plusOne.compose(multiplyByTwo);
        Integer twelve = incAndDouble.apply(5);
        System.out.println(twelve);
        Integer eleven = doubleAndInc.apply(5);
        System.out.println(eleven);

        Supplier<Boolean> aEx = () -> {
            throw new ArithmeticException("That's an odd implementation");
        };
        Function1<Integer,Boolean> even =  i -> i % 2 == 0 || aEx.get();
        try {
            Boolean result = even.apply(2);
            System.out.println(result);
            even.apply(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Function1<Integer, Option<Boolean>> saferEven = Function1.lift(even);
        System.out.println(saferEven.apply(2));
        System.out.println(saferEven.apply(1));
        Function1<Integer, Boolean> bestEven = saferEven.andThen(Option::isDefined);
        System.out.println(bestEven.apply(2));
        System.out.println(bestEven.apply(1));


        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> inc = sum.apply(1);
        System.out.println(inc.apply(665));
        Function1<Integer, Function1<Integer, Integer>> curriedSum = sum.curried();
    }
}
