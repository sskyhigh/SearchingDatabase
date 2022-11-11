import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class _EmployeeD {
    private Connection connection;

    public _EmployeeD() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("Login.properties"));

        String user = properties.getProperty("user");
        String pass = properties.getProperty("pass");
        String address = properties.getProperty("address");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");
            System.out.println("Connected to database" + address);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update employees" +
                    "set first_name=?, last_name=?, email=?, salary=?" + "where id=?");

            // setting the parameters:
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setBigDecimal(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getID());

            //Updating the database:
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void addEmployee(Employee employee) throws Exception {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into employees" + "(first_name, last_name, email, salary)" + "values (?, ?, ?, ?)");
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setBigDecimal(4, employee.getSalary());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() throws Exception {
        List<Employee> list = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from employees");

            while (resultSet.next()) {
                Employee temp = convertToEmployee(resultSet);
                list.add(temp);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close(statement, resultSet);
        }
        return list;
    }

    public List<Employee> searchEmployees(String lastName) throws Exception {
        List<Employee> list = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            lastName += "%";

            statement = connection.prepareStatement("select * from employees where last_name like ?");
            statement.setString(1, lastName);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Employee temp = convertToEmployee(resultSet);
                list.add(temp);
            }
        } finally {
            close(statement, resultSet);
        }
        return list;
    }

    // this gets the info from the database
    private static Employee convertToEmployee(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt("id");
        String lastName = resultSet.getString("last_name");
        String firstName = resultSet.getString("first_name");
        String email = resultSet.getString("email");
        BigDecimal salary = resultSet.getBigDecimal("salary");

        return new Employee(ID, lastName, firstName,
                email, salary);
    }

    private static void close(Statement statement, ResultSet set) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (set != null) {
            set.close();
        }
    }
}
