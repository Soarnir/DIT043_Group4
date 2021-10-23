package employees;

import utility.MenuUtility;

public class EmployeeManager extends EmployeeRegular {

    protected EmployeeDegree degree;
    private static final int DIRECTOR_SALARY_BENEFIT = 5000;

    public EmployeeManager(String employeeID, String name, double grossSalary, String degree) {
        super(employeeID, name, grossSalary);
        setDegree(degree);
    }

    public EmployeeDegree getDegree() {
        return degree;
    }

    /*
     * Sets the degree for both Managers and Directors using an enum and
     * calculates the relevant salary bonus for either employee type.
     */
    public void setDegree(String degree) {
        switch (degree) {
            case "BSc":
                this.degree = EmployeeDegree.BSc;
                this.grossSalary = this.rawSalary + (this.rawSalary * EmployeeDegree.BSc.getSalaryBonus());
                break;
            case "MSc":
                this.degree = EmployeeDegree.MSc;
                this.grossSalary = this.rawSalary + (this.rawSalary * EmployeeDegree.MSc.getSalaryBonus());
                break;
            case "PhD":
                this.degree = EmployeeDegree.PhD;
                this.grossSalary = this.rawSalary + (this.rawSalary * EmployeeDegree.PhD.getSalaryBonus());
                break;
        }
        if (this instanceof EmployeeDirector) {
            this.grossSalary += DIRECTOR_SALARY_BENEFIT;
        }
        this.grossSalary = MenuUtility.doubleTruncate(this.grossSalary, 2);
    }

    @Override
    public double getGrossSalary() {
        return this.grossSalary;
    }

    @Override
    public double getNetSalary() {
        return MenuUtility.doubleTruncate(getGrossSalary() - (getGrossSalary() * 0.1), 2);
    }

    @Override
    public void setRawSalary(double rawSalary) {
        this.rawSalary = rawSalary;
        setDegree(this.degree.toString());
    }

    @Override
    public String toString() {
        return this.degree + " " + this.name + "'s gross salary is " + MenuUtility.doubleFormat(getGrossSalary()) + " SEK per month.";
    }
}
