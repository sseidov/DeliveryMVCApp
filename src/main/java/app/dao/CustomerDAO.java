package app.dao;

import app.models.Customer;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.dao.DishDAO.*;

@Component
public class CustomerDAO {

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
    public List<Customer> listOfCustomersEmail(){
        List<Customer> customers = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select email from customers");
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer);
            }

            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = new Customer();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select phonenumber, firstname, lastname, email, password from customers" +
                    " where email = '" + email + "'");
            resultSet.next();

            customer.setPhonenumber(resultSet.getString("phonenumber"));
            customer.setFirstname(resultSet.getString("firstname"));
            customer.setLastname(resultSet.getString("lastname"));
            customer.setEmail(email);
            customer.setPassword(resultSet.getString("password"));
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public void registrNewCust(Customer customer) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into customers values" +
                    " (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, customer.getPhonenumber());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getFirstname());
            preparedStatement.setString(4, customer.getLastname());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCustomer(String email, Customer customer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update customers set password = ?" +
                    "where email = '" + email + "'" );
            preparedStatement.setString(1, customer.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(String email){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from customers where email = ?");
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
