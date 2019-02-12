package dev.goumies.bankaccount.domain;

import java.time.LocalDate;
import java.util.Objects;

class BankingOperation {
    private final LocalDate date;
    private Money amount;

    private BankingOperation(LocalDate date, Money amount) {
        this.date = date;
        this.amount = amount;
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

    static final class Builder {
        private LocalDate date;
        private Money amount;

        Builder withADate(LocalDate date) {
            this.date = date;
            return this;
        }

        Builder withAnAmount(Money amount) {
            this.amount = amount;
            return this;
        }

        BankingOperation build() {
            return new BankingOperation(date, amount);
        }
    }


}
