import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeletingData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database_v1";
        String user = "root";
        String pass = "Magnum12!";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            String delete = "delete from data where Last_Name ='Carter' ";
            int rowsAffected = statement.executeUpdate(delete);

            if (rowsAffected == 0) {
                System.out.println("No data was removed");
            } else {
                System.out.println("Rows removed: " + rowsAffected);
            }
            System.out.println("Delete Complete");

        } catch (Exception ex) {ex.printStackTrace();}
    }
}
