import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetaData_Basics {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_v1", "root", "Magnum12!");
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("Product Name: " + metaData.getDatabaseProductName());
            System.out.println("Product Version: " + metaData.getDatabaseProductVersion());
            System.out.println();

            System.out.println("JDBC driver name: " + metaData.getDriverName());
            System.out.println("JDBC driver version: " + metaData.getDriverVersion());
        } catch (Exception exception) {exception.printStackTrace();} finally {
            close(connection);
        }
    }

    private static void close(Connection connection) throws SQLException {
        if (connection != null) connection.close();
    }
}