package controllers;

import employees.EmployeeRegular;
import employees.EmployeeDirector;
import employees.EmployeeIntern;
import employees.EmployeeManager;
import exceptions.IDNotFoundException;
import item.Storage;
import utility.MenuUtility;

import java.util.HashMap;
import java.util.Map;

public class EmployeeController {

    Storage storage;

    public EmployeeController(Storage storage) {
        this.storage = storage;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        try {
            storage.getEmployeeMap().put(employeeID, new EmployeeRegular(employeeID, employeeName, grossSalary));
            MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
            return "Employee " + employeeID + " was registered successfully.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String printEmployee(String employeeID) throws Exception {
        return storage.getEmployeeMap().get(employeeID).toString();
    }

    public String createManagerEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        try {
            storage.getEmployeeMap().put(employeeID, new EmployeeManager(employeeID, employeeName, grossSalary, degree));
            MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
            return "Employee " + employeeID + " was registered successfully.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String createInternEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        try {
            storage.getEmployeeMap().put(employeeID, new EmployeeIntern(employeeID, employeeName, grossSalary, gpa));
            MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
            return "Employee " + employeeID + " was registered successfully.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public double getNetSalary(String employeeID) throws Exception {
        return storage.getEmployeeMap().get(employeeID).getNetSalary();
    }

    public String createDirectorEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        try {
            storage.getEmployeeMap().put(employeeID, new EmployeeDirector(employeeID, employeeName, grossSalary, degree, dept));
            MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
            return "Employee " + employeeID + " was registered successfully.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String removeEmployee(String empID) throws Exception {

        try {
            storage.getEmployeeMap().remove(empID);
            return "Employee " + empID + " was successfully removed.";
        } catch (IDNotFoundException e) {
            // TODO Where to throw first? -K ("Employee <ID> was not registered yet.")
            return e.getMessage();
        }
    }

    public String printAllEmployees() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("All registered employees:");
        for (EmployeeRegular employee : storage.getEmployeeMap().values()){
            sb.append(employee.toString());
        }
        return sb.toString();
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
        return new HashMap<>();
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
