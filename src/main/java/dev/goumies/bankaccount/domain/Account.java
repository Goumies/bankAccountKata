package dev.goumies.bankaccount.domain;

import java.time.LocalDate;
import java.util.Objects;

import static dev.goumies.bankaccount.domain.BankingOperation.anOperation;

class Account {
    private final Money previousBalance;
    private Money newBalance;
    private final Operations operations;

    Account(Money initialBalance) {
        this.previousBalance = initialBalance;
        this.operations = new Operations(anOperation().withADate(LocalDate.now()).withAnAmount(initialBalance).withType(Type.DEPOSIT).build());
        this.newBalance = initialBalance;
    }

    void deposit(Money amount) {
        if (amount.isEnoughFor(Money.valueOf(0))) {
            addDepositToOperations(amount);
            newBalance = this.previousBalance.plus(amount);
        }
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

    BankingOperation getLastOperation() {
        int indexOfLastOperation = operations.size() - 1;
        return operations.get(indexOfLastOperation);
    }

    Operations getAllOperations() {
        return operations;
    }

    Operations getAllWithdrawals() {
        return operations.getWithdrawals();
    }

    Money getBalance() {
        return operations.getBalance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(previousBalance, account.previousBalance) &&
                Objects.equals(newBalance, account.newBalance) &&
                Objects.equals(operations, account.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousBalance, newBalance, operations);
    }
}
