import java.sql.*;

public class Mainnn {
    public static void main(String[] args) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from demo.employees");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Last_Name") + " " + resultSet.getString("ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}