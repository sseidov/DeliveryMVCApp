package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static app.dao.DishDAO.*;

public class Orders_dishesDAO {

    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
