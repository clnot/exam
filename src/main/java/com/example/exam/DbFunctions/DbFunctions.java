package com.example.exam.DbFunctions;

import com.example.exam.Controller.Clients;
import com.example.exam.Controller.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbFunctions {

    public Connection connect_to_db() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" +
                    "exam", "postgres", "123");
            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connected failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }


    public void createUser(String surname, String name, String lastname,
                           String login, String password) {
        try {
            String query = String.format("insert into пользователи (фамилия, имя," +
                            " отчество, логин, пароль) " +
                            "values('%s', '%s', '%s', '%s', '%s');",
                    surname, name, lastname, login, password);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("user created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int signIn(String login, String password) {
        try {
            String query = String.format("select * from пользователи where логин = '%s' and пароль='%s'", login, password);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                return 0;
            }
            Variables.LOGIN_USER = resultSet.getString("id");
            Variables.ACTIVE_USER = resultSet.getString("логин");
            SingletonUser.getInstance().setId(resultSet.getString("id"));
            SingletonUser.getInstance().setSurname(resultSet.getString("фамилия"));
            SingletonUser.getInstance().setName(resultSet.getString("имя"));
            SingletonUser.getInstance().setLastname(resultSet.getString("отчество"));
            SingletonUser.getInstance().setLogin(resultSet.getString("логин"));
            SingletonUser.getInstance().setPassword(resultSet.getString("пароль"));



        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public int check_login(String login) {
        try {
            String query = String.format("select * from пользователи where логин = '%s'", login);
            Statement statement = connect_to_db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();
            if (resultSet.getRow() >= 1) {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }


    public ObservableList<Clients> getClients() {

        ObservableList<Clients> clients = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery(String.format(
                    "select * from занятия_пред"));
            while (resultSet.next()) {
                clients.add(new Clients(
                        resultSet.getString("фамилия"),
                        resultSet.getString("имя"),
                        resultSet.getString("отчество"),
                        resultSet.getString("название"),
                        resultSet.getString("дата_начала"),
                        resultSet.getString("дата_окончания")
                ));
            }
            return clients;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return clients;
        }
    }

    public ObservableList<Services> getServices() {

        ObservableList<Services> clients = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery(String.format(
                    "select название, цена from услуги"));
            while (resultSet.next()) {
                clients.add(new Services(
                        resultSet.getString("название"),
                        resultSet.getFloat("цена")

                ));
            }
            return clients;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return clients;
        }
    }


    public int deleteDataBiscuit(String id) {
        try {
            String query = String.format("delete from состав_бисквита a using бисквиты b where a.код_бисквита = b.id and b.название = '%s';", id);
            String query2 = String.format("delete from бисквиты b where b.название = '%s'", id);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            Statement statement1 = connect_to_db().createStatement();
            statement1.executeUpdate(query2);
            System.out.println("Data deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void updateIngFilling(Float weight, String idFilling) {
        try {
            String query = String.format("update состав_начинки set вес = '%s' where id ='%s'",
                    weight, idFilling);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    public XYChart.Series<String, Number> getStatistic() { для статистики
//        XYChart.Series<String, Number> series = new XYChart.Series<>();
//        try {
//            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select * from sums");
//
//            while (resultSet.next()) {
//                String string = resultSet.getString("extract");
//                Number value = resultSet.getInt("sum");
//                series.getData().add(new XYChart.Data<>(string, value));
//            }
//            return series;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return series;
//        }
//    }


    // этот метод прописывать в классе в котором вызвать хочешь
//    private void installBarChart() throws SQLException {
//        CategoryAxis categoryAxis = new CategoryAxis();
//        categoryAxis.setLabel("Год");
//        NumberAxis numberAxis = new NumberAxis();
//        numberAxis.setLabel("Сумма");
//        barChart = new BarChart<>(categoryAxis, numberAxis);
//        barChart.getData().add(dbFunctions.getStatistic());
//        tabSums.setContent(barChart);
//
//
//    }


}
