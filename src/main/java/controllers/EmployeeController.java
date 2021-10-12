package controllers;

import item.Storage;

import java.util.Map;

public class EmployeeController {

    Storage storage;

    public EmployeeController(Storage storage) {
        this.storage = storage;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        return "";
    }

    public String printEmployee(String employeeID) throws Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        return "";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        return "";
    }

    public double getNetSalary(String employeeID) throws Exception {
        return -1.0;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        return "";
    }

    public String removeEmployee(String empID) throws Exception {
        return "";
    }

    public String printAllEmployees() throws Exception {
        return "";
    }

    public double getTotalNetSalary() throws Exception {
        return -1.0;
    }

    public String printSortedEmployees() throws Exception {
        return "";
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        return "";
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        return "";
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        return "";
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        return "";
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        return "";
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        return null;
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        return "";

    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        return "";
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        return "";
    }
}
