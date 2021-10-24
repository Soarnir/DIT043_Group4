package items;

import utility.MenuUtility;

public class Transaction {

    private final String ITEM_ID;
    private final int AMOUNT;
    private final double TRANSACTION_COST;

    /*
     * In order to reduce Storage calls, the constructor accepts an Item reference in order to store the item ID
     * and perform the cost calculations for the transaction.
     */
    public Transaction(Item item, int amount) {
        this.ITEM_ID = item.getItemID();
        this.AMOUNT = amount;
        if (amount > 4) {
            double discountPrice = item.getItemPrice() * 0.7;
            this.TRANSACTION_COST = ((4 * item.getItemPrice()) + ((amount - 4) * discountPrice));
        } else {
            this.TRANSACTION_COST = amount * item.getItemPrice();
        }
    }

    public int getAmount() {
        return AMOUNT;
    }

    public double getTransactionCost() {
        return TRANSACTION_COST;
    }

    @Override
    public String toString() {
        return ITEM_ID + ": " + AMOUNT + " item(s). " + MenuUtility.doubleFormat(TRANSACTION_COST) + " SEK";
    }
}