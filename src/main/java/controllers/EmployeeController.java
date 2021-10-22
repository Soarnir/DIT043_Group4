package controllers;

import employees.*;
import exceptions.*;
import utility.Storage;
import utility.MenuUtility;

import java.util.*;

public class EmployeeController {

    private final Storage storage;
    private final List<String> employeeDegreeList = new ArrayList<>();
    private final List<String> validDepartments = new ArrayList<>();

    /*
     * The controller constructor passes through the same Storage reference from the Facade to be used by the controllers methods
     */
    public EmployeeController(Storage storage) {
        this.storage = storage;

        for (EmployeeDegree employeeDegree : EmployeeDegree.values()) {
            employeeDegreeList.add(employeeDegree.toString());
        }
        for (EmployeeDepartment employeeDepartment : EmployeeDepartment.values()) {
            validDepartments.add(employeeDepartment.getDepartmentName());
        }
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        produceCreationExceptionMessage(employeeID, employeeName, grossSalary);
        storage.getEmployeeMap().put(employeeID, new EmployeeRegular(employeeID, employeeName, grossSalary));
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String createManagerEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        produceCreationExceptionMessage(employeeID, employeeName, grossSalary);
        if (employeeDegreeList.contains(degree)) {
            storage.getEmployeeMap().put(employeeID, new EmployeeManager(employeeID, employeeName, grossSalary, degree));
        } else {
            throw new InvalidDegreeException();
        }
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String createInternEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        produceCreationExceptionMessage(employeeID, employeeName, grossSalary);
        if (gpa >= 0 && gpa <= 10) {
            storage.getEmployeeMap().put(employeeID, new EmployeeIntern(employeeID, employeeName, grossSalary, gpa));
        } else {
            throw new InvalidGPAException(gpa);
        }
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String createDirectorEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        produceCreationExceptionMessage(employeeID, employeeName, grossSalary);
        if (!employeeDegreeList.contains(degree.trim())) {
            throw new InvalidDegreeException();
        } else if (!validDepartments.contains(dept.trim())) {
            throw new InvalidDepartmentException();
        } else {
            storage.getEmployeeMap().put(employeeID, new EmployeeDirector(employeeID, employeeName, grossSalary, degree, dept));
        }
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String removeEmployee(String empID) throws Exception {
        if (storage.getEmployeeMap().containsKey(empID)) {
            storage.getEmployeeMap().remove(empID);
        } else {
            throw new EmployeeNotRegisteredException(empID);
        }
        return "Employee " + empID + " was successfully removed.";
    }

    public double getTotalNetSalary() throws EmployeesNotRegisteredException {
        double totalNetSalary = 0;

        if (!storage.getEmployeeMap().isEmpty()) {
            for (EmployeeRegular employee : storage.getEmployeeMap().values()) {
                totalNetSalary += employee.getNetSalary();
            }
        } else {
            throw new EmployeesNotRegisteredException();
        }
        return MenuUtility.doubleTruncate(totalNetSalary, 2);
    }

    public double getNetSalary(String employeeID) throws Exception {
        return storage.getEmployee(employeeID).getNetSalary();
    }

    // Will refactor this method after testing out functionality -K
    // I need to cite the Math Course Script as the pseudocode was referred to while making this.

    public EmployeeRegular[] getSortedEmployees() {
        // https://stackoverflow.com/questions/1090556/java-how-to-convert-hashmapstring-object-to-array
        EmployeeRegular[] arrayOfEmployees = storage.getEmployeeMap().values().toArray(new EmployeeRegular[0]);

        for (int i = 0; i < arrayOfEmployees.length - 1; i++) {
            int indexOfLowestSalary = i;

            for (int j = i + 1; j < arrayOfEmployees.length; j++) {
                if (arrayOfEmployees[j].getGrossSalary() < arrayOfEmployees[indexOfLowestSalary].getGrossSalary()) {
                    indexOfLowestSalary = j;
                }
            }
            if (indexOfLowestSalary != i) {
                EmployeeRegular initialEmployeeWithLowestSalary = arrayOfEmployees[indexOfLowestSalary];
                arrayOfEmployees[indexOfLowestSalary] = arrayOfEmployees[i];
                arrayOfEmployees[i] = initialEmployeeWithLowestSalary;
            }
        }
        return arrayOfEmployees;
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        Map<String, Integer> degreeMap = new HashMap<>();

        if (storage.getEmployeeMap().isEmpty()) {
            throw new EmployeesNotRegisteredException();
        } else {
            int BScDegrees = 0, MScDegrees = 0, PhDDegrees = 0;
            for (EmployeeRegular employee : storage.getEmployeeMap().values()) {
                if (employee instanceof EmployeeManager) {
                    switch (((EmployeeManager) employee).getDegree()) {
                        case BSc:
                            BScDegrees += 1;
                            break;
                        case MSc:
                            MScDegrees += 1;
                            break;
                        case PhD:
                            PhDDegrees += 1;
                            break;
                    }
                }
            }
            if (BScDegrees > 0)
                degreeMap.put("BSc", BScDegrees);
            if (MScDegrees > 0)
                degreeMap.put("MSc", MScDegrees);
            if (PhDDegrees > 0)
                degreeMap.put("PhD", PhDDegrees);
        }
        return degreeMap;
    }

    public String printSortedEmployees() throws Exception {
        StringBuilder sb = new StringBuilder();

        if (!storage.getEmployeeMap().isEmpty()) {
            // sb.append("All registered employees:").append(MenuUtility.EOL);
            // Tests require not above comment but the "Employees sorted by gross salary (ascending order):"
            sb.append("Employees sorted by gross salary (ascending order):").append(MenuUtility.EOL);
            EmployeeRegular[] arrayOfEmployees = getSortedEmployees();

            for (EmployeeRegular employee : arrayOfEmployees) {
                sb.append(employee.toString()).append(MenuUtility.EOL);
            }
        } else {
            throw new EmployeesNotRegisteredException();
        }
        return sb.toString();
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        if (empID.trim().equals("")) {
            throw new InputEmptyException("ID cannot be blank.");
        } else if (newName.trim().equals("")) {
            throw new InputEmptyException("Name cannot be blank.");
        } else {
            storage.getEmployee(empID).setName(newName);
        }
        return "Employee " + empID + " was updated successfully";
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        if (newGPA < 0 || newGPA > 10) {
            throw new InvalidGPAException(newGPA);
        } else if (storage.getEmployee(empID) == null) {
            throw new EmployeeNotRegisteredException(empID);
        } else {
            EmployeeIntern employeeIntern = (EmployeeIntern) storage.getEmployee(empID);
            employeeIntern.setGPA(newGPA);
        }
        return "Employee " + empID + " was updated successfully";
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        if (storage.getEmployee(empID) == null) {
            throw new EmployeeNotRegisteredException(empID);
        } else if (employeeDegreeList.contains(newDegree)) {
            EmployeeManager employeeManager = (EmployeeManager) storage.getEmployee(empID);
            employeeManager.setDegree(newDegree);
        } else {
            throw new InvalidDegreeException();
        }
        return "Employee " + empID + " was updated successfully";
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        if (storage.getEmployee(empID) == null) {
            throw new EmployeeNotRegisteredException(empID);
        } else if (validDepartments.contains(newDepartment)) {
            EmployeeDirector employeeDirector = (EmployeeDirector) storage.getEmployee(empID);
            employeeDirector.setDepartment(newDepartment);
        } else {
            throw new InvalidDepartmentException();
        }
        return "Employee " + empID + " was updated successfully";
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        if (storage.getEmployee(empID) == null) {
            throw new EmployeeNotRegisteredException(empID);
        } else if (newSalary <= 0) {
            throw new InvalidSalaryException();
        } else {
            storage.getEmployee(empID).setRawSalary(newSalary);
        }
        return "Employee " + empID + " was updated successfully";
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        checkEmployeeExists(empID);
        validateDegree(degree);

        EmployeeRegular employeeToPromote = storage.getEmployee(empID);
        removeEmployee(empID);
        createManagerEmployee(empID, employeeToPromote.getName(), employeeToPromote.getRawSalary(), degree);
        return empID + " promoted successfully to Manager.";
    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        checkEmployeeExists(empID);
        validateDegree(degree);
        validateDepartment(department);

        EmployeeRegular employeeToPromote = storage.getEmployee(empID);
        removeEmployee(empID);
        createDirectorEmployee(empID, employeeToPromote.getName(), employeeToPromote.getRawSalary(), degree, department);
        return empID + " promoted successfully to Director.";
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        checkEmployeeExists(empID);
        validateGPA(gpa);

        EmployeeRegular employeeToPromote = storage.getEmployee(empID);
        removeEmployee(empID);
        createInternEmployee(empID, employeeToPromote.getName(), employeeToPromote.getRawSalary(), gpa);
        return empID + " promoted successfully to Intern.";
    }

    public String printEmployee(String employeeID) throws Exception {
        return storage.getEmployee(employeeID).toString();
    }

    public String printAllEmployees() throws Exception {
        StringBuilder sb = new StringBuilder();
        if (!storage.getEmployeeMap().isEmpty()) {
            sb.append("All registered employees:").append(MenuUtility.EOL);
            for (EmployeeRegular employee : storage.getEmployeeMap().values()) {
                sb.append(employee.toString()).append(MenuUtility.EOL);
            }
        } else {
            throw new EmployeesNotRegisteredException();
        }
        return sb.toString();
    }

    // TODO Need to decide on a better name
    public void produceCreationExceptionMessage(String employeeID, String employeeName, double grossSalary) throws Exception {
        if (employeeID.trim().isEmpty()) {
            throw new InputEmptyException("ID cannot be blank.");
        } else if (employeeName.trim().isEmpty()) {
            throw new InputEmptyException("Name cannot be blank.");
        } else if (grossSalary <= 0) {
            throw new InvalidSalaryException();
        }
    }

    public void checkEmployeeExists(String empID) throws EmployeeNotRegisteredException {
        if (storage.getEmployee(empID) == null) {
            throw new EmployeeNotRegisteredException(empID);
        }
    }

    public void validateDegree(String degree) throws InvalidDegreeException {
        if (!employeeDegreeList.contains(degree)) {
            throw new InvalidDegreeException();
        }
    }

    public void validateDepartment(String department) throws InvalidDepartmentException {
        if (!validDepartments.contains(department)) {
            throw new InvalidDepartmentException();
        }
    }

    public void validateGPA(int gpa) throws InvalidGPAException {
        if (gpa < 0 || gpa > 10) {
            throw new InvalidGPAException(gpa);
        }
    }
}