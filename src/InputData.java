import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InputData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database_v1";
        String username = "root";
        String password = "Magnum12!";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String addSQL = "insert into data" +
                    "(First_Name, Last_Name, ID)" + "values ('Johny', 'Carter', 15)";


            statement.executeUpdate(addSQL);
            System.out.println("Data has been added.");

        } catch (Exception exception) {exception.printStackTrace();}
    }
}
