package entity;

import objectDB.interfaceObjectDB.ObjectDB;

import java.sql.SQLException;

//Описываем определенный функционал
public interface WorkDB {
    void addDB(ObjectDB objectDB) throws SQLException;
    void addList() throws SQLException;
    void createTable() throws SQLException;
}
