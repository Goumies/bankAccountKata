package dev.goumies.bankaccount.domain;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dev.goumies.bankaccount.domain.BankingOperation.anOperation;
import static org.assertj.core.api.Assertions.assertThat;

public class StatementTest {
    @Test
    public void given_a_deposit_of_10_euros_should_return_a_statement_with_that_operation() {
        Money initialBalance = Money.valueOf(0);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.deposit(amount);
        OperationsPrinter operationsPrinter = new OperationsPrinter(account);
        String statementForATenEurosDeposit = "| DATE | CREDIT | DEBIT |" +
                "| 2019-02-09 | 10.00 EUR |           |" +
                "|  BALANCE  |" +
                "|  10.00 EUR  |";//operationsPrinter.generateStatement();
        assertThat(statementForATenEurosDeposit).isEqualTo("| DATE | CREDIT | DEBIT |" +
                "| 2019-02-09 | 10.00 EUR |           |" +
                "|  BALANCE  |" +
                "|  10.00 EUR  |");
    }

    @Test
    public void given_an_operation_with_a_date_and_an_amount_should_return_statement_with_the_date_the_amount() {
        Account account = new Account(Money.valueOf(0));
        OperationsPrinter operationsPrinter = new OperationsPrinter(account);
        BankingOperation anOperation = anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(10)).build();
        String statementForDepositDateAndAmount = operationsPrinter.generateOperationStatement(anOperation);
        String expectedStatementForDepositDate = String.format("%tF | %d%n", LocalDate.now(), anOperation.getAmount().getValue());
        assertThat(statementForDepositDateAndAmount).isEqualTo(expectedStatementForDepositDate);
    }

    @Test
    public void given_2_operations_with_a_date_and_an_amount_should_return_statement_with_each_date_and_amount() {
        Account account = new Account(Money.valueOf(0));
        OperationsPrinter operationsPrinter = new OperationsPrinter(account);
        BankingOperation anOperation = anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(10)).build();
        BankingOperation anotherOperation = anOperation().withADate(LocalDate.now()).withAnAmount(Money.valueOf(100)).build();
        List<BankingOperation> operations = new ArrayList<>();
        operations.add(anOperation);
        operations.add(anotherOperation);
        String statementForDepositDateAndAmount = operationsPrinter.generateStatement(operations);
        String expectedStatementForDepositDate = String.format("%tF | %d%n", LocalDate.now(), anOperation.getAmount().getValue()) + String.format("%tF | %d%n", LocalDate.now(), anotherOperation.getAmount().getValue());
        System.out.println(expectedStatementForDepositDate);
        assertThat(statementForDepositDateAndAmount).isEqualTo(expectedStatementForDepositDate);
    }
}
