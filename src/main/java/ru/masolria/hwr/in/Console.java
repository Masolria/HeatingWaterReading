package ru.masolria.hwr.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashSet;

public class Console {
    public static void main(String[] args) {
        useConsole();

    }

    public static void useConsole() {




        try (BufferedReader bufferedConsole = new BufferedReader(new InputStreamReader(System.in))) {
            String readStr = "";
            boolean insideLoop = true;
            boolean isAdmin = false;
            boolean isAuthorized = false;

            User user = null;
            while (insideLoop) {
                if (isAuthorized) {
                    System.out.println("Личный кабинет " + user.getPersonalAccount());
                    System.out.println("Доступные команды:\n" +
                            "4-добавить показания счетчиков за этот месяц" + "\n" +
                            "5-посмотреть историю показаний" + "\n" +
                            "6-выход из учетной записи\n" +
                            "0-выход из системы");
                    switch (bufferedConsole.readLine()) {
                        case "4" -> {
                            if (UserInteraction.availableAddReadings(user)) {
                                System.out.println("Введите показания холодной воды");
                                float cold = Float.parseFloat(bufferedConsole.readLine());
                                System.out.println("Введите показания горячей воды");
                                float hot = Float.parseFloat(bufferedConsole.readLine());
                                System.out.println("Введите показания отопления");
                                float heat = Float.parseFloat(bufferedConsole.readLine());
                                if (user.getMeterReadings().addReading(cold, hot, heat, Calendar.getInstance())) {
                                    System.out.println("Показания успешно записаны");
                                } else {
                                    System.out.println("Ошибка в подаче показаний." +
                                            "Показания за текущий месяц не могут быть меньше показаний за прошлый месяц.\n" +
                                            "Показаниями могут быть только положительные числа");
                                }
                            } else {
                                System.out.println("В данном месяце вы уже добавляли показания счетчиков");
                            }

                        }
                        case "5" -> {
                            user.printReadings();
                        }
                        case "6" -> {
                            isAuthorized = false;
                        }
                        case "0" -> {
                            insideLoop = false;
                        }
                        default -> System.out.println("некорректный ввод");
                    }

                } else if (isAdmin) {
                    System.out.println("Доступные команды\n" +
                            "7-посмотреть показания счетчиков всех пользователей\n" +
                            "6-выход из учетной записи\n" +
                            "0-выход из системы");
                    switch (bufferedConsole.readLine()) {
                        case "7" ->{
                            new Administrator().printAllReadings(UserInteraction.users);
                        }
                        case "6" ->{
                            isAdmin = false;
                        }
                        case "0" ->{
                            insideLoop =false;
                        }
                    }


                } else {
                    System.out.println("Вас приветствует приложение учета показаний счетчиков." +
                            "\n\n" + "Доступные команды:\n" +
                            "1-авторизация" + "\n" +
                            "2-регистрация" + "\n" +
                            "3-войти как администратор\n" +
                            "0-выход из системы");
                    switch (bufferedConsole.readLine()) {
                        case "1" -> {
                            System.out.println("Введите ваш номер лицевого счета");
                            String usr_str = bufferedConsole.readLine();
                            System.out.println("Введите ваш пароль");
                            String usr_psw = bufferedConsole.readLine();
                            user = UserInteraction.authorize(usr_str, usr_psw);
                            if (user != null) {
                                isAuthorized = true;
                            }
                        }
                        case "2" -> {
                            System.out.println("Введите номер лицевого счета");
                            String personalAcc = bufferedConsole.readLine();
                            if (UserInteraction.isAvailableRegister(personalAcc)) {
                                System.out.println("Придумайте пароль");
                                String password = bufferedConsole.readLine();
                                user = UserInteraction.register(personalAcc,password);
                                isAuthorized = true;
                            }

                        }
                        case "3" -> {
                            System.out.println("Введите пароль");
                            if(new Administrator().checkPassword(bufferedConsole.readLine())) {
                                System.out.println("Вы зашли как администратор");
                                isAdmin = true;
                            }else {
                                System.out.println("Вы неверно ввели пароль");
                            }

                        }
                        case "0" -> {
                            insideLoop = false;
                        }
                        default -> {
                            System.out.println("Некорректный ввод!");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class UserInteraction {
        static HashSet<User> users = new HashSet<>();

        public static boolean isAvailableRegister(String personalAcc) {
            for (User user : users) {
                if (user.getPersonalAccount().equals(personalAcc))
                    return false;
            }
            return true;
        }

        public static User register(String personalAcc, String password) {
            User newUser = new User(personalAcc, password);
            users.add(newUser);
            return newUser;
        }

        public static User authorize(String personalAcc, String password) {
            for (User user : users) {
                if (user.getPersonalAccount().equals(personalAcc) && user.getPassword().equals(password)) {
                    System.out.println("Вы успешно авторизованы");
                    return user;
                }
            }
            System.out.println("учетная запись с данным лицевым счетом и паролем не найдена.");
            return null;
        }

        public static boolean availableAddReadings(User user) {
            return user.getMeterReadings().availableAddReading();
        }

    }
}
