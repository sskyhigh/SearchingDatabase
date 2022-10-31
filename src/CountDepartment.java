import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class CountDepartment {
    public static void main(String[] args) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");
            String department = "Engineering";
            callableStatement = connection.prepareCall("{call get_count_for_department(?,?)}");

            // set the parameters
            // to replace the ?, ?

            callableStatement.setString(1, department);
            callableStatement.registerOutParameter(2, Types.INTEGER);

            callableStatement.execute();

            System.out.println("Finished calling stored procedures");

            int count=  callableStatement.getInt(2);
            System.out.println(count + "count ");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
