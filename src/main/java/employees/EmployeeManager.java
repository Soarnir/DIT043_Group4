package employees;

import utility.MenuUtility;

public class EmployeeManager extends Employee {

    EmployeeDegree degree;

    public EmployeeManager(String employeeID, String name, double grossSalary, String degree) {
        super(employeeID, name, grossSalary);
        switch (degree) {
            case "BSc":
                this.degree = EmployeeDegree.BSc;
                break;
            case "MSc":
                this.degree = EmployeeDegree.MSc;
                break;
            case "PhD":
                this.degree = EmployeeDegree.PhD;
                break;
            default:
                System.out.println("what");
        }
    }

    @Override
    public String toString() {
        return this.degree + " " + this.name + "'s gross salary is " + MenuUtility.doubleFormat(this.grossSalary) + " SEK per month.";
    }

    public EmployeeDegree getDegree() {
        return degree;
    }

    public void setDegree(EmployeeDegree degree) {
        this.degree = degree;
    }
}
