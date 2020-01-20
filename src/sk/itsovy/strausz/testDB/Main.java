package sk.itsovy.strausz.testDB;

import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

//        selectAll();
//        selectByMail();
//        selectByMonth();


//        String myDate = "2000/12/12 00:00:00";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date =  sdf.parse(myDate);
//        long millis = date.getTime();
//
//        insertUser("asHN", "ksaasA", "kaspa@gmail.com", new Date(millis));

        findByName();

    }


    public static void selectAll() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        String select = "select * from userInfo ";
        PreparedStatement statement = connection.prepareStatement(select);
        ResultSet resultSet = statement.executeQuery();
        System.out.println("List of users");
        while (resultSet.next()) {

            System.out.println("  " + resultSet.getString(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3) +
                    "  " + resultSet.getString(4) + "  " + resultSet.getString(5));

        }

        connection.close();

    }

    public static void selectByMail() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/testDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        String select = "select * from userInfo where email like ? ";
        PreparedStatement statement = connection.prepareStatement(select);
        statement.setString(1, "%@gmail.com");
        ResultSet resultSet = statement.executeQuery();
        System.out.println("Users with gmail account: ");
        while (resultSet.next()) {

            System.out.println("  " + resultSet.getString(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3) +
                    "  " + resultSet.getString(4) + "  " + resultSet.getString(5));

        }
        System.out.println();
        connection.close();


    }

    public static void selectByMonth() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/testDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        String select = "select * from userInfo where DateOfBirth like ? ";

        PreparedStatement statement = connection.prepareStatement(select);
        statement.setString(1, "%-02-%");
        ResultSet resultSet = statement.executeQuery();

        System.out.println("Users born in february: ");
        while (resultSet.next()) {

            System.out.println("  " + resultSet.getString(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3) +
                    "  " + resultSet.getString(4) + "  " + resultSet.getString(5));

        }

        connection.close();


    }


    public static void insertUser(String name, String lastname, String email, Date dateuser) throws ClassNotFoundException, SQLException, ParseException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/testDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        String insert = "INSERT INTO userInfo (Name, Lastname, email, DateOfBirth) VALUES(?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(insert);

        String nameUpper = name.substring(0, 1).toUpperCase();
        String capitalized = nameUpper + name.substring(1).toLowerCase();
        String lastnameUpper = lastname.substring(0, 1).toUpperCase();
        String cap = lastnameUpper + lastname.substring(1).toLowerCase();

        statement.setString(1, capitalized);
        statement.setString(2, cap);
        statement.setString(3, email);


        DateFormat dateform = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateform.format(dateuser);

        statement.setString(4, strDate);
        statement.execute();


    }

    public static void findByName() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/testDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        String select = "SELECT * FROM userInfo where name like ? OR lastname like ?";

        PreparedStatement statement = connection.prepareStatement(select);
        statement.setString(1, "%oz%");
        statement.setString(2, "%oz%");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            System.out.println("  " + resultSet.getString(1) + "  " + resultSet.getString(2) + "  " +
                    resultSet.getString(3));


        }

        connection.close();


    }
}
