package dev.goumies.bankaccount.domain;

import java.time.LocalDate;

class OperationsPrinter {

    private static final String STATEMENT_HEADER = String.format("| %-10s | %-20s | %-20s |%n", "DATE", "CREDIT", "DEBIT");
    private static final String STATEMENT_FOOTER_HEADER = String.format("| %-50s |%n", "BALANCE");
    private Account account;

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
            return String.format(" | %tF | %-20s | %-20s |%n", LocalDate.now(), "", operation.amount.printAmount());
        return String.format(" | %tF | %-20s | %-20s |%n", LocalDate.now(), operation.amount.printAmount(), "");
    }

    String addFooter(String statement) {
        StringBuilder stringBuilder = new StringBuilder(statement).append(STATEMENT_FOOTER_HEADER);
        Money balance = account.getBalance();
        System.out.println(balance.toString());
        return stringBuilder.append(String.format("| %-50s |%n", balance.printAmount())).toString();
    }
}
