package streams;

import bean.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

/**
 * Created by vasudvis on 6/7/2017.
 */
public class BeginStreams {

    public static void main(String[] args) {
        List<User> users = Arrays.asList(new User("email1@mail.com","name1",1), new User("email2@mail.com","name2",2),
                new User("email3@mail.com","name3",3), new User("email4@mail.com","name4",4));

        // finding the average.
        OptionalDouble value = users.stream().mapToInt(user -> user.getAge()).average();
        System.out.println(value);

        // finding the average using method reference.
        OptionalDouble value2 = users.stream().mapToInt(User::getAge).average();
        System.out.println(value2);

        // print value from 1 to 100
        IntStream.range(1, 100 + 1).forEach(System.out::println);

        // print value from 1 to 100
        IntStream.rangeClosed(1, 100).forEach(System.out::println);

        // printing all the characters.
        "this is it".chars().forEach(ch -> System.out.println((char)ch));

    }

}
