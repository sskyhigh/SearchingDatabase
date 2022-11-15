import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;


public class SearchApp extends JFrame {
    private JPanel MainPanel;
    private JButton searchButton;
    private JButton addEmployeeButton;
    private JTextField userData;
    private JScrollPane pane;
    private JTable table;
    private JLabel textHolder;
    private JButton updateButton;
    private String lastName;
    private _EmployeeD employeeD;

    public SearchApp() throws Exception {
        try {
            employeeD = new _EmployeeD();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastName = userData.getText();
                List<Employee> employees = null;
                if (lastName != null && lastName.trim().length() > 0) {
                    try {
                        employees = employeeD.searchEmployees(lastName);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        employees = employeeD.getAllEmployees();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                EmployeeTable table1 = new EmployeeTable(employees);
                table.setModel(table1);
            }
        });
        searchButton.setFocusable(false);
        setContentPane(MainPanel);
        setTitle("Search feature");
        setVisible(true);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEmployeeForm addEmployeeForm = new AddEmployeeForm(SearchApp.this, employeeD);
                addEmployeeForm.setVisible(true);
                dispose();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(SearchApp.this, "You need to select an employee",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Employee temp = (Employee) table.getValueAt(row, EmployeeTable.OBJECT_COL);
                AddEmployeeForm addEmployeeForm = new AddEmployeeForm(SearchApp.this,
                        employeeD, temp, true);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        try {
            SearchApp app = new SearchApp();
            app.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshEmployee() {
        try {
            List<Employee> employees = employeeD.getAllEmployees();
            // creating the model and update the "table"
            EmployeeTable model = new EmployeeTable(employees);
            table.setModel(model);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Error: " + exception, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
