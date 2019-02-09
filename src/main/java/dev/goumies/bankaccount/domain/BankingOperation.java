package dev.goumies.bankaccount.domain;

import java.time.LocalDate;

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

    public Money getAmount() {
        return amount;
    }

    static final class Builder {
        private LocalDate date;
        private Money amount;

        Builder withADate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withAnAmount(Money amount) {
            this.amount = amount;
            return this;
        }

        BankingOperation build() {
            return new BankingOperation(date, Money.valueOf(0));
        }
    }


}
