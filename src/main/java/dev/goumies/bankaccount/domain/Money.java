package dev.goumies.bankaccount.domain;

import java.util.Objects;

class Money {
    private final int value;

    private Money(int amount) {
        this.value = amount;
    }

    static Money valueOf(int amount) {
        return new Money(amount);
    }

    int plus(Money otherAmount) {
        return value + otherAmount.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
