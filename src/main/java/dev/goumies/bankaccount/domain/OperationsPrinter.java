package dev.goumies.bankaccount.domain;

import java.time.LocalDate;

class OperationsPrinter {

    private static final String STATEMENT_HEADER = String.format("| %-10s | %-20s | %-20s |%n", "DATE", "CREDIT", "DEBIT");
    private static final String STATEMENT_FOOTER_HEADER = String.format("| %-50s |%n", "BALANCE");
    private final Account account;

    OperationsPrinter(Account account) {
        this.account = account;
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
            return String.format(" | %tF | %-20s | %-20s |%n", LocalDate.now(), "", MoneyPrinter.printAmount(operation.amount));
        return String.format(" | %tF | %-20s | %-20s |%n", LocalDate.now(), MoneyPrinter.printAmount(operation.amount), "");
    }

    String addFooter(String statement) {
        StringBuilder stringBuilder = new StringBuilder(statement).append(STATEMENT_FOOTER_HEADER);
        Money balance = account.getBalance();
        return stringBuilder.append(String.format("| %-50s |%n", MoneyPrinter.printAmount(balance))).toString();
    }
}
