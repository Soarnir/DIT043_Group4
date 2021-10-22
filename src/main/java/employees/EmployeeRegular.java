package employees;

import utility.MenuUtility;

import java.util.Objects;

public class EmployeeRegular implements Employee {

    protected final String EMPLOYEE_ID;
    protected String name;
    protected double rawSalary;
    protected double grossSalary;

    public EmployeeRegular(String employeeID, String name, double grossSalary) {
        this.EMPLOYEE_ID = employeeID;
        this.name = name;
        this.rawSalary = MenuUtility.doubleTruncate(grossSalary, 2);
        this.grossSalary = MenuUtility.doubleTruncate(grossSalary, 2);
    }

    public String getEmployeeID() {
        return this.EMPLOYEE_ID;
    }

    public String getName() {
        return this.name;
    }

    //Regular employees get raw salary vs gross salary as no value manipulation is needed for them.
    public double getGrossSalary() {
        return this.grossSalary;
    }

    public double getRawSalary() {
        return this.rawSalary;
    }

    public double getNetSalary() {
        return MenuUtility.doubleTruncate(getGrossSalary() - (getGrossSalary() * 0.1), 2);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRawSalary(double rawSalary) {
        this.rawSalary = rawSalary;
        this.grossSalary = rawSalary;
    }

    @Override
    public String toString() {
        return this.name + "'s gross salary is " + MenuUtility.doubleFormat(getGrossSalary()) + " SEK per month.";
    }

    @Override
    public boolean equals(Object employee) {
        if (this == employee) {
            return true;
        }
        if (employee == null || getClass() != employee.getClass()) {
            return false;
        }
        EmployeeRegular employeeRegular = (EmployeeRegular) employee;
        return EMPLOYEE_ID.equals(employeeRegular.EMPLOYEE_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EMPLOYEE_ID);
    }
}