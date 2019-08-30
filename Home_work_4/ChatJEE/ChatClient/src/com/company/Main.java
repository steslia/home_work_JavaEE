package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Scanner scanner = new Scanner(System.in);
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Чтобы войти нужно ввести один из логинов и пароль: [test 123; admin 123; user 123]");
        while (true) {
            System.out.print("1.Ввойти \n2.Проверить статус пользователя \n3.Список пользователей \n");
            String number = scanner.readLine();
            if (number.equals("1")) {

                System.out.println("Введите логин:");
                String name = scanner.readLine();

                System.out.println("Введите пароль:");
                String password = scanner.readLine();

                //Проверка входа
                CheckedIn checkedIn = new CheckedIn();
                boolean flag = checkedIn.checkIn(name, password);
                if (flag) {
                    try {
                        String login = checkedIn.getName();

                        Thread th = new Thread(new GetThread(login));
                        th.setDaemon(true);
                        th.start();

                        Thread.sleep(1000);
                        //Меню для выбора действий внутри залогиненого пользователя
                        while (true) {

                            System.out.print("\n1.Отправить сообщение в общий чат " +
                                    "\n2.Отправить приватное сообщение \n3.Выход \n");
                            String menu = scanner.readLine();

                            if (menu.equals("1")) {
                                System.out.println("\nДля выхода отправте пустую строку");
                                System.out.println("Введите сообщение для отправки: ");
                                while (true) {
                                    String text = scanner.readLine();
                                    if (text.isEmpty()) break;

                                    Message m = new Message(login, text);
                                    int res = m.send(Utils.getURL() + "/add");

                                    if (res != 200) { // 200 OK
                                        System.out.println("HTTP error occured: " + res);
                                        return;
                                    }
                                }
                            }

                            if (menu.equals("2")) {
                                //Отправления по логину
                                System.out.println("Введите имя пользователя для отправки:");
                                String fromName = scanner.readLine();
                                System.out.println("Введите сообщение: ");
                                while (true) {

                                    String text = scanner.readLine();
                                    if (text.isEmpty()) break;

                                    Message m = new Message(login, fromName, text);
                                    int res = m.send(Utils.getURL() + "/add");

                                    if (res != 200) { // 200 OK
                                        System.out.println("HTTP error occured: " + res);
                                        return;
                                    }

                                }
                            }
                            if (menu.equals("3"))
                                break;
                        }

                        //Завершаем поток
                        th.interrupt();
                    } catch (IOException ignored) {

                    }
                } else {
                    System.out.println("Вход не удался");
                    System.out.println();
                }
            }
            if (number.equals("2")) {
                System.out.println("Введите имя пользователся для проверки статуса \n");
                String nameUserStatus = scanner.readLine();
                StatusUser statusUser = new StatusUser(nameUserStatus);
                statusUser.test();
                System.out.println(statusUser);
                System.out.println();
            }

            if (number.equals("3")) {
                Map<String, String> mapUser = UserClient.getMapUser();
                for (Map.Entry entry : mapUser.entrySet()) {
                    String name = (String) entry.getKey();
                    StatusUser statusUser = new StatusUser(name);
                    statusUser.test();
                    System.out.println(statusUser);
                }
                System.out.println();
            }
        }
    }
}
