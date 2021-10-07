package Item;

import utility.MenuUtility;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Transaction {

    private final Item item;
    private final int amount;
    private final BigDecimal transactionCost;

    public Transaction(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        if (amount > 4) {
            BigDecimal discountPrice = item.getItemPrice().multiply(BigDecimal.valueOf(0.7));
            MenuUtility.sout("" + discountPrice);
            this.transactionCost = BigDecimal.valueOf(4).multiply(item.getItemPrice())
                                    .add(BigDecimal.valueOf(amount - 4).multiply(discountPrice))
                                    .setScale(2, RoundingMode.FLOOR);
            MenuUtility.sout("" + transactionCost);
        } else {
            this.transactionCost = BigDecimal.valueOf(amount).multiply(item.getItemPrice())
                                    .setScale(2, RoundingMode.FLOOR);
        }
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getTransactionCost() {
        return transactionCost;
    }
}
