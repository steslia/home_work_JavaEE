package objectDB;

import objectDB.interfaceObjectDB.ProductInterface;

public class Product implements ProductInterface {
    private int code;
    private String name;
    private int count;
    private double price;

    public Product(int code, String name, int count, double price) {
        this.code = code;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
