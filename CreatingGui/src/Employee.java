import java.math.BigDecimal;

public class Employee {
    private int ID;
    private String lastName;
    private String firstName;
    private String email;
    private BigDecimal salary;

    public Employee(int id, String lastName, String firstName,
                    String email, BigDecimal salary) {
        super();
        this.ID = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.salary = salary;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Employee [ID=%s, lastName=%s, firstName=%s, " +
                        "email=%s, salary=%s]",
                ID, lastName, firstName, email, salary);
    }
}
