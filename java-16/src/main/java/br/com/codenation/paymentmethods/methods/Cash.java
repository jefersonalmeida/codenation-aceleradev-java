package br.com.codenation.paymentmethods.methods;

import br.com.codenation.paymentmethods.PriceStrategy;

public class Cash implements PriceStrategy {

    @Override
    public Double calculate(Double price) {
        return price * 0.9;
    }
}
