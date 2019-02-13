package dev.goumies.bankaccount.domain;

import org.junit.Test;

import java.time.LocalDate;

import static dev.goumies.bankaccount.domain.BankingOperation.anOperation;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {
    @Test
    public void given_a_deposit_of_minus_1_euro_should_return_false() {
        Money initialBalance = Money.valueOf(0);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(-1);
        account.deposit(amount);
        boolean hasLastDepositBeenAddedToAccount = account.hasAddedLastDeposit(amount);
        assertThat(hasLastDepositBeenAddedToAccount).isEqualTo(false);
    }

    @Test
    public void given_a_deposit_of_0_euro_should_return_false() {
        Money initialBalance = Money.valueOf(0);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(0);
        account.deposit(amount);
        boolean hasLastDepositBeenAddedToAccount = account.hasAddedLastDeposit(amount);
        assertThat(hasLastDepositBeenAddedToAccount).isEqualTo(true);
    }

    @Test
    public void given_a_deposit_of_10_euros_should_return_true() {
        Money initialBalance = Money.valueOf(0);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.deposit(amount);
        boolean hasLastDepositBeenAddedToAccount = account.hasAddedLastDeposit(amount);
        assertThat(hasLastDepositBeenAddedToAccount).isEqualTo(true);
    }

    @Test
    public void given_a_withdrawal_of_10_euros_with_an_inferior_balance_should_return_false() {
        Money initialBalance = Money.valueOf(1);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.withdraw(amount);
        boolean hasLastWithdrawBeenSubtractedFromAccount = account.hasSubtractedLastWithdraw(amount);
        assertThat(hasLastWithdrawBeenSubtractedFromAccount).isEqualTo(false);
    }

    @Test
    public void given_a_withdrawal_of_10_euros_with_enough_balance_should_return_true() {
        Money initialBalance = Money.valueOf(10);
        Account account = new Account(initialBalance);
        Money amount = Money.valueOf(10);
        account.withdraw(amount);
        boolean hasLastWithdrawBeenSubtractedFromAccount = account.hasSubtractedLastWithdraw(amount);
        assertThat(hasLastWithdrawBeenSubtractedFromAccount).isEqualTo(true);
    }

    @Test
    public void given_a_deposit_of_10_euros_should_add_that_operation_to_the_list_of_operations_of_the_account() {
        Account account = new Account(Money.valueOf(0));
        Money aDepositOf10Euros = Money.valueOf(10);
        account.deposit(aDepositOf10Euros);
        assertThat(account.getLastOperation()).isEqualTo(anOperation().withADate(LocalDate.now()).withAnAmount(aDepositOf10Euros).build());
    }

    @Test
    public void given_a_withdrawal_of_10_euros_should_add_that_operation_to_the_list_of_operations_of_the_account() {
        Account account = new Account(Money.valueOf(10));
        Money aWithdrawalOf10Euros = Money.valueOf(10);
        account.withdraw(aWithdrawalOf10Euros);
        assertThat(account.getLastOperation()).isEqualTo(anOperation().withADate(LocalDate.now()).withAnAmount(aWithdrawalOf10Euros).build());
    }

    @Test
    public void given_multiple_banking_operations_should_add_all_operations_to_the_list_of_operations_of_the_account() {
        Account account = new Account(Money.valueOf(10));
        Money aDepositOf10Euros = Money.valueOf(10);
        Money aWithdrawalOf10Euros = Money.valueOf(10);
        Money aDepositOf100Euros = Money.valueOf(100);
        account.withdraw(aWithdrawalOf10Euros);
        account.deposit(aDepositOf100Euros);
        BankingOperation aWithdrawalOperationWith10Euros = anOperation().withADate(LocalDate.now()).withAnAmount(aWithdrawalOf10Euros).build();
        BankingOperation aDepositOperationWith100Euros = anOperation().withADate(LocalDate.now()).withAnAmount(aDepositOf100Euros).build();
        BankingOperation aDepositOperationWith10Euros = anOperation().withADate(LocalDate.now()).withAnAmount(aDepositOf10Euros).build();
        Operations operations = new Operations(aDepositOperationWith10Euros, aWithdrawalOperationWith10Euros, aDepositOperationWith100Euros);
        assertThat(account.getAllOperations()).isEqualTo(operations);
    }

    @Test
    public void given_multiple_banking_operations_should_withdrawals_with_negative_values() {
        Account account = new Account(Money.valueOf(10));
        Money aWithdrawalOf10Euros = Money.valueOf(10);
        Money aDepositOf100Euros = Money.valueOf(100);
        Money aWithdrawalOf20Euros = Money.valueOf(20);
        account.withdraw(aWithdrawalOf10Euros);
        account.deposit(aDepositOf100Euros);
        account.withdraw(aWithdrawalOf20Euros);
        BankingOperation aWithdrawalOperationWith10Euros = anOperation().withADate(LocalDate.now()).withAnAmount(aWithdrawalOf10Euros).withType(Type.WITHDRAWAL).build();
        BankingOperation aDepositOperationWith100Euros = anOperation().withADate(LocalDate.now()).withAnAmount(aDepositOf100Euros).withType(Type.DEPOSIT).build();
        BankingOperation aWithdrawalOperationWith20Euros = anOperation().withADate(LocalDate.now()).withAnAmount(aWithdrawalOf20Euros).withType(Type.WITHDRAWAL).build();
        Operations withdrawals = new Operations(aWithdrawalOperationWith10Euros, aWithdrawalOperationWith20Euros);
        assertThat(account.getAllWithdrawals()).isEqualTo(withdrawals);
    }
}
