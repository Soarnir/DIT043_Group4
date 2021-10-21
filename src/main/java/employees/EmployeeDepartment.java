package employees;

public enum EmployeeDepartment {
    Business("Business"),
    Technical("Technical"),
    HumanResources("Human Resources");

    private final String departmentName;

    EmployeeDepartment(String name) {
        this.departmentName = name;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }
}
