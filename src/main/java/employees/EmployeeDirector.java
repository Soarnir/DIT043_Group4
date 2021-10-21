package employees;

import utility.MenuUtility;

public class EmployeeDirector extends EmployeeManager {

    EmployeeDepartment department;
    final int LOWER_SALARY_BOUND = 30000;
    final int UPPER_SALARY_BOUND = 50000;
    final double LOW_TAX_PERCENTAGE = 0.1;
    final double MEDIUM_TAX_PERCENTAGE = 0.2;
    final double HIGH_TAX_PERCENTAGE = 0.4;

    public EmployeeDirector(String employeeID, String name, double grossSalary, String degree, String department) {
        super(employeeID, name, grossSalary, degree);
        setDepartment(department);
    }

    public String getDepartment() {
        return this.department.getDepartmentName();
    }

    public void setDepartment(String department) {
        switch (department) {
            case "Business":
                this.department = EmployeeDepartment.Business;
                break;
            case "Technical":
                this.department = EmployeeDepartment.Technical;
                break;
            case "Human Resources":
                this.department = EmployeeDepartment.HumanResources;
                break;
        }
    }

    @Override
    public double getGrossSalary() {
        return this.grossSalary;
    }

    @Override
    public double getNetSalary() {
        double netSalary;

        if (getGrossSalary() < LOWER_SALARY_BOUND){
            netSalary = getGrossSalary() - (getGrossSalary() * LOW_TAX_PERCENTAGE);
        // TODO Need to clarify if it's <= and >= or < and > -K
        } else if (getGrossSalary() >= LOWER_SALARY_BOUND && getGrossSalary() <= UPPER_SALARY_BOUND){
            netSalary = getGrossSalary() - (getGrossSalary() * MEDIUM_TAX_PERCENTAGE);
        } else {
            netSalary = getGrossSalary() - (LOWER_SALARY_BOUND * MEDIUM_TAX_PERCENTAGE);
            netSalary -= ((getGrossSalary() - LOWER_SALARY_BOUND) * HIGH_TAX_PERCENTAGE);
        }
        return netSalary;
    }

    @Override
    public String toString() {
        return this.degree + " " + this.name + "'s gross salary is " + MenuUtility.doubleFormat(getGrossSalary()) + " SEK per month. " + "Dept: " + getDepartment();
    }
}
