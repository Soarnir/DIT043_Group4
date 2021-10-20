package employees;

import utility.MenuUtility;

public class EmployeeRegular implements Employee {

    final String EMPLOYEE_ID;
    String name;
    double grossSalary;
    double netSalary;

    public EmployeeRegular(String employeeID, String name, double grossSalary) {
        this.EMPLOYEE_ID = employeeID;
        this.name = name;
        this.grossSalary = MenuUtility.doubleTruncate(grossSalary, 2);
        this.netSalary = MenuUtility.doubleTruncate(grossSalary - (grossSalary * 0.1), 2);
    }

    //  TODO Discuss this, was implemented so EmployeeManager could handle grossSalary implementation -K
//    public EmployeeRegular(String employeeID, String name) {
//        this.EMPLOYEE_ID = employeeID;
//        this.name = name;
//    }

    public String getEmployeeID() {
        return EMPLOYEE_ID;
    }

    public String getName() {
        return name;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrossSalary(int grossSalary) {
        this.grossSalary = grossSalary;
    }

    @Override
    public String toString() {
        return this.name + "'s gross salary is " + MenuUtility.doubleFormat(this.grossSalary) + " SEK per month.";
    }
}
