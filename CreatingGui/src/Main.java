import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws Exception {
        _EmployeeD employeeD = new _EmployeeD();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from demo.employees");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("Last_Name") + " " + resultSet.getString("ID"));
        }
    }
}