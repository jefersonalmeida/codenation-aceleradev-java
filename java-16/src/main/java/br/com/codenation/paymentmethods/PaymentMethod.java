package br.com.codenation.paymentmethods;

import br.com.codenation.paymentmethods.methods.Cash;
import br.com.codenation.paymentmethods.methods.CreditCard;
import br.com.codenation.paymentmethods.methods.DebitCard;
import br.com.codenation.paymentmethods.methods.Transfer;

public enum PaymentMethod {

    CASH(new Cash()), DEBIT_CARD(new DebitCard()), CREDIT_CARD(new CreditCard()), TRANSFER(new Transfer());

    private final PriceStrategy priceStrategy;

    PaymentMethod(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public PriceStrategy getPaymentStrategy() {
        return priceStrategy;
    }
}
