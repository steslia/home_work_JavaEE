package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserClient {
    private static Map<String, String> mapUser = new HashMap<>();
    private static UserClient userClient = new UserClient();

    private UserClient(){
        mapUser.put("test","123");
        mapUser.put("admin","123");
        mapUser.put("user","123");
    }

    public static Set<String> getUserName(){
        return mapUser.keySet();
    }

    public static UserClient getInstance(){
        return userClient;
    }

    public static void addMapUser(String name, String password){
        mapUser.put(name,password);
    }

    public static Map getMapUser(){
        return mapUser;
    }
}
