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
    private Employee previousEmp = null;
    private boolean update = false;

    public AddEmployeeForm(SearchApp searchApp, _EmployeeD theEmployee,
                           Employee previous, boolean update1) {
        this();
        employeeD = theEmployee;
        _searchApp = searchApp;
        update = update1;
        previousEmp = previous;

        if (update) {
            setTitle("Update Employee");
            populateData(previous);
        }
    }

    public AddEmployeeForm(SearchApp searchApp, _EmployeeD employeeD) {
        this(searchApp, employeeD, null, false);
    }

    public void populateData(Employee employee) {
        _FirstName.setText(employee.getFirstName());
        _LastName.setText(employee.getLastName());
        _Email.setText(employee.getEmail());
        Salary.setText(employee.getSalary().toString());
    }

    public AddEmployeeForm() {
        setContentPane(AddEmployee);
        setSize(400, 200);
        setVisible(true);
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

    protected BigDecimal convertingStrToDecimal(String salary) {
        BigDecimal result = null;
        try {
            double sal = Double.parseDouble(salary);
            result = BigDecimal.valueOf(sal);
        } catch (Exception exception) {
            System.out.println("Invalid value, setting to 0.0");
            result = BigDecimal.valueOf(0.0);
        }
        return result;
    }

    protected void saveEmployee() {
        if (_FirstName.getText().isEmpty() || _LastName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Input all fields");
        }
        String firstName = _FirstName.getText();
        String lastName = _LastName.getText();
        String email = _Email.getText();

        String sal = Salary.getText();
        BigDecimal salary = convertingStrToDecimal(sal);

        Employee temp = null;

        if (update) {
            temp = previousEmp;

            temp.setFirstName(firstName);
            temp.setLastName(lastName);
            temp.setEmail(email);
            temp.setSalary(salary);
        } else {
            temp = new Employee(lastName, firstName, email, salary);
        }

        try {
            if (update) {
                employeeD.updateEmployee(temp);
            } else {
                employeeD.addEmployee(temp);
            }
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
