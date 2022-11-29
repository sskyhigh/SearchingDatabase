import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Update_Database {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo.employees";
        String username = "root";
        String password = "Magnum12!";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String UpdateData = "update data" +
                    " set Email = 'jimmy1@gmail.com' " +
                    "where ID = 13";

            statement.executeUpdate(UpdateData);
            System.out.println("update completed. ");
        } catch (Exception exception) {exception.printStackTrace();}
    }
}
