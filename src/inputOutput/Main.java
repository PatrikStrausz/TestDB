package inputOutput;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        test database = new test();
        database.insert();
    }
}
