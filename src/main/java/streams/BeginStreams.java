package streams;

import bean.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Created by vasudvis on 6/7/2017.
 */
public class BeginStreams {

    public static void main(String[] args) {
        List<User> users = Arrays.asList(new User("email1@mail.com","name1",1), new User("email2@mail.com","name2",2),
                new User("email3@mail.com","name3",3), new User("email4@mail.com","name4",4));

        // finding teh average
        OptionalDouble value = users.stream().mapToInt(user -> user.getAge()).average();
        System.out.println(value);
        
    }

}
