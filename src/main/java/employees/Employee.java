package employees;

public interface Employee {

    String getEmployeeID();

    String getName();

    double getGrossSalary();

    double getNetSalary();

    void setName(String name);

    void setGrossSalary(int grossSalary);

}