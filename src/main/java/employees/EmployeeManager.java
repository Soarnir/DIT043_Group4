package employees;

import utility.MenuUtility;

public class EmployeeManager extends EmployeeRegular {

    EmployeeDegree degree;
    final double BSC_BONUS = 0.1;
    final double MSC_BONUS = 0.2;
    final double PHD_BONUS = 0.35;

    public EmployeeManager(String employeeID, String name, double grossSalary, String degree) {
        super(employeeID, name, grossSalary);
        switch (degree) {
            case "BSc":
                this.degree = EmployeeDegree.BSc;
                this.grossSalary += (grossSalary * BSC_BONUS);
                break;
            case "MSc":
                this.degree = EmployeeDegree.MSc;
                this.grossSalary += (grossSalary * MSC_BONUS);
                break;
            case "PhD":
                this.degree = EmployeeDegree.PhD;
                this.grossSalary += (grossSalary * PHD_BONUS);
                break;
            default:
                // ?? -K
                System.out.println("what");
                // TODO can this be added here?
                this.grossSalary = MenuUtility.doubleTruncate(this.grossSalary, 2);
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
//            default:
//                // ?? -K
//                System.out.println("what");
//        }
//
//        return netSalary;
//    }

}
