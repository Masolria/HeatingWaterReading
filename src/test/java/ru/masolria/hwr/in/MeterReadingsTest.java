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
        Assertions.assertFalse(isAdd);//проверяет, что при значениях <0 выдает false
    }
    @Test
    void addReading_monthsTrue(){
        //пока не работает
        Calendar previousMonth = Calendar.getInstance();
        previousMonth.set(2023,10,3);//хранит время 3 ноя 2023
        boolean isAddPrevious =meterReadings.addReading(5,5,5,previousMonth);
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.set(2023,11,3);//хранит время 3 дек 2023
        boolean isAddCurrent = meterReadings.addReading(6,6,6,currentMonth);
        Assertions.assertAll(
                () -> assertTrue(isAddPrevious),
                () -> assertTrue(isAddCurrent)
        );

    }
    @Test
    void addReading_monthsFalse(){//проверяет, что нельзя добавить в один месяц показания дважды.
        Calendar previousMonth = Calendar.getInstance();
        previousMonth.set(2024,0,4);//хранит время 4 янв 2020
        boolean isAddPrevious =meterReadings.addReading(5,5,5,previousMonth);
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.set(2024,0,17);//хранит время 17 янв 2020
        boolean isAddCurrent = meterReadings.addReading(6,6,6,currentMonth);
            Assertions.assertFalse(isAddCurrent);
    }
    @Test
     void addReading_readingLessFalse(){
         Calendar previousMonth = Calendar.getInstance();
         previousMonth.setTime(new Date(1578171661));//хранит время 4 янв 2020
         boolean isAddPrevious =meterReadings.addReading(5,5,5,previousMonth);
         Calendar currentMonth = Calendar.getInstance();
         currentMonth.setTime(new Date(1579208461));//хранит время 17 янв 2020
         boolean isAddCurrent = meterReadings.addReading(4,6,6,currentMonth);
         Assertions.assertFalse(isAddCurrent);
     }


}