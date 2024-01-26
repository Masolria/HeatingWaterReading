package ru.masolria.hwr.in;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

}