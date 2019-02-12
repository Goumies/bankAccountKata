package dev.goumies.bankaccount.domain;

import java.time.LocalDate;
import java.util.List;

class OperationsPrinter {
    private final Account account;

    OperationsPrinter(Account account) {
        this.account = account;
    }

    String generateStatement(List<BankingOperation> operations) {
        StringBuilder accountStatement = new StringBuilder();
        operations.forEach(operation -> accountStatement.append(generateOperationStatement(operation)));
        return accountStatement.toString();
    }

    String generateOperationStatement(BankingOperation operation) {
        return String.format("%tF | %d%n", LocalDate.now(), operation.getAmount().getValue());
    }
}
