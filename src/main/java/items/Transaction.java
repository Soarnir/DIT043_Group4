package items;

import utility.MenuUtility;

public class Transaction {

    private final Item item;
    private final int AMOUNT;
    private final double TRANSACTION_COST;

    public Transaction(Item item, int amount) {
        this.item = item;
        this.AMOUNT = amount;
        if (amount > 4) {
            double discountPrice = item.getItemPrice() * 0.7;
            this.TRANSACTION_COST = ((4 * item.getItemPrice()) + ((amount - 4) * discountPrice));
        } else {
            this.TRANSACTION_COST = amount * item.getItemPrice();
        }
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return AMOUNT;
    }

    public double getTransactionCost() {
        return TRANSACTION_COST;
    }

    @Override
    public String toString() {
        return item.getItemID() + ": " + AMOUNT + " item(s). " + MenuUtility.doubleFormat(TRANSACTION_COST) + " SEK";
    }
}
