package ru.masolria.hwr.in;

public class User {
    private String personalAccount;
    private String password;
    private MeterReadings meterReadings ;

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
