package employees;

public interface Employee {

    String getEmployeeID();

    String getName();

    double getRawSalary();

    double getGrossSalary();

    double getNetSalary();

    void setName(String name);

    void setRawSalary(double rawSalary);

}