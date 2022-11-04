import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchApp extends JFrame {
    private JPanel MainPanel;
    private JButton searchButton;
    private JTextField userData;
    private JScrollPane pane;
    private JTable table;
    private JLabel textHolder;
    private JButton addEmployeeButton;
    private String lastName;
    private _EmployeeD employeeD;

    public SearchApp() throws Exception {
        employeeD = new _EmployeeD();
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
                for (Employee temp : employees) {
                    System.out.println(temp);
                }
            }
        });
        searchButton.setFocusable(false);
        setContentPane(MainPanel);
        setTitle("Search feature");
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEmployeeForm addEmployeeForm = new AddEmployeeForm(SearchApp.this, employeeD);
                addEmployeeForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        SearchApp app = new SearchApp();
    }

    public void refreshEmployee() {
        try{
            List <Employee> employees = employeeD.getAllEmployees();

            // creating the model and update the "table"
            EmployeeTable model = new EmployeeTable(employees);
        }catch(Exception exception){

        }
    }
}
