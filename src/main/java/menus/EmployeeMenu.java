package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class EmployeeMenu {

    //Utility class declaration
    Facade facade;

    /*
     * Constructor for EmployeeMenu
     * Requires Facade passed through
     */
    public EmployeeMenu(Facade facade) {
        this.facade = facade;
    }

    //Menu options
    final int CREATE_EMPLOYEE_REGULAR = 1;
    final int CREATE_EMPLOYEE_MANAGER = 2;
    final int CREATE_EMPLOYEE_DIRECTOR = 3;
    final int CREATE_EMPLOYEE_INTERN = 4;
    final int REMOVE_EMPLOYEE = 5;
    final int PRINT_EMPLOYEE = 6;
    final int PRINT_EMPLOYEE_ALL = 7;
    final int PRINT_TOTAL_EXPENSE = 8;
    final int PRINT_EMPLOYEE_SORTED = 9;

    //Menu text
    final String EMPLOYEE_MENU_OPTIONS = "Employee options menu:" + MenuUtility.EOL +
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
     * Enter item menu loop, error handling is managed by Input class
     * User stays in loop even when accessing menu options, exit is only provided upon invalid input or 0
     */
    public void printMenu() {
        boolean loop = true;
        do {
            MenuUtility.print(EMPLOYEE_MENU_OPTIONS);
            int chosenMenuOption = Input.readMenuInt(0, 9);
            switch (chosenMenuOption) {
                case CREATE_EMPLOYEE_REGULAR:

                    break;
                case CREATE_EMPLOYEE_MANAGER:

                    break;
                case CREATE_EMPLOYEE_DIRECTOR:

                    break;
                case CREATE_EMPLOYEE_INTERN:

                    break;
                case REMOVE_EMPLOYEE:

                    break;
                case PRINT_EMPLOYEE:

                    break;
                case PRINT_EMPLOYEE_ALL:

                    break;
                case PRINT_TOTAL_EXPENSE:

                    break;
                case PRINT_EMPLOYEE_SORTED:

                    break;
                default:
                    loop = false;
            }
        } while (loop);
    }

}
