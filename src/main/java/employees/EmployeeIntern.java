package employees;

import utility.MenuUtility;

public class EmployeeIntern extends EmployeeRegular {

    private int gpa;
    private final int LOWER_GPA_BOUND = 5;
    private final int HIGHER_GPA_BOUND = 8;
    private final int ACADEMIC_EXCELLENCE_BONUS = 1000;

    public EmployeeIntern(String employeeID, String name, double grossSalary, int gpa) {
        super(employeeID, name, grossSalary);
        setGPA(gpa);
    }

    public int getGpa() {
        return this.gpa;
    }

    public void setGPA(int gpa) {
        if (gpa <= LOWER_GPA_BOUND) {
            this.grossSalary = 0.0;
        } else if (gpa < HIGHER_GPA_BOUND) {
            this.grossSalary = MenuUtility.doubleTruncate(this.rawSalary,2 );
        } else {
            this.grossSalary = MenuUtility.doubleTruncate((this.rawSalary + ACADEMIC_EXCELLENCE_BONUS),2 );
        }
        this.gpa = gpa;
    }

    @Override
    public double getGrossSalary() {
        return this.grossSalary;
    }

    @Override
    public double getNetSalary() {
        return getGrossSalary();
    }

    @Override
    public void setRawSalary(double rawSalary) {
        this.rawSalary = rawSalary;
        setGPA(this.gpa);
    }

    @Override
    public String toString() {
        return this.name + "'s gross salary is " + MenuUtility.doubleFormat(getGrossSalary()) + " SEK per month. GPA: " + this.gpa;
    }
}
