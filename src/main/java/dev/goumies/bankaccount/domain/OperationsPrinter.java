package dev.goumies.bankaccount.domain;

import java.util.List;

public class OperationsPrinter {
    private final Account account;

    public OperationsPrinter(Account account) {
        this.account = account;
    }

    public String generateStatement(List<BankingOperation> operations) {
        throw new UnsupportedOperationException();
    }

    public String generateOperationStatement(BankingOperation operation) {
        return operation.getDate().toString() + " " + String.format("%d", operation.getAmount().getValue());
    }
}
