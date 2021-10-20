package employees;

public enum EmployeeDegree {
    BSc(0.1),
    MSc(0.2),
    PhD(0.35);

    private final double salaryBonus;

    EmployeeDegree(double salaryBonus) {
        this.salaryBonus = salaryBonus;
    }

    public double getSalaryBonus() {
        return salaryBonus;
    }
}
