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


    public User(String personalAccount, String password) {
        this.personalAccount = personalAccount;
        this.password = password;
        meterReadings = new MeterReadings();
    }

    public void printReadings(){
        System.out.println("Показания счетчиков "+ personalAccount +":");
        System.out.println(meterReadings.toString());
    }

    public String getPersonalAccount() {
        return personalAccount;
    }

    public String getPassword() {
        return password;
    }

    public MeterReadings getMeterReadings() {
        return meterReadings;
    }
}
