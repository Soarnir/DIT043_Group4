package employees;

public enum EmployeeDegree {
    BSc(0.1),
    MSc(0.2),
    PhD(0.35);

    private final double SALARY_BONUS;

    /*
     * Enum implemented to maintain updated collection of valid Employee degrees and relevant bonuses
     */
    EmployeeDegree(double salaryBonus) {
        this.SALARY_BONUS = salaryBonus;
    }

    public double getSalaryBonus() {
        return SALARY_BONUS;
    }
}
