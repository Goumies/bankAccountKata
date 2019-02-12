package dev.goumies.bankaccount.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dev.goumies.bankaccount.domain.BankingOperation.anOperation;

class Account {
    private Money previousBalance;
    private Money newBalance;
    private Money lastDeposit;
    private List<BankingOperation> operations;

    Account(Money initialBalance) {
        this.previousBalance = initialBalance;
        this.operations = new ArrayList<>();
        this.newBalance = Money.valueOf(0);
        this.lastDeposit = Money.valueOf(0);
    }

    void deposit(Money amount) {
        if (amount.isGreaterThan(Money.valueOf(0))) {
            saveLastDeposit(amount);
            operations.add(anOperation().withADate(LocalDate.now()).withAnAmount(amount).build());
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

    boolean hasSubtractedLastWithdraw(Money amount) {
        return newBalance.plus(amount).equals(previousBalance);
    }

    List<BankingOperation> getOperations() {
        return operations;
    }

    BankingOperation getLastOperation() {
        if (operations.size() > 0) {
            int indexOfLastOperation = operations.size() - 1;
            return operations.get(indexOfLastOperation);
        }
        return anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(0)).build();
    }
}
