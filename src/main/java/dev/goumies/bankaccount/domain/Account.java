package dev.goumies.bankaccount.domain;

import java.time.LocalDate;

import static dev.goumies.bankaccount.domain.BankingOperation.anOperation;

class Account {
    private Money previousBalance;
    private Money newBalance;
    private Money lastDeposit;
    private Operations operations;

    Account(Money initialBalance) {
        this.previousBalance = initialBalance;
        this.operations = new Operations(anOperation().withADate(LocalDate.now()).withAnAmount(initialBalance).withType(Type.DEPOSIT).build());
        this.newBalance = initialBalance;
        this.lastDeposit = Money.valueOf(0);
    }

    void deposit(Money amount) {
        if (amount.isEnoughFor(Money.valueOf(0))) {
            saveLastDeposit(amount);
            addDepositToOperations(amount);
            newBalance = this.previousBalance.plus(amount);
        }
    }

    private void saveLastDeposit(Money amount) {
        lastDeposit = amount;
    }

    boolean hasAddedLastDeposit(Money amount) {
        return newBalance.minus(previousBalance).equals(amount);
    }

    private void addDepositToOperations(Money amount) {
        operations.add(anOperation().withADate(LocalDate.now()).withAnAmount(amount).withType(Type.DEPOSIT).build());
    }

    void withdraw(Money amount) {
        if (newBalance.isEnoughFor(amount)) {
            addWithdrawalToOperations(amount);
            newBalance = this.previousBalance.minus(amount);
        }
    }

    private void addWithdrawalToOperations(Money amount) {
        operations.add(anOperation().withADate(LocalDate.now()).withAnAmount(amount).withType(Type.WITHDRAWAL).build());
    }

    boolean hasSubtractedLastWithdraw(Money amount) {
        return newBalance.plus(amount).equals(previousBalance);
    }

    BankingOperation getLastOperation() {
        if (!operations.isEmpty()) {
            int indexOfLastOperation = operations.size() - 1;
            return operations.get(indexOfLastOperation);
        }
        return anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(0)).build();
    }

    Operations getAllOperations() {
        return operations.getAll();
    }

    Operations getAllWithdrawals() {
        return operations.getWithdrawals();
    }

    Money getBalance() {
        return operations.getBalance(previousBalance);
    }
}
