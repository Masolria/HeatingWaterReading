package ru.masolria.hwr.in;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {
    InputStream storeIN = System.in;
    PrintStream storeOut = System.out;
    ByteArrayInputStream in;
    ByteArrayOutputStream out;

    @Test
    void useConsole_register() {//зарегистрировались
        String inRegister = "2\n123\npassword\n0\n";
        in = new ByteArrayInputStream(inRegister.getBytes());
        System.setIn(in);

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Console.useConsole();
        String consoleOutput = out.toString();
        assertTrue(consoleOutput.contains("Вас приветствует приложение учета показаний счетчиков."));
        assertTrue(consoleOutput.contains("Введите номер лицевого счета"));
        assertTrue(consoleOutput.contains("Придумайте пароль"));
        assertTrue(consoleOutput.contains("Личный кабинет 123"));
    }

    @Test
    void useConsole_authorizeAddReading() {//зашли после 1-го теста в созданный аккаунт, внесли показания, вышли.
        //выполняется верно после первого теста
        String inAddReading = "1\n123\npassword\n4\n10.5\n20.3\n30.7\n0\n";
        in = new ByteArrayInputStream(inAddReading.getBytes());
        System.setIn(in);

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Console.useConsole();

        // Проверка вывода в консоль
        String consoleOutput = out.toString();
        assertTrue(consoleOutput.contains("Введите ваш номер лицевого счета"));
        assertTrue(consoleOutput.contains("Введите ваш номер лицевого счета"));
        assertTrue(consoleOutput.contains("Введите показания холодной воды"));
        assertTrue(consoleOutput.contains("Введите показания горячей воды"));
        assertTrue(consoleOutput.contains("Введите показания отопления"));
        assertTrue(consoleOutput.contains("Показания успешно записаны"));
    }

    @Test
    void useConsole_authorizeFalse() {//попытались авторизоваться с неизвестным лицевым счетом и паролем.


        String inAddReading = "1\nrandom\npassword\n0\n";
        in = new ByteArrayInputStream(inAddReading.getBytes());
        System.setIn(in);

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Console.useConsole();

        // Проверка вывода в консоль
        String consoleOutput = out.toString();
        assertTrue(consoleOutput.contains("Введите ваш номер лицевого счета"));
        assertTrue(consoleOutput.contains("Введите ваш пароль"));
        assertTrue(consoleOutput.contains("учетная запись с данным лицевым счетом и паролем не найдена."));

    }

    @Test
    void useConsole_admin() {//зашли как админ, посмотрели показания пользователей, вышли.


        String inAddReading = "3\nAdminPassword\n5\n0\n";
        in = new ByteArrayInputStream(inAddReading.getBytes());
        System.setIn(in);

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Console.useConsole();

        // Проверка вывода в консоль
        String consoleOutput = out.toString();
        assertTrue(consoleOutput.contains("Вы зашли как администратор"));
        assertTrue(consoleOutput.contains("Введите пароль"));
    }


}