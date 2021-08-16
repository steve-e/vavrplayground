package vavrplayground;

import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.function.BiFunction;


public class Tuples {
    public static void main(String[] args) {
        Tuple2<Integer, String> t = Tuple.of(1, "two");
        System.out.println(t);
        System.out.println(t._1);
        Tuple2<Integer, String> t2 = t.map2(String::toUpperCase);
        System.out.println(t2);
        Tuple2<Integer, String> t3 = t.map(i -> i + 1, s -> new StringBuffer(s).reverse().toString());
        System.out.println(t3);
        Tuple2<String, Integer> t4 = t.map(new BiFunction<Integer, String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> apply(Integer integer, String s) {
                return Tuple.of(s.substring(0,1),s.length()-integer);
            }
        });
        System.out.println(t4);
        String s1 = t.apply((i, s) -> s + i);
        System.out.println(s1);
    }
}
