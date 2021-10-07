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
            MenuUtility.sout("" + discountPrice);
            this.transactionCost = ((4 * item.getItemPrice()) + ((amount - 4) * discountPrice));
            MenuUtility.sout("" + transactionCost);
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
}
