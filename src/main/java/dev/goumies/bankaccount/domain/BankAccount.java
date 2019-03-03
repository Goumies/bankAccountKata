package dev.goumies.bankaccount.domain;

import static dev.goumies.bankaccount.domain.BankingOperation.aBankingOperation;

final class BankAccount {
    private final Balance balance;
    private final BankingOperations operations;

    BankAccount(Balance balance) {
        this.balance = balance;
        this.operations = new BankingOperations(aBankingOperation().withAmount(balance.getAmount()).withBankingOperationType(BankingOperationType.DEPOSIT).build());
    }

    BankAccount deposit(Money amount) {
        operations.add(aBankingOperation().withAmount(amount).withBankingOperationType(BankingOperationType.DEPOSIT).build());
        return updateBalance(amount);
    }

    private BankAccount updateBalance(Money amount) {
        return new BankAccount(Balance.valueOf(balance.plus(amount)));
    }

    boolean hasBalanceEqualTo(Balance otherBalance) {
        return this.balance.equals(otherBalance);
    }
}
