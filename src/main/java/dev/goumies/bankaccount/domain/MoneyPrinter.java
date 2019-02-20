package dev.goumies.bankaccount.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

class MoneyPrinter {
    MoneyPrinter() {
    }

    static String printAmount(Money money) {
        if (money.value == 0)
            return "0,00 EUR";
        DecimalFormat decimalFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.FRENCH));
        String formattedValueOfAmountOfMoney = decimalFormat.format(money.value);
        return formattedValueOfAmountOfMoney + " " + money.getCurrency();
    }
}
