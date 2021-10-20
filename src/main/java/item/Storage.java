package item;

import employees.EmployeeRegular;
import exceptions.IDNotFoundException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Storage {

    // No getter has been implemented because only the Storage class should be accessing usedIDs.
    private final List<String> usedIDs = new ArrayList<>();

    // TODO Rename to employeeMap? -K
    private final LinkedHashMap<String, EmployeeRegular> employeeList = new LinkedHashMap<>();

    private final LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();

    private final LinkedHashMap<String, List<Transaction>> transactionMap = new LinkedHashMap<>();


    public LinkedHashMap<String, Item> getItemMap(){
        return itemMap;
    }

    public List<String> getUsedIDs() {
        return usedIDs;
    }

    public Item getItem(String itemID) {
        return getItemMap().get(itemID);
    }

    public boolean checkForUsedID(String itemID) {
        return usedIDs.contains(itemID);
    }

    public List<Transaction> getItemTransactionList(String itemID) {
        return transactionMap.get(itemID);
    }

    public LinkedHashMap<String, List<Transaction>> getTransactionMap() {
        return transactionMap;
    }

    public String setTransactionList(String itemID, List<Transaction> transactionList) {
        transactionMap.put(itemID, transactionList);
        return "updated successfully";
    }

    public LinkedHashMap<String, EmployeeRegular> getEmployeeMap() {
        return employeeList;
    }

    public EmployeeRegular getEmployee(String employeeID) throws Exception {
        if (getEmployeeMap().containsKey(employeeID)) {
            return getEmployeeMap().get(employeeID);
        } else {
            throw new IDNotFoundException(employeeID);
        }
    }
}
