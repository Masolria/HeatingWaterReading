package ru.masolria.hwr.in;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleUserInteractionTest {
    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = Console.UserInteraction.register("testAcc", "testPassword");
    }

    @Test
    void isAvailableRegister_true() {
        assertTrue(Console.UserInteraction.isAvailableRegister("addNewAcc"));
    }

    @Test
    void isAvailableRegister_WhenNotAvailable() {//такой пользователь уже есть.
        assertFalse(Console.UserInteraction.isAvailableRegister("testAcc"));
    }

    @Test
    void register() {
        User newUser = Console.UserInteraction.register("newAcc", "newPassword");
        assertNotNull(newUser);
        assertEquals("newAcc", newUser.getPersonalAccount());
    }

    @Test
    void authorize_nonRegistered() {
        User nonRegisteredUser = Console.UserInteraction.authorize("invalidAcc", "invalidPassword");
        assertNull(nonRegisteredUser);
    }

    @Test
    void availableAddReadings_WhenAvailable() {
        assertTrue(Console.UserInteraction.availableAddReadings(testUser));
    }

    @Test
    void availableAddReadings_WhenNotAvailable(){
        testUser.getMeterReadings().addReading(4,4,4, Calendar.getInstance());
        assertFalse(Console.UserInteraction.availableAddReadings(testUser));
    }
}