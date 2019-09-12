import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Подключаемся к бд
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();

        try {

            Group group1 = new Group(126);
            Group group2 = new Group(228);
            Group group3 = new Group(426);
            Student student;

            //Начало транзакции, без транзакции не начнеться запить
            em.getTransaction().begin();

            try {
                //Добавляем группы в бд, но еще пока изменения не сохранились
                em.persist(group1);
                em.persist(group2);
                em.persist(group3);

                //Добавления студентов с разными группами
                for (int i = 0; i < 5; i++) {
                    student = new Student("name " + i, "surname " + i, group1);
                    em.persist(student);
                }

                for (int i = 0; i < 7; i++) {
                    student = new Student("name " + i, "surname " + i, group2);
                    em.persist(student);
                }

                for (int i = 0; i < 15; i++) {
                    student = new Student("name " + i, "surname " + i, group3);
                    em.persist(student);
                }

                //Сохраняем изменения
                em.getTransaction().commit();
            } catch (Exception e) {

                //Если произошла ошибка, при записии и сохранении, делаем откат транзакции до начала
                em.getTransaction().rollback();
            }


            //Запрос HQL для получения всей таблицы и указываем тип объекта, каждую сточку считывает как объект
            Query querySt = em.createQuery("FROM Student ", Student.class);

            Query queryGr = em.createQuery("FROM Group");

            //Считанные данные с таблиц приводим в спосок
            List<Student> studentList = querySt.getResultList();
            List<Group> studentGrop = queryGr.getResultList();

            //Подсчитываем количество, человек в группе и выводим
            for (Group group : studentGrop) {
                int count = 0;
                Group groupValue = group;

                for (Student studen : studentList) {
                    if (studen.getGroup().equals(groupValue)) {
                        count++;
                    }
                }

                System.out.println("В " + groupValue.getNumber() + " группе " + count + " студентов");
            }
        } finally {

            em.close();
            emf.close();
        }


    }
}
