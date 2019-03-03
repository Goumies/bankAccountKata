package dev.goumies.bankaccount.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BankAccountTest {
    @Test
    public void given_a_deposit_on_a_newly_created_with_neutral_balance_account_should_return_true() {
        final Balance initialBalance = Balance.valueOf(0);
        BankAccount account = new BankAccount(initialBalance);
        final Money depositOf10Euros = Money.valueOf(10);
        account = account.deposit(depositOf10Euros);
        final Balance expectedBalanceOf10Euros = initialBalance.plus(depositOf10Euros);
        Assertions.assertThat(account.hasBalanceEqualTo(expectedBalanceOf10Euros)).isTrue();
    }

    @Test
    public void given_a_deposit_on_a_newly_created_account_should_return_true() {
        final Balance initialBalance = Balance.valueOf(10);
        BankAccount account = new BankAccount(initialBalance);
        final Money depositOf10Euros = Money.valueOf(10);
        account = account.deposit(depositOf10Euros);
        final Balance expectedBalanceOf10Euros = initialBalance.plus(depositOf10Euros);
        Assertions.assertThat(account.hasBalanceEqualTo(expectedBalanceOf10Euros)).isTrue();
    }
}
