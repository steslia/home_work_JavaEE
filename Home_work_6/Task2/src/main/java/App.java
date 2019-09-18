import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    private static RestaurantMenu restaurantMenu;

    //Добавление в БД
    public static void addDB(Scanner scanner, EntityManager em) {
        String str;
        String name;
        double price;
        int weight;
        boolean sale;

        System.out.println("Введите название блюда: ");
        name = scanner.nextLine();

        System.out.println("Цена: ");
        price = scanner.nextDouble();

        System.out.println("Вес блюда: ");
        weight = scanner.nextInt();

        System.out.println("Наличие скидки введите yes/no");
        while (true) {
            str = scanner.nextLine();

            if ("yes".equals(str)) {
                sale = true;
                break;
            }

            if ("no".equals(str)) {
                sale = false;
                break;
            }
        }


        try {

            //Создание объкта на основе заполненных данных
            restaurantMenu = new RestaurantMenu(name, price, weight, sale);

            //Начало транзакции для записи
            em.getTransaction().begin();

            try {
                //Добавляем объект
                em.persist(restaurantMenu);

                //Записываем в БД
                em.getTransaction().commit();

            } catch (Exception e) {
                //Откат в случаи ошибки
                em.getTransaction().rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //Вывод с таблицы только со скидкой
    public static void showSale(EntityManager em) {

        System.out.println("\nБлюда со скидкой\n");

        Query query = em.createQuery("FROM RestaurantMenu WHERE sale = true", RestaurantMenu.class);

        List<RestaurantMenu> list = query.getResultList();

        for (RestaurantMenu menu : list) {
            System.out.println(menu);
        }

    }

    //Вывод всех данных с таблиц
    public static void showDB(EntityManager em) {

        System.out.println("\nБлюда со скидкой\n");

        Query query = em.createQuery("FROM RestaurantMenu", RestaurantMenu.class);

        List<RestaurantMenu> list = query.getResultList();

        for (RestaurantMenu menu : list) {
            System.out.println(menu);
        }

    }

    //Сортируем по цене
    public static void sortPrise(EntityManager em) {

        System.out.println("\nБлюда от-до\n");

        //Получаем все данные с таблицы
        Query query = em.createQuery("FROM RestaurantMenu", RestaurantMenu.class);

        //Преобразуем в лист
        List<RestaurantMenu> list = query.getResultList();

        //Делаем стрим, переопределяем копаратор для сортировки как нам нужно(по возрастанию цены) и выводим
        list.stream()
                .sorted(Comparator.comparingDouble(RestaurantMenu::getPrice))
                .forEach(System.out::println);

    }

    //Выборка блюд до 1кг
    public static void selectionSetDishes(Scanner scanner, EntityManager em) {
        List<RestaurantMenu> restaurantMenus = new ArrayList<>();
        String name;
        int count = 0;

        //Получаем все данные с таблицы
        Query query = em.createQuery("FROM RestaurantMenu", RestaurantMenu.class);
        List<RestaurantMenu> list = query.getResultList();

        System.out.println("Чтобы выйти отправте пустую строку\n");
        while (count <= 1000) {
            System.out.println("Название блюда");
            name = scanner.nextLine();

            //Завершаем работу если пустая срока
            if (name.isEmpty())
                break;

            for (RestaurantMenu menu : list) {

                if (name.equals(menu.getName())) {

                    count += menu.getWeight();
                    //добавляем в локальный список
                    if (count <= 1000) {
                        restaurantMenus.add(menu);
                    }

                }
            }
        }

        if (count > 1000) {
            System.out.println("Вы перевысили лимит, блюда что вы смогли выбрать на вес 1кг");
            restaurantMenus.forEach(System.out::println);
        } else {
            System.out.println("Блюда что вы смогли выбрать на вес 1кг");
            System.out.println("Общий вес: " + count + " грм");
            restaurantMenus.forEach(System.out::println);
        }
    }

    //Генерируем данные в БД 
    public static void generateBD(EntityManager em) {

        em.getTransaction().begin();

        try {
            for (int i = 1; i <= 5; i++) {
                em.persist(new RestaurantMenu("name" + i, 600 - (100 * i), 100 * i, i % 2 == 0));
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }


}
