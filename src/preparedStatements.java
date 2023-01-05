import javax.xml.transform.Result;
import java.sql.*;

public class preparedStatements {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_v1", "root", "Magnum12!");
            statement = connection.prepareStatement("select * from data where salary > ? and department=?");
            statement.setDouble(1, 20000);
            statement.setString(2, "hr");
            resultSet = statement.executeQuery();
            display(resultSet);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
    }

    public static void display(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String last = resultSet.getString("Last_Name");
            String first = resultSet.getString("First_Name");
            double sal = resultSet.getDouble("salary");
            String department = resultSet.getString("department");

            System.out.printf("%s, %s, %.2f, %s\n", last, first, sal, department);
        }
    }
}
