package utility;

import employees.EmployeeRegular;
import exceptions.EmployeeNotFoundException;
import items.Item;
import items.Transaction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Storage {

    // Final attributes not capitalized as they are collections
    private final List<String> usedItemIDs;

    private final LinkedHashMap<String, EmployeeRegular> employeeMap;

    private final LinkedHashMap<String, Item> itemMap;

    private final LinkedHashMap<String, List<Transaction>> transactionMap;

    public Storage() {
        usedItemIDs = new ArrayList<>();
        employeeMap = new LinkedHashMap<>();
        itemMap = new LinkedHashMap<>();
        transactionMap = new LinkedHashMap<>();
    }
    /*------------------------------------------ Items ------------------------------------------*/
    public LinkedHashMap<String, Item> getItemMap() {
        return itemMap;
    }

    public List<String> getUsedItemIDs() {
        return usedItemIDs;
    }

    public Item getItem(String itemID) {
        return itemMap.get(itemID);
    }

    public void addItem(String itemID, Item item) {
        usedItemIDs.add(itemID);
        itemMap.put(itemID, item);
    }

    public void removeItem(String itemID) {
        itemMap.remove(itemID);
    }

    public boolean checkForUsedID(String itemID) {
        return usedItemIDs.contains(itemID);
    }

    /*
     * This check is needed by controllers but implementing the method in storage allows the controllers to
     * have the desired functionality (of receiving a boolean value) without accessing the item map itself.
     */
    public boolean isItemMapEmpty(){
        return itemMap.isEmpty();
    }

    /*-------------------------------------- Transactions ---------------------------------------*/
    public List<Transaction> getItemTransactionList(String itemID) {
        return transactionMap.get(itemID);
    }

    public LinkedHashMap<String, List<Transaction>> getTransactionMap() {
        return transactionMap;
    }

    /*
     * This check is needed by transaction controller but implementing the method in storage allows it to
     * have the desired functionality (of receiving a boolean value) without accessing the transaction map itself.
     */
    public boolean isTransactionMapEmpty(){
        return transactionMap.isEmpty();
    }
    /*---------------------------------------- Employees ----------------------------------------*/
    public LinkedHashMap<String, EmployeeRegular> getEmployeeMap() {
        return employeeMap;
    }

    public EmployeeRegular getEmployee(String employeeID) throws EmployeeNotFoundException {
        EmployeeRegular employeeRegular;
        if (getEmployeeMap().containsKey(employeeID)) {
            employeeRegular = getEmployeeMap().get(employeeID);
        } else {
            throw new EmployeeNotFoundException(employeeID);
        }
        return employeeRegular;
    }

    public void addEmployee(String employeeID, EmployeeRegular employee) {
        employeeMap.put(employeeID, employee);
    }

    public void removeEmployee(String employeeID) {
        employeeMap.remove(employeeID);
    }
}
