package ru.masolria.hwr.in;

import java.util.HashSet;

/**
 * Класс администратор, предоставляет функционал для задач админа,
 * такие как отображение показаний счетчиков всех пользователей.
 */
public class Administrator {
    /** Хранит в себе пароль для доступа к функционалу администратора.
     */
    static private final String password = "AdminPassword";

    /**
     * Сравнивает предоставленный пароль с паролем администратора
     * @param passwd сравниваемый пароль с паролем администратора
     * @return true - если пароли совпадают, false - если нет.
     */
    boolean checkPassword(String passwd){
        return passwd.equals(password);
    }

    /**
     * Выводит в консоль показания счетчиков всех пользователей
     * с указанием номера лицевого счета пользователя.
     * @param users набор пользователей для вывода в консоль
     */
    public void printAllReadings(HashSet<User> users){
        for (User user : users) {
            System.out.println(user.getPersonalAccount() + ":\n");
            user.printReadings();
        }
    }
}
