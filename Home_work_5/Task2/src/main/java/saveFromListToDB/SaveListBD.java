package saveFromListToDB;

import objectDB.Order;
import objectDB.Person;
import objectDB.Product;

import java.util.LinkedList;
import java.util.List;

//Класс сингелтон, который хранит разные списки
public class SaveListBD {
    private static final SaveListBD SAVE_LIST_BD = new SaveListBD();
    private static final int LIMIT = 100;
    private final List<Person> listPerson = new LinkedList<>();
    private final List<Product> listProduct = new LinkedList<>();
    private final List<Order> listOrder = new LinkedList<>();

    private SaveListBD() {
    }

    public static SaveListBD getInstance(){
        return SAVE_LIST_BD;
    }

    public synchronized void addListPerson(Person person){
        if (listPerson.size() + 1 == LIMIT){
            listPerson.remove(0);
        }

        listPerson.add(person);
    }

    public synchronized void addListProduct(Product product){
        if (listProduct.size() + 1 == LIMIT){
            listProduct.remove(0);
        }

        listProduct.add(product);
    }

    public synchronized void addListOrder(Order order){
        if (listOrder.size() + 1 == LIMIT){
            listOrder.remove(0);
        }

        listOrder.add(order);
    }

    public synchronized void clearListPerson(){
        listPerson.removeAll(listPerson);
    }

    public synchronized void clearListProduct(){
        listProduct.removeAll(listProduct);
    }

    public synchronized void clearListOrder(){
        listOrder.removeAll(listOrder);
    }

    public List<Person> getListPerson(){
        return listPerson;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public List<Order> getListOrder() {
        return listOrder;
    }
}
