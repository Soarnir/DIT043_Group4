package controllers;

import employees.*;
import exceptions.*;
import utility.Storage;
import utility.MenuUtility;

import java.util.*;

public class EmployeeController {

    // Final attributes not capitalized as they are collections
    private final Storage storage;
    private final List<String> employeeDegreeList;
    private final List<String> validDepartments;

    /*
     * The controller constructor passes through the same Storage reference from the Facade
     * to be used by the controllers' methods.
     */
    public EmployeeController(Storage storage) {
        this.storage = storage;
        this.employeeDegreeList = new ArrayList<>();
        this.validDepartments = new ArrayList<>();

        /*
         * Initialize employee degree and department lists,
         * basing them on enums allows for great extensibility with very minor refactoring.
         */
        for (EmployeeDegree employeeDegree : EmployeeDegree.values()) {
            employeeDegreeList.add(employeeDegree.toString());
        }
        for (EmployeeDepartment employeeDepartment : EmployeeDepartment.values()) {
            validDepartments.add(employeeDepartment.getDepartmentName());
        }
    }

    /*
     * Creation of employees utilize several validation methods to reduce complexity
     * and improve readability, keeping the overall controller DRY.
     */
    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        validateRegularEmployee(employeeID, employeeName, grossSalary);
        storage.addEmployee(employeeID, new EmployeeRegular(employeeID, employeeName, grossSalary));
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String createManagerEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        validateRegularEmployee(employeeID, employeeName, grossSalary);
        if (validateDegree(degree)) {
            storage.addEmployee(employeeID, new EmployeeManager(employeeID, employeeName, grossSalary, degree));
        }
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String createInternEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        validateRegularEmployee(employeeID, employeeName, grossSalary);
        if (validateGPA(gpa)) {
            storage.addEmployee(employeeID, new EmployeeIntern(employeeID, employeeName, grossSalary, gpa));
        }
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String createDirectorEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        validateRegularEmployee(employeeID, employeeName, grossSalary);
        if (validateDegree(degree) && validateDepartment(dept)) {
            storage.addEmployee(employeeID, new EmployeeDirector(employeeID, employeeName, grossSalary, degree, dept));
        }
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String removeEmployee(String empID) throws EmployeeNotFoundException {
        if (checkEmployeeExists(empID)) {
            storage.removeEmployee(empID);
        }
        return "Employee " + empID + " was successfully removed.";
    }

    public double getTotalNetSalary() throws EmployeesNotRegisteredException {
        double totalNetSalary = 0;

        if (checkEmployeesRegistered()) {
            for (EmployeeRegular employee : storage.getEmployeeMap().values()) {
                totalNetSalary += employee.getNetSalary();
            }
        }
        return MenuUtility.doubleTruncate(totalNetSalary, 2);
    }

    public double getNetSalary(String employeeID) throws EmployeeNotFoundException {
        return storage.getEmployee(employeeID).getNetSalary();
    }

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
            EmployeeRegular initialEmployeeWithLowestSalary = arrayOfEmployees[indexOfLowestSalary];
            arrayOfEmployees[indexOfLowestSalary] = arrayOfEmployees[i];
            arrayOfEmployees[i] = initialEmployeeWithLowestSalary;
        }
        return arrayOfEmployees;
    }

    public Map<String, Integer> mapEachDegree() throws EmployeesNotRegisteredException {
        Map<String, Integer> degreeMap = new HashMap<>();

        if (checkEmployeesRegistered()) {
            int BScDegrees = 0;
            int MScDegrees = 0;
            int PhDDegrees = 0;
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
            if (BScDegrees > 0) {
                degreeMap.put("BSc", BScDegrees);
            }
            if (MScDegrees > 0) {
                degreeMap.put("MSc", MScDegrees);
            }
            if (PhDDegrees > 0) {
                degreeMap.put("PhD", PhDDegrees);
            }
        }
        return degreeMap;
    }

    public String printSortedEmployees() throws EmployeesNotRegisteredException {
        StringBuilder sb = new StringBuilder();

        if (checkEmployeesRegistered()) {
            sb.append("Employees sorted by gross salary (ascending order):").append(MenuUtility.EOL);
            EmployeeRegular[] arrayOfEmployees = getSortedEmployees();

            for (EmployeeRegular employee : arrayOfEmployees) {
                sb.append(employee.toString()).append(MenuUtility.EOL);
            }
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
        checkEmployeeExists(empID);
        if (validateGPA(newGPA)) {
            EmployeeIntern employeeIntern = (EmployeeIntern) storage.getEmployee(empID);
            employeeIntern.setGPA(newGPA);
        }
        return "Employee " + empID + " was updated successfully";
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        checkEmployeeExists(empID);
        if (validateDegree(newDegree)) {
            EmployeeManager employeeManager = (EmployeeManager) storage.getEmployee(empID);
            employeeManager.setDegree(newDegree);
        }
        return "Employee " + empID + " was updated successfully";
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        checkEmployeeExists(empID);
        if (validateDepartment(newDepartment)) {
            EmployeeDirector employeeDirector = (EmployeeDirector) storage.getEmployee(empID);
            employeeDirector.setDepartment(newDepartment);
        }
        return "Employee " + empID + " was updated successfully";
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        checkEmployeeExists(empID);
        if (newSalary <= 0) {
            throw new InvalidSalaryException();
        } else {
            storage.getEmployee(empID).setRawSalary(newSalary);
        }
        return "Employee " + empID + " was updated successfully";
    }

    /*
     * Promotions utilize helper methods for exception handling
     */
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

    public String printEmployee(String employeeID) throws EmployeeNotFoundException {
        return storage.getEmployee(employeeID).toString();
    }

    public String printAllEmployees() throws Exception {
        StringBuilder sb = new StringBuilder();

        if (checkEmployeesRegistered()) {
            sb.append("All registered employees:").append(MenuUtility.EOL);
            for (EmployeeRegular employee : storage.getEmployeeMap().values()) {
                sb.append(employee.toString()).append(MenuUtility.EOL);
            }
        }
        return sb.toString();
    }

    /*
     * Performs initial validation of common employee attributes and handles throwing of exceptions
     * Additional checks and validation for more specific employee attributes, handling exception throws this way
     * allows for clearer code during employee creation
     */
    // TODO better?
    public void validateRegularEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        if (employeeID.trim().isEmpty()) {
            throw new InputEmptyException("ID cannot be blank.");
        } else if (employeeName.trim().isEmpty()) {
            throw new InputEmptyException("Name cannot be blank.");
        } else if (grossSalary <= 0) {
            throw new InvalidSalaryException();
        }
    }

    public boolean checkEmployeeExists(String empID) throws EmployeeNotFoundException {
        if (storage.getEmployee(empID) == null) {
            throw new EmployeeNotFoundException(empID);
        }
        return true;
    }

    public boolean checkEmployeesRegistered() throws EmployeesNotRegisteredException{
        if (storage.getEmployeeMap().isEmpty()) {
            throw new EmployeesNotRegisteredException();
        }
        return true;
    }

    public boolean validateDegree(String degree) throws InvalidDegreeException {
        if (!employeeDegreeList.contains(degree)) {
            throw new InvalidDegreeException();
        }
        return true;
    }

    public boolean validateDepartment(String department) throws InvalidDepartmentException {
        if (!validDepartments.contains(department)) {
            throw new InvalidDepartmentException();
        }
        return true;
    }

    public boolean validateGPA(int gpa) throws InvalidGPAException {
        if (gpa < 0 || gpa > 10) {
            throw new InvalidGPAException(gpa);
        }
        return true;
    }
}