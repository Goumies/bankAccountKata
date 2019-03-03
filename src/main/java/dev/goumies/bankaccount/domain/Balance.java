package dev.goumies.bankaccount.domain;

import java.util.Objects;

class Balance {
    private final Money amount;

    Balance(Money amount) {
        this.amount = amount;
    }

    private Balance(Balance newBalance) {
        this.amount = newBalance.amount;
    }

    static Balance valueOf(int value) {
        return new Balance(Money.valueOf(value));
    }

    static Balance valueOf(Balance newBalance) {
        return new Balance(newBalance);
    }

    Balance plus(Money otherAmount) {
        return Balance.valueOf(amount.plus(otherAmount));
    }

    Money getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(amount, balance.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
