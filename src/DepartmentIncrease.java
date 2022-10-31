import javax.xml.transform.Result;
import java.sql.*;

public class DepartmentIncrease {
    public static void main(String[] args) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");
            String department = "Engineering";
            double increaseAmount = 10000;

            System.out.println("salaries before:\n");
            showSalaries(connection, department);

            callableStatement = connection.prepareCall("{call increase_salaries_for_department(?, ?)}");
            callableStatement.setString(1, department);
            callableStatement.setDouble(2, increaseAmount);

            callableStatement.execute();

            System.out.println("Salaries after\n");
            showSalaries(connection, department);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void showSalaries(Connection connection, String department) throws SQLException {
        ResultSet set = null;
        PreparedStatement preparedStatement = null;

        try {
            // preparing the statements
            preparedStatement = connection.prepareStatement("select * from employees where department=?");
            preparedStatement.setString(1, department);
            // executing the query
            set = preparedStatement.executeQuery();
            while (set.next()) {
                String lastName = set.getString("last_name");
                String firstName = set.getString("first_name");
                double sal = set.getDouble("salary");
                String depart = set.getString("department");

                System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, sal, depart);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

