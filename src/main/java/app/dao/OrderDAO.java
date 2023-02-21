package app.dao;

import app.models.Order;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.dao.DishDAO.*;

@Component
public class OrderDAO {

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

    public List<Order> listOfCustomerOrders(String customers_phonenumber){
        List<Order> orders = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from orders where customers_phonenumber " +
                    "= '" + customers_phonenumber + "'");
            while (resultSet.next()){
                Order order = new Order();
                order.setData(resultSet.getString("deliverydate"));

                orders.add(order);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    public List<Order> listOfCourierOrders(String couriers_phonenumber){
        List<Order> orders = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from orders where couriers_phonenumber " +
                    "= '" + couriers_phonenumber + "'");
            while (resultSet.next()){
                Order order = new Order();
                order.setData(resultSet.getString("deliverydate"));

                orders.add(order);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }


}
