package employees;

import utility.MenuUtility;

public class EmployeeDirector extends EmployeeManager {

    String department;
    final int DIRECTOR_SALARY_BENEFIT = 5000;
    final int LOWER_SALARY_BOUND = 30000;
    final int UPPER_SALARY_BOUND = 50000;
    // Could have reused two variables but BSC_BONUS would've been unclear -K
    final double LOW_TAX_PERCENTAGE = 0.1;
    final double MEDIUM_TAX_PERCENTAGE = 0.2;
    final double HIGH_TAX_PERCENTAGE = 0.4;

    public EmployeeDirector(String employeeID, String name, double grossSalary, String degree, String department) {
        super(employeeID, name, grossSalary, degree);
        // Need to discuss -K
        this.grossSalary = this.grossSalary + DIRECTOR_SALARY_BENEFIT;
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public double getNetSalary() {
        double netSalary = 0;

        if (this.grossSalary < LOWER_SALARY_BOUND){
            netSalary = this.grossSalary - (this.grossSalary * LOW_TAX_PERCENTAGE);
        // TODO Need to clarify if it's <= and >= or < and > -K
        } else if (grossSalary >= LOWER_SALARY_BOUND && grossSalary <= UPPER_SALARY_BOUND){
            netSalary = this.grossSalary - (this.grossSalary * MEDIUM_TAX_PERCENTAGE);
        } else {
            double salaryLessLowerBound = this.grossSalary - LOWER_SALARY_BOUND;
            netSalary = this.grossSalary - (this.grossSalary * MEDIUM_TAX_PERCENTAGE);
            netSalary -= (salaryLessLowerBound * HIGH_TAX_PERCENTAGE);
        }

        return netSalary;
    }

    @Override
    public String toString() {
        return this.degree + " " + this.name + "'s gross salary is " + MenuUtility.doubleFormat(this.grossSalary) + " SEK per month. " + "Dept: " + this.department;
    }
}
