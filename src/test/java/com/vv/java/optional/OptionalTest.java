package com.vv.java.optional;

import org.junit.Test;

import javax.swing.text.html.Option;

import static  org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTest {
    @Test
    public void whenCreateEmptyOptional() {
        Optional<String> name = Optional.empty();
        assertFalse(name.isPresent());
    }

    @Test
    public void givenNonNull_createNonNull() {
        Optional<String> name = Optional.of("Sachin");
        assertTrue(name.isPresent());
        assertEquals(name.get(), "Sachin");
    }

    @Test(expected = NullPointerException.class)
    public void giveNull_CreateNonNullable() {
        Optional<String> name = Optional.of(null);
    }

    @Test
    public void givenNull_createNullable() {
        // if the actual value is nullable. Won't throw null pointer exception.
        Optional<String> name = Optional.ofNullable(null);
        assertFalse(name.isPresent());

        name = Optional.ofNullable("sam");
        assertTrue(name.isPresent());
    }

    @Test
    public void givenOptional_whenIfPresentWorks() {
        Optional<String> name = Optional.ofNullable("sam");
        name.ifPresent((n) -> {
            assertEquals(n, "sam");
        });

        name = Optional.ofNullable(null);
        name.ifPresent((n)->{
            // dead code.
            assertFalse(true);
        });
    }

    @Test
    public void whenOrElseWorks() {
        String nullValue = null;
        String name = Optional.ofNullable(nullValue).orElse("default_name");
        assertEquals(name, "default_name");
    }

    private String getDefaultValue() {
        System.out.println("Getting default value");
        return "DEFAULT_VALUE";
    }

    // check for null.
    @Test
    public void whenOrElseWithMethodCall_withNull() {
        String nullName = null;
        String value = Optional.ofNullable(nullName).orElse(getDefaultValue());
        assertEquals(value, "DEFAULT_VALUE");
    }

    @Test
    public void whenOrElseWithMethodCall_withNonNull() {
        String name = "sam";
        // getDefaultValue() will get called in both null/non-null cases of name.
        String value = Optional.ofNullable(name).orElse(getDefaultValue());
        assertEquals(value, "sam");
    }
    //

    @Test
    public void whenOrElseGetWithMethodCall_withNonNull() {
        String name = "sam";
        // No invocation of getDefaultValue().
        String value = Optional.ofNullable(name).orElseGet(this::getDefaultValue);
        assertEquals(value, "sam");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOrElseThrowMethodCall_withNull() {
        String value = null;
        // throw Exception if the value is null.
        String name = Optional.ofNullable(value).orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void whenGet_withNonNullable() {
        Optional<String> name = Optional.ofNullable("sam");
        assertEquals(name.get(), "sam");
    }

    @Test (expected = NoSuchElementException.class)
    public void whenGet_withNull() {
        String value = null;
        Optional<String> name = Optional.ofNullable(value);
        name.get();
    }

    // filter
    class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    @Test
    public void userValidation_withoutOptional() {
        User user = new User ("User", "user@user.com");
        assertTrue(isValidUser("User", "user@user.com", user));
    }

    @Test
    public void userValidation_withOptionalFilters() {
        User user = new User ("User", "user@user.com");
        assertTrue(Optional.ofNullable(user)
                .filter((u) -> u.name.equals("User"))
                .filter((u) -> u.email.equals("user@user.com"))
                .isPresent());
    }

    private boolean isValidUser(String userName, String email, User user) {
        if (user != null && user.email.equals(email) && user.name.equals(userName)) {
            return true;
        }
        return false;
    }

    // Map
    @Test
    public void givenOptional_withMap() {
        String email = " mail@mail.com ";
        assertEquals(13, getEmailLength(email));

        // With Optional
        int length = Optional.ofNullable(email)
                .map(String::trim)
                .map(String::length)
                .orElse(0);
        assertEquals(13, length);
    }

    private int getEmailLength(String emailId) {
        if(emailId == null || emailId.isEmpty()) {
            return 0;
        } else {
            String trimmed = emailId.trim();
            return trimmed.length();
        }
    }

    // User management
    private Optional<User> getUserDetails(final Long userId) {
        return Optional.ofNullable(userId).map((id) -> {
            return new User("", "");
        });
    }

    @Test
    public void testUserDeatails_withOptional() {
        Long userId = 1233L;
        Optional<User> user = getUserDetails(userId);
        assertNotNull(user.get());
    }


}
