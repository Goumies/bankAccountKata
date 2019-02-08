package dev.goumies.bankaccount.domain;

public class Account {
    private Money previousBalance;
    private Money newBalance;
    private Money lastDeposit;

    public Account(Money initialBalance) {
        this.previousBalance = initialBalance;
        this.newBalance = Money.valueOf(0);
        this.lastDeposit = Money.valueOf(0);
    }

    public Money getBalance() {
        return newBalance;
    }

    public Money getPreviousBalance() {
        return previousBalance;
    }

    public void deposit(Money amount) throws IllegalArgumentException {
        if (amount.isGreaterThan(Money.valueOf(0))) {
            saveLastDeposit(amount);
            newBalance = this.previousBalance.plus(amount);
        }
    }

    private void saveLastDeposit(Money amount) {
        lastDeposit = amount;
    }

    public boolean hasAddedLastDeposit(Money amount) {
        return newBalance.minus(previousBalance).equals(amount);
    }
}
