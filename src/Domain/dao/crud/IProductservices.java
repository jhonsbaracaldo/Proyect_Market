package Domain.dao.crud;


import java.sql.SQLException;

public interface IProductservices  {

    void add();
    void remove() throws SQLException;

    void update();

    void Searchid();
    void view() throws SQLException;
}
