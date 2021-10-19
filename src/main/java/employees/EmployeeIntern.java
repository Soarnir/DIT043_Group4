package employees;

import utility.MenuUtility;

public class EmployeeIntern extends EmployeeRegular {

    int gpa;
    double grossSalaryPlusBonus;
    final int LOWER_GPA_BOUND = 5;
    final int HIGHER_GPA_BOUND = 8;
    final int ACADEMIC_EXCELLENCE_BONUS = 1000;

    public EmployeeIntern(String employeeID, String name, double grossSalary, int gpa) {
        super(employeeID, name, grossSalary);
        this.gpa = gpa;
        if (gpa <= LOWER_GPA_BOUND){
            this.grossSalaryPlusBonus = MenuUtility.doubleTruncate(0,2 );
        } else if (gpa >= HIGHER_GPA_BOUND){
            this.grossSalaryPlusBonus += ACADEMIC_EXCELLENCE_BONUS;
            // TODO could extract the doubleTruncate out of the if-else -K
            this.grossSalaryPlusBonus = MenuUtility.doubleTruncate(grossSalaryPlusBonus,2 );
        }
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    @Override
    public double getNetSalary() {
        return grossSalaryPlusBonus;
    }

    @Override
    public String toString() {
        return this.name + "'s gross salary is " + this.grossSalaryPlusBonus + " SEK per month. GPA: " + this.gpa;
    }
}
