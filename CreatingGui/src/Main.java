import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws Exception {
        _EmployeeD employeeD = new _EmployeeD();
        System.out.println(employeeD.searchEmployees("thom"));

        System.out.println(employeeD.getAllEmployees() + "\n");
        System.out.println();
    }
}