package dev.goumies.bankaccount.domain;

import java.util.List;

class Operations {
    private List<BankingOperation> bankingOperations;

    Operations(List<BankingOperation> bankingOperations) {
        this.bankingOperations = bankingOperations;
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
}
