import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int number;
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();

        try {

            while (true) {

                System.out.println("\n1.Заполнить меню\n2.Добавить в меню\n3.Вывести все меню" +
                        "\n4.Сортировать по цене от-доn\n5.Выбрать блюда до 1к\n6.Вывести блюда со скидкой\n");
                number = scanner.nextInt();
                scanner.nextLine();

                switch (number){
                    case 1:
                        App.generateBD(em);
                        break;

                    case 2:
                        App.addDB(scanner,em);
                        break;

                    case 3:
                        App.showDB(em);
                        break;

                    case 4:
                        App.sortPrise(em);
                        break;

                    case 5:
//                        scanner.nextLine();
                        App.selectionSetDishes(scanner,em);
                        break;

                    case 6:
                        App.showSale(em);
                        break;

                        default:
                            System.out.println("\nВы ничего не выбрали");
                }
            }

        } finally {
            scanner.close();
            em.close();
            emf.close();
        }

    }
}
