package app.dao;

import app.models.Dish;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DishDAO {

    public final static String URL = "jdbc:postgresql://localhost:5432/delivery_db";
    public final static String USERNAME = "postgres";
    public final static String PASSWORD = "root";

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

    public List<Dish> listOfDishesName(){
        List<Dish> dishes = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select name from dishes");
            while (resultSet.next()){
                Dish dish = new Dish();
                dish.setName(resultSet.getString("name"));
//                dish.setIngredients(resultSet.getString("ingredients"));
//                dish.setPrice(resultSet.getBigDecimal("price"));
//                dish.setWeight(resultSet.getInt("weight"));

                dishes.add(dish);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dishes;
    }

    public Dish getDishInfoByDishName(String name){
        Dish dish = new Dish();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select name, ingredients, price, weight from dishes" +
                    " where name = '" + name + "'");
            resultSet.next();
            dish.setName(name);
            dish.setIngredients(resultSet.getString("ingredients"));
            dish.setPrice(resultSet.getBigDecimal("price"));
            dish.setWeight(resultSet.getInt("weight"));
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dish;
    }

    public void addNewDish(Dish dish){
        try(Statement statement = connection.createStatement()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into dishes " +
                    "(name, ingredients, price, weight) values" +
                    " (?, ?, ?, ?)");
            preparedStatement.setString(1, dish.getName());
            preparedStatement.setString(2, dish.getIngredients());
            preparedStatement.setBigDecimal(3, dish.getPrice());
            preparedStatement.setDouble(4, dish.getWeight());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void updateDish(String name, Dish dish){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update dishes set name = ?, " +
                    "ingredients = ?, price = ?, weight = ? where name = " + "'" + name + "'");
            preparedStatement.setString(1, dish.getName());
            preparedStatement.setString(2, dish.getIngredients());
            preparedStatement.setBigDecimal(3, dish.getPrice());
            preparedStatement.setDouble(4, dish.getWeight());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(String name){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from dishes where name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
