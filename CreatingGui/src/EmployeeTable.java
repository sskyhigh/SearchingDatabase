import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmployeeTable extends AbstractTableModel {
    private static final int LAST_NAME_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int EMAIL_COL = 2;
    private static final int SALARY_COL = 3;

    private String[] columnNames = {"Last Name", "First Name",
            "Email, Salary"};

    private List<Employee> employees;

    public EmployeeTable(List<Employee> TheEmployees) {
        employees = TheEmployees;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee temp = employees.get(rowIndex);
        switch (columnIndex) {
            case LAST_NAME_COL:
                return temp.getLastName();
            case FIRST_NAME_COL:
                return temp.getFirstName();
            case EMAIL_COL:
                return temp.getEmail();
            case SALARY_COL:
                return temp.getSalary();
            default:
                return temp.getLastName();
        }
    }

    @Override
    public Class getColumnClass(int a) {
        return getValueAt(0, a).getClass();
    }
}