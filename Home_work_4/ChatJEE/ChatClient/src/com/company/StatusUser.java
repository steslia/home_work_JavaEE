package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

public class StatusUser {
    private Gson gson;
    private String name;
    private Date lastAct;
    private boolean status = false;
    private int calc;

    public StatusUser(String name) {
        this.name = name;
    }

    public synchronized void test() throws IOException {
        URL url = new URL(Utils.getURL() + "/get?from=" + calc);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        try (InputStream is = http.getInputStream()) {
            gson = new GsonBuilder().create();
            byte[] buf = requestBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);

            JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
            if (list != null) {
                for (Message m : list.getList()) {
                    if (m.getFrom().equals(name)) {
                        name = m.getFrom();
                        lastAct = m.getDate();
                        isAction();
                    }
                    calc++;
                }
            }
        }
    }

    //Таймер на  минут
    private void isAction() {
        Date now = new Date();
        status = (now.getTime() - lastAct.getTime()) <= 300000;
    }

    @Override
    public String toString() {
        Set<String> setUser = UserClient.getUserName();
        for (String str : setUser){
            if (str.equals(name)) {
                String actS = status ? "Online" : "Offline";
                return "Имя: " + name + " Статус: " + actS;
            }
        }
        return "Такого пользователя нет";
    }

    //Метод для считывания JSON в массив
    private byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
