package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class EmployeeMenu {

    private final Facade facade;

    /*
     * Constructor for EmployeeMenu
     * Requires Facade passed through
     */
    public EmployeeMenu(Facade facade) {
        this.facade = facade;
    }

    // Employee Menu options
    private static final int EXIT = 0;
    private static final int CREATE_EMPLOYEE_REGULAR = 1;
    private static final int CREATE_EMPLOYEE_MANAGER = 2;
    private static final int CREATE_EMPLOYEE_DIRECTOR = 3;
    private static final int CREATE_EMPLOYEE_INTERN = 4;
    private static final int REMOVE_EMPLOYEE = 5;
    private static final int PRINT_EMPLOYEE = 6;
    private static final int PRINT_EMPLOYEE_ALL = 7;
    private static final int PRINT_TOTAL_EXPENSE = 8;
    private static final int PRINT_EMPLOYEE_SORTED = 9;

    // Employee Menu text
    private static final String EMPLOYEE_MENU_OPTIONS =
        "Employee options menu:" + MenuUtility.EOL +
        "0. Return to Main Menu." + MenuUtility.EOL +
        "1. Create an employee (Regular Employee)." + MenuUtility.EOL +
        "2. Create an employee (Manager)." + MenuUtility.EOL +
        "3. Create an employee (Director)." + MenuUtility.EOL +
        "4. Create an employee (Intern)." + MenuUtility.EOL +
        "5. Remove an employee." + MenuUtility.EOL +
        "6. Print specific employee." + MenuUtility.EOL +
        "7. Print all registered employees." + MenuUtility.EOL +
        "8. Print the total expense with net salary." + MenuUtility.EOL +
        "9. Print all employees sorted by gross salary." + MenuUtility.EOL + MenuUtility.EOL +
        "Type an option number:";

    /*
     * Enter employee menu loop, error handling is managed by several custom exceptions during action execution,
     * hence generic Exception e used to catch all possible thrown errors.
     * User stays in loop even when accessing menu options, exit is only provided upon invalid non-integer input or 0
     */
    public void printMenu() {
        int chosenMenuOption;
        boolean shouldPrintMenu = true;

        do {
            String employeeID, employeeName, degree, department;
            double grossSalary;
            int gpa;

            if (shouldPrintMenu) {
                System.out.print(EMPLOYEE_MENU_OPTIONS);
            }
            chosenMenuOption = Input.readInt();
            shouldPrintMenu = true;

            switch (chosenMenuOption) {
                case EXIT:
                    break;
                case CREATE_EMPLOYEE_REGULAR:
                    employeeID = Input.readString("ID: ");
                    employeeName = Input.readString("Name: ", true);
                    grossSalary = Input.readDouble("Salary: ");

                    try {
                        System.out.println(facade.createEmployee(employeeID, employeeName, grossSalary));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case CREATE_EMPLOYEE_MANAGER:
                    employeeID = Input.readString("ID: ");
                    employeeName = Input.readString("Name: ", true);
                    grossSalary = Input.readDouble("Salary: ");
                    degree = Input.readString("Degree: ");

                    try {
                        System.out.println(facade.createEmployee(employeeID, employeeName, grossSalary, degree));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case CREATE_EMPLOYEE_DIRECTOR:
                    employeeID = Input.readString("ID: ");
                    employeeName = Input.readString("Name: ", true);
                    grossSalary = Input.readDouble("Salary: ");
                    degree = Input.readString("Degree: ");
                    department = Input.readString("Department: ", true);

                    try {
                        System.out.println(facade.createEmployee(employeeID, employeeName, grossSalary, degree, department));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case CREATE_EMPLOYEE_INTERN:
                    employeeID = Input.readString("ID: ");
                    employeeName = Input.readString("Name: ", true);
                    grossSalary = Input.readDouble("Salary: ");
                    gpa = Input.readInt("GPA: ");

                    try {
                        System.out.println(facade.createEmployee(employeeID, employeeName, grossSalary, gpa));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case REMOVE_EMPLOYEE:
                    employeeID = Input.readString("ID: ");

                    try {
                        System.out.println(facade.removeEmployee(employeeID));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case PRINT_EMPLOYEE:
                    employeeID = Input.readString("ID: ");

                    try {
                        System.out.println(facade.printEmployee(employeeID));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case PRINT_EMPLOYEE_ALL:
                    try {
                        System.out.println(facade.printAllEmployees());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case PRINT_TOTAL_EXPENSE:
                    try {
                        System.out.println(facade.getTotalNetSalary());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case PRINT_EMPLOYEE_SORTED:
                    try {
                        System.out.println(facade.printSortedEmployees());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    shouldPrintMenu = false;
                    System.out.println("Invalid menu option. Please type another option");
            }
        } while (chosenMenuOption != EXIT);
    }
}
