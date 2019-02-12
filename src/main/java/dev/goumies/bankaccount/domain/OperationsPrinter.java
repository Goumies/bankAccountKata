package dev.goumies.bankaccount.domain;

import java.time.LocalDate;

class OperationsPrinter {

    private static final String STATEMENT_HEADER = String.format("| %-10s | %-20s | %-20s |%n", "DATE", "CREDIT", "DEBIT");

    OperationsPrinter() {
    }

    String generateStatementFor(Operations operations, boolean hasHeader) {
        StringBuilder accountStatement = generateStatementHeader(hasHeader);
        operations.getCollection()
                .forEach(operation -> accountStatement.append(generateOperationStatement(operation)));
        return accountStatement.toString();
    }

    private StringBuilder generateStatementHeader(boolean hasHeader) {
        if (hasHeader) {
            return new StringBuilder(STATEMENT_HEADER);
        }
        return new StringBuilder();
    }

    String generateOperationStatement(BankingOperation operation) {
        if (operation.isAWithdrawal())
            return String.format(" | %tF | %-20s | %-20s |%n", LocalDate.now(), "", operation.printAmount());
        return String.format(" | %tF | %-20s | %-20s |%n", LocalDate.now(), operation.printAmount(), "");
    }
}
