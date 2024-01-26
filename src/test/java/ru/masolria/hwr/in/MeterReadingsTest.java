package ru.masolria.hwr.in;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MeterReadingsTest {
    MeterReadings meterReadings;
    @BeforeEach
    void setUp(){
        meterReadings = new MeterReadings();
    }


    @Test
    void addReading_lessZero(){

        boolean isAdd =meterReadings.addReading(-1,-1,-1, Calendar.getInstance() );
        Assertions.assertFalse(isAdd);
    }
    @Test
    void addReading_monthsTrue(){
        Calendar previousMonth = Calendar.getInstance();
        previousMonth.setTime(new Date(1578171661));//хранит время 4 янв 2020
        boolean isAddPrevious =meterReadings.addReading(5,5,5,previousMonth);
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.setTime(new Date(1580850061));//хранит время 4 фев 2020
        Assertions.assertArrayEquals();
    }


}