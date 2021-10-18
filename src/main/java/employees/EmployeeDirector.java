package employees;

public class EmployeeDirector extends EmployeeManager {

    String department;

    public EmployeeDirector(String employeeID, String name, double grossSalary, String degree, String department) {
        super(employeeID, name, grossSalary, degree);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
