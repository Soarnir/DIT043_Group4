package facade;

import controllers.EmployeeController;
import controllers.ItemController;
import controllers.ReviewController;
import controllers.TransactionController;
import utility.Storage;

import java.util.List;
import java.util.Map;

public class Facade {

    private final Storage storage;
    private final ItemController itemController;
    private final ReviewController reviewController;
    private final TransactionController transactionController;
    private final EmployeeController employeeController;
    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade() {
        this.storage = new Storage();
        this.itemController = new ItemController(storage);
        this.reviewController = new ReviewController(storage);
        this.transactionController = new TransactionController(storage);
        this.employeeController = new EmployeeController(storage);
    }

    public String createItem(String itemID, String itemName, double unitPrice) {
        return itemController.createItem(itemID, itemName, unitPrice);
    }

    public String printItem(String itemID) {
        return itemController.printItem(itemID);
    }

    public String removeItem(String itemID) {
        return itemController.removeItem(itemID);
    }

    public boolean containsItem(String itemID) {
        return storage.getItemMap().containsKey(itemID);
    }

    public double buyItem(String itemID, int amount) {
        return transactionController.buyItem(itemID, amount);
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        return reviewController.createReview(itemID, reviewComment, reviewGrade);
    }

    public String reviewItem(String itemID, int reviewGrade) {
        return reviewController.createReview(itemID, reviewGrade);
    }

    public String getItemCommentsPrinted(String itemID) {
        return reviewController.getItemCommentsPrinted(itemID);
    }

    public List<String> getItemComments(String itemID) {
        return reviewController.getItemComments(itemID);
    }

    public double getItemMeanGrade(String itemID) {
        return reviewController.getItemMeanGrade(itemID);
    }

    public int getNumberOfReviews(String itemID) {
        return reviewController.getNumberOfReviews(itemID);
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        return reviewController.printReview(itemID, reviewNumber);
    }

    public String getPrintedReviews(String itemID) {
        return reviewController.printAllItemReviews(itemID);
    }

    public String printMostReviewedItems() {
        return reviewController.printMostReviewedItems();
    }

    public List<String> getMostReviewedItems() {
        return reviewController.getMostReviewedItems();
    }

    public List<String> getLeastReviewedItems() {
        return reviewController.getLeastReviewedItems();
    }

    public String printLeastReviewedItems() {
        return reviewController.printLeastReviewedItems();
    }

    public double getTotalProfit() {
        return transactionController.getTotalProfit();
    }

    public String printItemTransactions(String itemID) {
        return transactionController.printItemTransactions(itemID);
    }

    public int getTotalUnitsSold() {
        return transactionController.getTotalUnitsSold();
    }

    public int getTotalTransactions() {
        return transactionController.getTotalTransactions();
    }

    public double getProfit(String itemID) {
        return transactionController.getProfit(itemID);
    }

    public int getUnitsSolds(String itemID) {
        return transactionController.getUnitsSold(itemID);
    }

    public String printAllTransactions() {
        return transactionController.printAllTransactions();
    }

    public String printWorseReviewedItems() {
        return reviewController.printWorseReviewedItems();
    }

    public String printBestReviewedItems() {
        return reviewController.printBestReviewedItems();
    }

    public List<String> getWorseReviewedItems() {
        return reviewController.getWorseReviewedItems();
    }

    public List<String> getBestReviewedItems() {
        return reviewController.getBestReviewedItems();
    }

    public String printAllReviews() {
        return reviewController.printAllReviews();
    }

    public String updateItemName(String itemID, String newName) {
        return itemController.updateItem(itemID, newName);
    }

    public String updateItemPrice(String itemID, double newPrice) {
        return itemController.updateItem(itemID, newPrice);
    }

    public String printAllItems() {
        return itemController.printAllItems();
    }

    public String printMostProfitableItems() {
        return transactionController.printMostProfitableItems();
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        return employeeController.createEmployee(employeeID, employeeName, grossSalary);
    }

    public String printEmployee(String employeeID) throws Exception {
        return employeeController.printEmployee(employeeID);
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        return employeeController.createManagerEmployee(employeeID, employeeName, grossSalary, degree);
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        return employeeController.createInternEmployee(employeeID, employeeName, grossSalary, gpa);
    }

    public double getNetSalary(String employeeID) throws Exception {
        return employeeController.getNetSalary(employeeID);
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        return employeeController.createDirectorEmployee(employeeID, employeeName, grossSalary, degree, dept);
    }

    public String removeEmployee(String empID) throws Exception {
        return employeeController.removeEmployee(empID);
    }

    public String printAllEmployees() throws Exception {
        return employeeController.printAllEmployees();
    }

    public double getTotalNetSalary() throws Exception {
        return employeeController.getTotalNetSalary();
    }

    public String printSortedEmployees() throws Exception {
        return employeeController.printSortedEmployees();
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        return employeeController.updateEmployeeName(empID, newName);
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        return employeeController.updateInternGPA(empID, newGPA);
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        return employeeController.updateManagerDegree(empID, newDegree);
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        return employeeController.updateDirectorDept(empID, newDepartment);
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        return employeeController.updateGrossSalary(empID, newSalary);
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        return employeeController.mapEachDegree();
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        return employeeController.promoteToManager(empID, degree);
    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        return employeeController.promoteToDirector(empID, degree, department);
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        return employeeController.promoteToIntern(empID, gpa);
    }
}