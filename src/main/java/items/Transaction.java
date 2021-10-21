package items;

import utility.MenuUtility;

public class Transaction {

    private final Item ITEM;
    private final int AMOUNT;
    private final double TRANSACTION_COST;

    public Transaction(Item item, int amount) {
        this.ITEM = item;
        this.AMOUNT = amount;
        if (amount > 4) {
            double discountPrice = item.getItemPrice() * 0.7;
            this.TRANSACTION_COST = ((4 * item.getItemPrice()) + ((amount - 4) * discountPrice));
        } else {
            this.TRANSACTION_COST = amount * item.getItemPrice();
        }
    }

    public Item getItem() {
        return ITEM;
    }

    public int getAmount() {
        return AMOUNT;
    }

    public double getTransactionCost() {
        return TRANSACTION_COST;
    }

    @Override
    public String toString() {
        return ITEM.getItemID() + ": " + AMOUNT + " item(s). " + MenuUtility.doubleFormat(TRANSACTION_COST) + " SEK";
    }
}
