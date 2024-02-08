package ru.masolria.hwr.in;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class MeterReadingsTest {
    MeterReadings meterReadings;

    @BeforeEach
    void setUp() {
        meterReadings = new MeterReadings();
    }


    @Test
    void addReading_lessZero() {

        boolean isAdd = meterReadings.addReading(-1, -1, -1, Calendar.getInstance());
        Assertions.assertFalse(isAdd);//проверяет, что при значениях <0 выдает false
    }

    @Test
    void addReading_monthsTrue() {//проверяет обычное поведение

        Calendar previousMonth = Calendar.getInstance();
        previousMonth.set(2023, 10, 3);//хранит время 3 ноя 2023
        boolean isAddPrevious = meterReadings.addReading(5, 5, 5, previousMonth);
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.set(2023, 11, 3);//хранит время 3 дек 2023
        boolean isAddCurrent = meterReadings.addReading(6, 6, 6, currentMonth);
        Assertions.assertAll(
                () -> assertTrue(isAddPrevious),
                () -> assertTrue(isAddCurrent)
        );

    }

    @Test
    void addReading_monthsFalse() {//Проверяет, что нельзя добавить в один месяц показания дважды.
        Calendar previousMonth = Calendar.getInstance();
        previousMonth.set(2024, 1, 4);//хранит время 4 янв 2020
        boolean isAddPrevious = meterReadings.addReading(5, 5, 5, previousMonth);
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.set(2024, 1, 17);//хранит время 17 янв 2020
        boolean isAddCurrent = meterReadings.addReading(6, 6, 6, currentMonth);
        Assertions.assertFalse(isAddCurrent);
    }

    @Test
    void addReading_readingLessFalse() {//Проверяет, что нельзя добавлять значения меньше предыдущих.
        Calendar previousMonth = Calendar.getInstance();
        previousMonth.set(2023, 10, 3);//хранит время 3 ноя 2023
        boolean isAddPrevious = meterReadings.addReading(5, 5, 5, previousMonth);
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.set(2023, 11, 3);//хранит время 3 дек 2023
        boolean isAddCurrent = meterReadings.addReading(4, 4, 4, currentMonth);
        Assertions.assertFalse(isAddCurrent);
    }

    @Test
    public void testToString() {
        MeterReadings meterReadings = new MeterReadings();
        Calendar time1 = Calendar.getInstance();
        time1.set(2023, Calendar.JANUARY, 1);
        meterReadings.addReading(100, 50, 200, time1);

        Calendar time2 = Calendar.getInstance();
        time2.set(2023, Calendar.FEBRUARY, 1);
        meterReadings.addReading(120, 55, 220, time2);

        String expected = "01-2023:\n\tхол. вода 100.0\n\tгоряч. вода 50.0\n\tотопление 200.0" +
                "\n02-2023:\n\tхол. вода 120.0\n\tгоряч. вода 55.0\n\tотопление 220.0\n";

        Assertions.assertEquals(expected, meterReadings.toString());
    }

    @Test
    void monthPassed_true() {
        Calendar time1 = Calendar.getInstance();
        time1.set(2023, Calendar.DECEMBER, 1);
        meterReadings.addReading(100, 50, 200, time1);//добавляет запись с временем1
        Assertions.assertTrue(meterReadings.monthPassed());//с дек 2023 на момент янв 2024 прошел месяц

    }
    @Test
    void monthPassed_false() {
        Calendar time1 = Calendar.getInstance();
        meterReadings.addReading(100, 50, 200, time1);//добавляет запись с нынешним временем
        Assertions.assertFalse(meterReadings.monthPassed());

    }

}