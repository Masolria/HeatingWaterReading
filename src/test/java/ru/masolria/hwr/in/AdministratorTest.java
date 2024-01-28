package ru.masolria.hwr.in;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.HashSet;

class AdministratorTest {
    private Administrator admin;
    @BeforeEach
    void setUp(){
        admin = new Administrator();
    }
    @Test
    void checkPassword_True(){
        boolean password = admin.checkPassword("AdminPassword");
        Assertions.assertTrue(password);
    }
    @Test
    void checkPassword_False(){
        boolean password = admin.checkPassword("somePassword123");
        Assertions.assertFalse(password);
    }
    @Test
    void printAllReadings(){
        HashSet<User> users = new HashSet<>();
        User user = new User("123","qwerty");
        users.add(user);
        Calendar time =Calendar.getInstance();
        time.set(2024,Calendar.JANUARY,7);
        user.getMeterReadings().addReading(3,4,5, Calendar.getInstance());
        PrintStream standardOut = System.out;
        ByteArrayOutputStream captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
        admin.printAllReadings(users);
        String expected = "Показания счетчиков 123:\n01-2024:\n\tхол. вода 3.0\n\tгоряч. вода 4.0\n\tотопление 5.0";
        Assertions.assertEquals(expected,captor.toString().replaceAll("\r", "").trim());
        System.setOut(standardOut);
    }
    @Test
    void printAllReadings_voidStr(){
        HashSet<User> users = new HashSet<>();
        PrintStream standardOut = System.out;
        ByteArrayOutputStream captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
        admin.printAllReadings(users);
        Assertions.assertEquals("",captor.toString().replaceAll("\r", "").trim());
        System.setOut(standardOut);
    }
}