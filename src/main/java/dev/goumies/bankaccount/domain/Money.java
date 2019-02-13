package dev.goumies.bankaccount.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

class Money {
    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }

    private final int value;
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

    private String getCurrency() {
        return currency;
    }

    String printAmount() {
        if (value == 0)
            return "0,00 EUR";
        DecimalFormat decimalFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.FRENCH));
        String formattedValueOfAmountOfMoney = decimalFormat.format(value);
        return String.valueOf(formattedValueOfAmountOfMoney) + " " + getCurrency();
    }
}
