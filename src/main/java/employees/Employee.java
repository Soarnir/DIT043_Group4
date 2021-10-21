package employees;

public interface Employee {

    String getEmployeeID();

    String getName();

    double getGrossSalary();

    double getNetSalary();

    double getRawSalary();

    void setName(String name);

    void setRawSalary(double rawSalary);

}