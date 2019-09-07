package objectDB;

import objectDB.interfaceObjectDB.OrderInterface;

public class Order implements OrderInterface {
    private int idPersonal;
    private int idCode;
    private int count;

    public Order(int idPersonal, int idCode, int count) {
        this.idPersonal = idPersonal;
        this.idCode = idCode;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getIdPerson() {
        return idPersonal;
    }

    @Override
    public int getIdCode() {
        return idCode;
    }
}
