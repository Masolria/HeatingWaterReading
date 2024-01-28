package ru.masolria.hwr.in;
/**
 * Класс User представляет пользователя системы.
 */
public class User {
    /**Лицевой счет*/
    private String personalAccount;
    /**Пароль пользователя для входа*/
    private String password;
    /**Поле, хранящее в себе все показания счетчиков по месяцам*/
    private MeterReadings meterReadings;
    /**
     * Конструктор класса User. Создает нового пользователя с указанным лицевым счетом и паролем
     * и инициализирует показания счетчиков.
     *
     * @param personalAccount Лицевой счёт пользователя
     * @param password Пароль пользователя
     */
    public User(String personalAccount, String password) {
        this.personalAccount = personalAccount;
        this.password = password;
        meterReadings = new MeterReadings();
    }
    /**
     * Выводит на экран показания счетчиков для данного пользователя.
     */
    public void printReadings(){
        System.out.println("Показания счетчиков "+ personalAccount +":");
        System.out.println(meterReadings.toString());
    }
    /**
     * Возвращает лицевой счёт пользователя.
     *
     * @return Лицевой счёт пользователя
     */
    public String getPersonalAccount() {
        return personalAccount;
    }
    /**
     * Возвращает пароль пользователя.
     *
     * @return Пароль пользователя
     */
    public String getPassword() {
        return password;
    }
    /**
     * Возвращает объект, представляющий показания счетчиков данного пользователя.
     *
     * @return Объект, представляющий показания счетчиков
     */
    public MeterReadings getMeterReadings() {
        return meterReadings;
    }

}
