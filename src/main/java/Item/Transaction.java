package Item;

import utility.MenuUtility;

public class Transaction {

    private final Item item;
    private final int amount;
    private final double transactionCost;

    public Transaction(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        if (amount > 4) {
            double discountPrice = item.getItemPrice() * 0.7;
            MenuUtility.print("" + discountPrice);
            this.transactionCost = ((4 * item.getItemPrice()) + ((amount - 4) * discountPrice));
            MenuUtility.print("" + transactionCost);
        } else {
            this.transactionCost = amount * item.getItemPrice();
        }
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public double getTransactionCost() {
        return transactionCost;
    }

    @Override
    public String toString() {
        return item.getItemID() + ": " + amount + " item(s). " + transactionCost + " SEK";
    }
}
