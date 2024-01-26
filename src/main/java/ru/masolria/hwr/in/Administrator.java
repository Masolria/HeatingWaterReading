package ru.masolria.hwr.in;

import java.util.HashSet;

public class Administrator {
    static final String password = "AdminPassword";
    boolean checkPassword(String pswd){
        if (pswd.equals(password)) return true;
        return false;
    }
    public void printAllReadings(HashSet<User> users){
        for (User user : users) {
            System.out.println(user.getPersonalAccount() + ":\n");
            user.printReadings();
        }
    }
}
