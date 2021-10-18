package employees;

public class EmployeeIntern extends Employee {

    int gpa;

    public EmployeeIntern(String employeeID, String name, double grossSalary, int gpa) {
        super(employeeID, name, grossSalary);
        this.gpa = gpa;
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }
}
