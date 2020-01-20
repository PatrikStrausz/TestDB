package inputOutput;

import java.sql.*;

public class test {

    public void insert() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/testDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        String select = "SELECT name,a,b,c  from input";
        String insert = "INSERT INTO output (name, surface, volume) VALUES( ?, ?, ?)";
        String delete = "DELETE from input where name = ? ";
        PreparedStatement statement = connection.prepareStatement(select);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            int a = resultSet.getInt(2);
            int b = resultSet.getInt(3);
            int c = resultSet.getInt(4);

            int surface = (a * b * 2) + (b * c * 2) + (a * c + 2);
            int volume = a * b * c;
            System.out.println("Values: " +resultSet.getInt(2) + ", " + resultSet.getInt(3) + ", "+resultSet.getInt(4));
            System.out.println("Surface: " +surface+"\n" +"Volume: " +volume);

            PreparedStatement stmt = connection.prepareStatement(insert);

            stmt.setString(1, name);
            stmt.setInt(2, surface);
            stmt.setInt(3, volume);

            stmt.execute();

            PreparedStatement statement1 = connection.prepareStatement(delete);

            statement1.setString(1, name);
            statement1.execute();
            System.out.println(name + " deleted from database.");


        }

        connection.close();
    }


}
