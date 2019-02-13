package dev.goumies.bankaccount.domain;

import java.time.LocalDate;
import java.util.Objects;

class BankingOperation {
    private final LocalDate date;
    final Money amount;
    private final Type type;

    private BankingOperation(LocalDate date, Money amount, Type type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    static Builder anOperation() {
        return new Builder();
    }

    Money getAmount() {
        return amount;
    }


    boolean isAWithdrawal() {
        return type.equals(Type.WITHDRAWAL);
    }

    boolean isADeposit() {
        return type.equals(Type.DEPOSIT);
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
