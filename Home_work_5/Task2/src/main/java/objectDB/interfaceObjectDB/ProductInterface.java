package objectDB.interfaceObjectDB;

public interface ProductInterface extends ObjectDB {
    int getCode();
    String getName();
    int getCount();
    double getPrice();
}
