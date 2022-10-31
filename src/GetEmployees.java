import java.sql.*;

public class GetEmployees {
    public static void main(String[] args) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");
            String department = "Engineering";
            callableStatement = connection.prepareCall("{call get_employees_for_department(?)}");

            callableStatement.setString(1, department);
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();

            display(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
