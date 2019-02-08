package dev.goumies.bankaccount.domain;

class Account {
    private Money previousBalance;
    private Money newBalance;
    private Money lastDeposit;

    Account(Money initialBalance) {
        this.previousBalance = initialBalance;
        this.newBalance = Money.valueOf(0);
        this.lastDeposit = Money.valueOf(0);
    }

    void deposit(Money amount) throws IllegalArgumentException {
        if (amount.isGreaterThan(Money.valueOf(0))) {
            saveLastDeposit(amount);
            newBalance = this.previousBalance.plus(amount);
        }
    }

    private void saveLastDeposit(Money amount) {
        lastDeposit = amount;
    }

    boolean hasAddedLastDeposit(Money amount) {
        return newBalance.minus(previousBalance).equals(amount);
    }

    void withdraw(Money amount) {
        if (newBalance.isGreaterThan(amount)) {
            saveLastDeposit(amount);
            newBalance = this.previousBalance.minus(amount);
        }
    }

    public boolean hasSubstractedLastWithdraw(Money amount) {
        return newBalance.plus(amount).equals(previousBalance);
    }
}
