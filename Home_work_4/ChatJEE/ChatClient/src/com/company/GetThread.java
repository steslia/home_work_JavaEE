package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson;
    private int n;
    private String login;

    public GetThread(String login) {
        gson = new GsonBuilder().create();
        this.login = login;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/get?from=" + n + "&" + "name=" + login);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                InputStream is = http.getInputStream();
                try {
                    byte[] buf = requestBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null) {

                        synchronized (this) {
                            for (Message m : list.getList()) {
                                if (m.getTo() == null) {
                                    System.out.println(m);
                                } else if (m.getTo().equals(login)) {
                                    System.out.println(m);
                                }

                                n++;
                            }
                        }
                    }
                } finally {
                    is.close();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //Метод для считывания JSON в массив
    protected static byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10340];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
