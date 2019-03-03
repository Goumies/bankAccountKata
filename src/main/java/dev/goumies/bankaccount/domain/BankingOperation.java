package dev.goumies.bankaccount.domain;

class BankingOperation {
    private final Money amount;
    private final BankingOperationType bankingOperationType;

    static BankingOperationBuilder aBankingOperation() {
        return new BankingOperationBuilder();
    }

    BankingOperation(Money amount, BankingOperationType bankingOperationType) {
        this.amount = amount;
        this.bankingOperationType = bankingOperationType;
    }

    public Money getAmount() {
        return amount;
    }

    public static class BankingOperationBuilder {
        private Money amount;
        private BankingOperationType bankingOperationType;

        BankingOperationBuilder withAmount(Money amount) {
            this.amount = amount;
            return this;
        }

        BankingOperationBuilder withBankingOperationType(BankingOperationType bankingOperationType) {
            this.bankingOperationType = bankingOperationType;
            return this;
        }

        BankingOperation build() {
            return new BankingOperation(amount, bankingOperationType);
        }
    }
}
