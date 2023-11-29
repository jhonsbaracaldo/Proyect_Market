package Vista;

import Domain.Dto.Menu;
import Domain.dao.crud.UserValidation;
import Services.Data_base.ConectionBD;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {


        Menu menu = new Menu();
        menu.menuUser();

    }
}


