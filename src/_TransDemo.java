// Delete all HR employees
// Set salaries for all engineers to 300k

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class _TransDemo {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");
            connection.setAutoCommit(false);

            String HR = "HR";
            String Engineer = "Engineering";

            System.out.println("Salaries before: ");
            showSalaries(connection, HR);
            showSalaries(connection, Engineer);

            statement = connection.createStatement();
            statement.executeUpdate("delete from employees where department= 'HR'");

            statement.executeUpdate("update employees set salary= 300000 where department='Engineering'");
            System.out.println("\nTransaction is ready to update");

            boolean confirm = askUserToConfirm();
            if (confirm) {
                connection.commit();
                System.out.println("Transaction committed");
            } else {
                connection.rollback();
                System.out.println("Transaction was not committed");
            }
            System.out.println("Salaries after updating: \n");
            showSalaries(connection, HR);
            showSalaries(connection, Engineer);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close(connection, statement, null);
        }
    }

    private static boolean askUserToConfirm() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to confirm? (yes/no)");
        String input = sc.nextLine();

        sc.close();
        return input.equalsIgnoreCase("yes");
    }

    private static void showSalaries(Connection connection, String role) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet set = null;

        System.out.println("Salaries for department: \n");
        try {
            preparedStatement = connection.prepareStatement("select * from employees where department=?");
            preparedStatement.setString(1, role);

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
        } finally {
            close(preparedStatement, set);
        }
    }

    public static void close(Connection connection, Statement statement,
                             ResultSet resultSet) throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public static void close(Statement statement, ResultSet set) throws SQLException {
        if (set != null) {
            set.close();
        }
        if (statement != null) {
            statement.close();
        }
    }
}

