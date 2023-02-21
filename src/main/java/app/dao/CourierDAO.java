package app.dao;

import app.models.Courier;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.dao.DishDAO.*;

@Component
public class CourierDAO {

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

    public List<Courier> listOfCouriers(){
        List<Courier> couriers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select firstname, lastname from couriers ");
            while (resultSet.next()){
                Courier courier = new Courier();
                courier.setFirstname(resultSet.getString("firstname"));
                courier.setLastname(resultSet.getString("lastname"));
                couriers.add(courier);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return couriers;
    }

    public Courier getCourierByPhonenumber(String phonenumber) {
        Courier courier = new Courier();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select phonenumber, firstname, lastname, password, deliverytype from couriers" +
                    " where phonenumber = '" + phonenumber + "'");
            resultSet.next();

            courier.setPhonenumber(phonenumber);
            courier.setFirstname(resultSet.getString("firstname"));
            courier.setLastname(resultSet.getString("lastname"));
            courier.setPassword(resultSet.getString("password"));
            courier.setDeliverytype(resultSet.getString("deliverytype"));
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courier;
    }

    public void registrationOfNewCourier(Courier courier) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into couriers values" +
                    " (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, courier.getPhonenumber());
            preparedStatement.setString(2, courier.getPassword());
            preparedStatement.setString(3, courier.getFirstname());
            preparedStatement.setString(4, courier.getLastname());
            preparedStatement.setString(5, courier.getDeliverytype());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCourier(String phonenumber, Courier courier) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update couriers set deliverytype = ?" +
                    "where phonenumber = '" + phonenumber + "'" );
            preparedStatement.setString(1, courier.getDeliverytype());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(String phonenumber){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from couriers where phonenumber = ?");
            preparedStatement.setString(1, phonenumber);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
