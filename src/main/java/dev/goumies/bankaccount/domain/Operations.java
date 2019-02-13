package dev.goumies.bankaccount.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Operations {
    private final List<BankingOperation> bankingOperations;

    private Operations(List<BankingOperation> bankingOperations) {
        this.bankingOperations = bankingOperations;
    }

    Operations(BankingOperation... bankingOperations) {
        this.bankingOperations = Stream.of(bankingOperations).collect(Collectors.toList());
    }

    boolean isEmpty() {
        return bankingOperations.isEmpty();
    }

    void add(BankingOperation bankingOperation) {
        bankingOperations.add(bankingOperation);
    }

    BankingOperation get(int indexOfTheOperation) {
        return bankingOperations.get(indexOfTheOperation);
    }

    int size() {
        return bankingOperations.size();
    }

    Operations getAll() {
        return this;
    }

    Operations getWithdrawals() {
        return new Operations(bankingOperations.stream()
                .filter(BankingOperation::isAWithdrawal)
                .collect(Collectors.toList()));
    }

    List<BankingOperation> getCollection() {
        return bankingOperations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operations that = (Operations) o;
        return Objects.equals(bankingOperations, that.bankingOperations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankingOperations);
    }

    Money getBalance() {
        Money totalCredit = bankingOperations.stream()
                .filter(BankingOperation::isADeposit)
                .reduce(Money.valueOf(0),
                        (amount, bankingOperation) -> amount.plus(bankingOperation.getAmount()),
                        Money::plus);
        Money totalDebit = bankingOperations.stream()
                .filter(BankingOperation::isAWithdrawal)
                .reduce(Money.valueOf(0),
                        (amount, bankingOperation) -> amount.plus(bankingOperation.getAmount()),
                        Money::plus);
        return totalCredit.minus(totalDebit);
    }
}
