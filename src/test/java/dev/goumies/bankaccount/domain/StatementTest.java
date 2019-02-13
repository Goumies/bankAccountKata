package dev.goumies.bankaccount.domain;

import org.junit.Test;

import java.time.LocalDate;

import static dev.goumies.bankaccount.domain.BankingOperation.anOperation;
import static org.assertj.core.api.Assertions.assertThat;

public class StatementTest {
    @Test
    public void given_an_operation_with_a_date_and_an_amount_should_return_statement_with_the_date_the_amount() {
        OperationsPrinter operationsPrinter = new OperationsPrinter(new Account(Money.valueOf(0)));
        BankingOperation anOperation = anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(10)).withType(Type.DEPOSIT).build();
        String statementForDepositDateAndAmount = operationsPrinter.generateOperationStatement(anOperation);
        String expectedStatementForDepositDate = String.format(" | %tF | %-20s | %-20s |%n", LocalDate.now(), anOperation.amount.printAmount(), "");
        assertThat(statementForDepositDateAndAmount).isEqualTo(expectedStatementForDepositDate);
    }

    @Test
    public void given_2_operations_with_a_date_and_an_amount_should_return_statement_with_each_date_and_amount() {
        OperationsPrinter operationsPrinter = new OperationsPrinter(new Account(Money.valueOf(0)));
        BankingOperation anOperation = anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(10)).withType(Type.DEPOSIT).build();
        BankingOperation anotherOperation = anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(100)).withType(Type.DEPOSIT).build();
        Operations operations = new Operations(anOperation, anotherOperation);
        boolean hasHeader = false;
        String statementForDepositDateAndAmount = operationsPrinter.generateStatementFor(operations, hasHeader);
        String expectedStatementForDepositDate = String.format(" | %tF | %-20s | %-20s |%n", LocalDate.now(), anOperation.amount.printAmount(), "") + String.format(" | %tF | %-20s | %-20s |%n", LocalDate.now(), anotherOperation.amount.printAmount(), "");
        assertThat(statementForDepositDateAndAmount).isEqualTo(expectedStatementForDepositDate);
    }

    @Test
    public void given_a_deposit_of_10_euros_should_return_a_statement_with_that_operation() {
        Money initialBalance = Money.valueOf(0);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.deposit(amount);
        OperationsPrinter operationsPrinter = new OperationsPrinter(new Account(Money.valueOf(0)));
        String statementForATenEurosDeposit = String.format("| %-10s | %-20s | %-20s |%n" +
                " | %tF | %-20s | %-20s |%n", "DATE", "CREDIT", "DEBIT", LocalDate.now(), "10,00 EUR", ""/* +
                "|  BALANCE  |" +
                "|  10.00 EUR  |"*/);
        Operations operations = new Operations(anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(10)).withType(Type.DEPOSIT).build());
        boolean hasHeader = true;
        assertThat(operationsPrinter.generateStatementFor(operations, hasHeader)).isEqualTo(statementForATenEurosDeposit);
    }

    @Test
    public void given_a_withdrawal_of_10_euros_should_return_a_statement_with_that_operation() {
        Money initialBalance = Money.valueOf(10);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.withdraw(amount);
        OperationsPrinter operationsPrinter = new OperationsPrinter(new Account(Money.valueOf(0)));
        String statementForATenEurosDeposit = String.format("| %-10s | %-20s | %-20s |%n" +
                " | %tF | %-20s | %-20s |%n", "DATE", "CREDIT", "DEBIT", LocalDate.now(), "", "10,00 EUR");
        Operations operations = new Operations(anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(10)).withType(Type.WITHDRAWAL).build());
        boolean hasHeader = true;
        assertThat(operationsPrinter.generateStatementFor(operations, hasHeader)).isEqualTo(statementForATenEurosDeposit);
    }

    @Test
    public void given_a_withdrawal_of_10_euros_should_return_a_complete_statement_with_that_operation() {
        Money initialBalance = Money.valueOf(10);
        Account account = new Account(initialBalance);
        account.withdraw(initialBalance);
        OperationsPrinter operationsPrinter = new OperationsPrinter(account);
        boolean hasHeader = true;
        String statementForATenEurosWithdrawal = String.format("| %-10s | %-20s | %-20s |%n" +
                " | %tF | %-20s | %-20s |%n" +
                " | %tF | %-20s | %-20s |%n" +
                "| %-50s |%n" +
                "| %-50s |%n", "DATE", "CREDIT", "DEBIT", LocalDate.now(), "10,00 EUR", "", LocalDate.now(), "", "10,00 EUR", "BALANCE", "0,00 EUR");
        String statement = operationsPrinter.generateStatementFor(account.getAllOperations(), hasHeader);
        assertThat(operationsPrinter.addFooter(statement)).isEqualTo(statementForATenEurosWithdrawal);
    }
}
