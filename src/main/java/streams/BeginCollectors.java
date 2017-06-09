package streams;

import bean.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by vasudvis on 6/9/2017.
 */
public class BeginCollectors {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("one", "two", "three", "four");
        List<User> users = Arrays.asList(new User("email1@mail.com","name1",1), new User("email2@mail.com","name2",2),
                new User("email3@mail.com","name3",3), new User("email4@mail.com","name4",4));

        // simple joining.
        System.out.println(strings.stream().collect(Collectors.joining()));

        // joining with comma
        System.out.println(strings.stream().collect(Collectors.joining(", ")));

        // collect to a map
        Map<String, String> nameEmailMap = users.stream().collect(Collectors.toMap(User::getName, User::getEmail));
        nameEmailMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " : "+ e.getValue()));

        //users.stream().map


    }
}
