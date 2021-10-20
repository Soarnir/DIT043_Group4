package controllers;

import employees.*;
import exceptions.*;
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

//        if (employeeID.isEmpty()) {
//            throw new InputEmptyException("ID cannot be blank.");
//        } else if (employeeName.isEmpty()) {
//            throw new InputEmptyException("Name cannot be blank.");
//        } else if (grossSalary <= 0) {
//            throw new InputEmptyException("Salary must be greater than zero.");
//        } else {
//            storage.getEmployeeMap().put(employeeID, new EmployeeRegular(employeeID, employeeName, grossSalary));
//            MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
//            return "Employee " + employeeID + " was registered successfully.";
//        }

//        if (employeeID.isEmpty() || employeeName.isEmpty() || grossSalary <= 0) {
//            produceCreationExceptions(employeeID, employeeName, grossSalary);
//        } else {
//            storage.getEmployeeMap().put(employeeID, new EmployeeRegular(employeeID, employeeName, grossSalary));
//            MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
//            return "Employee " + employeeID + " was registered successfully.";
//        }
        // TODO check if this works, otherwise make produceCreationExceptionMessage return a boolean. -K
        produceCreationExceptionMessage(employeeID, employeeName, grossSalary);

        storage.getEmployeeMap().put(employeeID, new EmployeeRegular(employeeID, employeeName, grossSalary));
        MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
        return "Employee " + employeeID + " was registered successfully.";
    }

    public void  produceCreationExceptionMessage (String employeeID, String employeeName, double grossSalary) throws Exception {
        if (employeeID.trim().isEmpty()) {
            throw new InputEmptyException("ID cannot be blank.");
        } else if (employeeName.trim().isEmpty()) {
            throw new InputEmptyException("Name cannot be blank.");
        } else if (grossSalary <= 0) {
            throw new InvalidSalaryException();
        }
    }

    public String printEmployee(String employeeID) throws Exception {
        return storage.getEmployee(employeeID).toString();
    }

    public String createManagerEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        produceCreationExceptionMessage(employeeID, employeeName, grossSalary);
        if (degree.equals(EmployeeDegree.BSc.toString()) || degree.equals(EmployeeDegree.MSc.toString()) || degree.equals(EmployeeDegree.PhD.toString())) {
            storage.getEmployeeMap().put(employeeID, new EmployeeManager(employeeID, employeeName, grossSalary, degree));
            MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
            return "Employee " + employeeID + " was registered successfully.";
        } else {
            throw new InvalidDegreeException();
        }
    }

    public String createInternEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        produceCreationExceptionMessage(employeeID, employeeName, grossSalary);
        if (gpa >= 0 && gpa <= 10){
            storage.getEmployeeMap().put(employeeID, new EmployeeIntern(employeeID, employeeName, grossSalary, gpa));
            MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
            return "Employee " + employeeID + " was registered successfully.";
        } else {
            throw new InvalidGPAException(gpa);
        }

    }

    public double getNetSalary(String employeeID) throws Exception {
        return storage.getEmployee(employeeID).getNetSalary();
    }

    public String createDirectorEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        produceCreationExceptionMessage(employeeID, employeeName, grossSalary);
        if (degree.equals(EmployeeDegree.BSc.toString()) || degree.equals(EmployeeDegree.MSc.toString()) || degree.equals(EmployeeDegree.PhD.toString())) {
            storage.getEmployeeMap().put(employeeID, new EmployeeDirector(employeeID, employeeName, grossSalary, degree, dept));
            MenuUtility.print("ID: " + employeeID + " Name: " + employeeName + " Salary: " + grossSalary);
            return "Employee " + employeeID + " was registered successfully.";
        } else {
            throw new InvalidDegreeException();
        }

    }

    public String removeEmployee(String empID) throws Exception {
        if (storage.getEmployeeMap().containsKey(empID)) {
            storage.getEmployeeMap().remove(empID);
            return "Employee " + empID + " was successfully removed.";
        } else {
            throw new IDNotFoundException(empID);
        }
    }

    public String printAllEmployees() throws Exception {

        if (!storage.getEmployeeMap().isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("All registered employees:");
            for (EmployeeRegular employee : storage.getEmployeeMap().values()) {
                sb.append(employee.toString());
            }
            return sb.toString();
        } else {
            throw new EmployeesNotRegisteredException();
        }
    }

    // Finish getTotalNetSalary and printSortedEmployees -K

    public double getTotalNetSalary() throws Exception {
        return -1.0;
    }

    public String printSortedEmployees() throws Exception {
        return "";
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        if (empID.trim().equals("")) {
            throw new InputEmptyException("ID cannot be blank.");
        } else if (newName.trim().equals("")) {
            throw new InputEmptyException("Name cannot be blank.");
        } else {
            storage.getEmployee(empID).setName(newName);
            return "Employee " + empID + " was updated successfully";
        }
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
