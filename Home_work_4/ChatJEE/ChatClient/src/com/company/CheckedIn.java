package com.company;

import java.util.Map;

//Класс для проверки входа пользователя
public class CheckedIn {
    private String name;
    private String password;


    public boolean checkIn(String nameUser, String passwordUser) {
        Map<String, String> mapUser = UserClient.getMapUser();

        name = nameUser;
        password = passwordUser;

        for (Map.Entry entry : mapUser.entrySet()) {
            if (entry.getKey().equals(name) && entry.getValue().equals(password)) {
                System.out.println("Вход успешный");
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }
}
