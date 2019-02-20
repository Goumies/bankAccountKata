package dev.goumies.bankaccount.domain;

import java.util.Objects;

class Money {
    final int value;
    private final String currency;

    private Money(int value) {
        this.value = value;
        this.currency = "EUR";
    }

    static Money valueOf(int value) {
        return new Money(value);
    }

    Money plus(Money amount) {
        return Money.valueOf(value + amount.value);
    }

    Money minus(Money amount) {
        return Money.valueOf(value - amount.value);
    }

    boolean isEnoughFor(Money amount) {
        return value >= amount.value;
    }

    String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value &&
                Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
