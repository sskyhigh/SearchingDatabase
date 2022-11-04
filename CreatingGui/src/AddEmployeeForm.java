import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class AddEmployeeForm extends JFrame {
    private JPanel AddEmployee;
    private JTextField _FirstName;
    private JTextField _LastName;
    private JTextField _Email;
    private JTextField Salary;
    private JButton saveButton;
    private JButton cancelButton;

    private _EmployeeD employeeD;
    private SearchApp _searchApp;

    public AddEmployeeForm(SearchApp searchApp, _EmployeeD theEmployee) {
        this();
        employeeD = theEmployee;
        _searchApp = searchApp;
    }

    public AddEmployeeForm() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEmployee();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
            }
        });
    }

    protected void saveEmployee() {
        String firstName = _FirstName.getText();
        String lastName = _LastName.getText();
        String email = _Email.getText();
        String sal = Salary.getText();

        BigDecimal salary = BigDecimal.valueOf(Long.parseLong(sal));
        Employee temp = new Employee(lastName, firstName, email, salary);

        try {
            employeeD.addEmployee(temp);
            // closing the dialog
            setVisible(false);
            dispose();
            _searchApp.refreshEmployee();

            // show that the employee was added:
            JOptionPane.showMessageDialog(_searchApp, "Employee has been added", "Employee added",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(_searchApp, "Error Saving the employee: " + e.getMessage(),
                    "Error at: ", JOptionPane.ERROR_MESSAGE);
        }
    }
}
