package ru.masolria.hwr.in;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;


class UserTest {

    @Test
     void getPersonalAccount() {
        User user = new User("ACCT123", "password");
        Assertions.assertEquals("ACCT123", user.getPersonalAccount());
    }

    @Test
     void getPassword() {
        User user = new User("ACCT123", "password");
        Assertions.assertEquals("password", user.getPassword());
    }
    @Test
    void getMeterReadings(){
        User user = new User("ACCT123", "password");
        MeterReadings meterReadings = user.getMeterReadings();
        Calendar prev = Calendar.getInstance();
        prev.set(2023,11,1);
        meterReadings.addReading(5, 5, 5, prev);
//        Calendar curr = Calendar.getInstance();
//        curr.set(2024,0,1);
//        meterReadings.addReading(6, 6,6,curr);
        Assertions.assertEquals(meterReadings,user.getMeterReadings());


    }


}