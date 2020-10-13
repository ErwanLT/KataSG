package fr.eletutour.kataSG.domain;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money negate() {
        return new Money(this.amount.negate());
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public BigDecimal getAmount(){
        return this.amount;
    }
}
