package employees;

import utility.MenuUtility;

public class EmployeeManager extends EmployeeRegular {

    EmployeeDegree degree;

    public EmployeeManager(String employeeID, String name, double grossSalary, String degree) {
        super(employeeID, name, grossSalary);
        switch (degree) {
            case "BSc":
                this.degree = EmployeeDegree.BSc;
                this.grossSalary += (grossSalary * EmployeeDegree.BSc.getSalaryBonus());
                break;
            case "MSc":
                this.degree = EmployeeDegree.MSc;
                this.grossSalary += (grossSalary * EmployeeDegree.MSc.getSalaryBonus());
                break;
            case "PhD":
                this.degree = EmployeeDegree.PhD;
                this.grossSalary += (grossSalary * EmployeeDegree.PhD.getSalaryBonus());
                break;
        }
        this.grossSalary = MenuUtility.doubleTruncate(this.grossSalary, 2);
    }

    @Override
    public String toString() {
        return this.degree + " " + this.name + "'s gross salary is " + MenuUtility.doubleFormat(getGrossSalary()) + " SEK per month.";
    }

    public EmployeeDegree getDegree() {
        return degree;
    }

    public void setDegree(EmployeeDegree degree) {
        this.degree = degree;
    }

//    @Override
//    public double getNetSalary() {
//
//        double netSalary = super.getNetSalary();
//
//        switch (degree) {
//            case BSc:
//                netSalary = grossSalary + (grossSalary * 0.1);
//                break;
//            case MSc:
//                netSalary = grossSalary + (grossSalary * 0.2);
//                break;
//            case PhD:
//                netSalary = grossSalary + (grossSalary * 0.35);
//                break;
//        }
//
//        return netSalary;
//    }


    @Override
    public double getGrossSalary() {
        return grossSalary + (grossSalary * degree.getSalaryBonus());
    }

    @Override
    public double getNetSalary() {
        return MenuUtility.doubleTruncate(getGrossSalary() - (getGrossSalary() * 0.1), 2);
    }
}
