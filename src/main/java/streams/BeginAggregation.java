package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Created by vasudvis on 6/9/2017.
 */
public class BeginAggregation {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("one", "two", "three", "four");

        Optional<String> concatResult = strings.stream().reduce(String::concat);
        System.out.println(concatResult);

        String concatResultWithidentity = strings.stream().reduce(" - ", String::concat);
        System.out.println(concatResultWithidentity);

        Optional<String> customConcat = strings.stream().reduce((str, str2)-> str2.concat(str));
        System.out.println(customConcat);

        // finding sum of all even numbers from 1 to 100
        // gives wrong result becuse reduce start from 2 (by providing initial value as x)
        System.out.println( IntStream.rangeClosed(1,2).reduce((x, y )-> {
            System.out.println(x + "-" + y);if( y % 2 == 0) return x + y; else return x;}));

        // right way to find the sum of even numbers.
        System.out.println( IntStream.rangeClosed(1,2).filter(x -> (x%2 == 0)?true:false).reduce((x,y)-> x+ y));



    }
}
