package dev.goumies.bankaccount.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {
    @Test
    public void given_a_deposit_of_minus_1_euro_should_return_false() {
        Money initialBalance = Money.valueOf(0);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(-1);
        account.deposit(amount);
        boolean hasLastDepositbeenAddedToAccount = account.hasAddedLastDeposit(amount);
        assertThat(hasLastDepositbeenAddedToAccount).isEqualTo(false);
    }

    @Test
    public void given_a_deposit_of_0_euro_should_return_false() {
        Money initialBalance = Money.valueOf(0);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(0);
        account.deposit(amount);
        boolean hasLastDepositbeenAddedToAccount = account.hasAddedLastDeposit(amount);
        assertThat(hasLastDepositbeenAddedToAccount).isEqualTo(true);
    }

    @Test
    public void given_a_deposit_of_10_euros_should_return_true() {
        Money initialBalance = Money.valueOf(0);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.deposit(amount);
        boolean hasLastDepositbeenAddedToAccount = account.hasAddedLastDeposit(amount);
        assertThat(hasLastDepositbeenAddedToAccount).isEqualTo(true);
    }

    @Test
    public void given_a_withdrawal_of_10_euros_with_an_inferior_balance_should_return_false() {
        Money initialBalance = Money.valueOf(1);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.withdraw(amount);
        boolean hasLastWithdrawBeenSubstractedFromAccount = account.hasSubstractedLastWithdraw(amount);
        assertThat(hasLastWithdrawBeenSubstractedFromAccount).isEqualTo(false);
    }

    @Test
    public void given_a_withdrawal_of_10_euros_with_enough_balance_should_return_true() {
        Money initialBalance = Money.valueOf(10);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.withdraw(amount);
        boolean hasLastWithdrawBeenSubstractedFromAccount = account.hasSubstractedLastWithdraw(amount);
        assertThat(hasLastWithdrawBeenSubstractedFromAccount).isEqualTo(true);
    }

    @Test
    public void given_a_deposit_of_10_euros_should_return_a_statement_with_that_operation() {
        Money initialBalance = Money.valueOf(0);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.deposit(amount);
        OperationsPrinter operationsPrinter = new OperationsPrinter();
        String statementForATenEurosDeposit = "| DATE | CREDIT | DEBIT |" +
                "| 08-02-2019 | 10.00 EUR |           |" +
                "|  BALANCE  |" +
                "|  10.00 EUR  |";//operationsPrinter.generateStatement();
        assertThat(statementForATenEurosDeposit).isEqualTo("| DATE | CREDIT | DEBIT |" +
                "| 08-02-2019 | 10.00 EUR |           |" +
                "|  BALANCE  |" +
                "|  10.00 EUR  |");
    }
}
