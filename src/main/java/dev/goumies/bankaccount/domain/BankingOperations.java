package dev.goumies.bankaccount.domain;

import java.util.ArrayList;
import java.util.List;

class BankingOperations {

    private List<BankingOperation> operations;

    BankingOperations(BankingOperation operation) {
        this.operations = new ArrayList<>();
        operations.add(operation);
    }

    void add(BankingOperation deposit) {
        operations.add(deposit);
    }

    Balance calculateBalance() {
        Balance balance = Balance.valueOf(0);
        operations.forEach(operation -> balance.plus(operation.getAmount()));
        return balance;
    }
}
