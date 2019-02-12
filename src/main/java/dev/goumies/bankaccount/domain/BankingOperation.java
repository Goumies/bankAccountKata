package dev.goumies.bankaccount.domain;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

class BankingOperation {
    private final LocalDate date;
    private Money amount;
    private Type type;

    private BankingOperation(LocalDate date, Money amount) {
        this(date, amount, Type.NONE);
    }

    private BankingOperation(LocalDate date, Money amount, Type type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    static Builder anOperation() {
        return new Builder();
    }

    Object getDate() {
        return date;
    }

    Money getAmount() {
        return amount;
    }


    private Type getType() {
        return type;
    }

    boolean isAWithdrawal() {
        return type.equals(Type.WITHDRAWAL);
    }

    String printAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedValueOfAmountOfMoney = decimalFormat.format(amount.getValue());
        return String.valueOf(formattedValueOfAmountOfMoney) + " " + amount.getCurrency();
    }

    static final class Builder {

        private LocalDate date;
        private Money amount;
        private Type type;

        Builder withADate(LocalDate date) {
            this.date = date;
            return this;
        }

        Builder withAnAmount(Money amount) {
            this.amount = amount;
            return this;
        }

        Builder withType(Type type) {
            this.type = type;
            return this;
        }

        BankingOperation build() {
            return new BankingOperation(date, amount, type);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankingOperation that = (BankingOperation) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount);
    }
}
