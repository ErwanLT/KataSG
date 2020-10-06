package fr.eletutour.kataSG.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Table
public class Operation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JoinColumn(name = "idBankAccount")
    @JsonBackReference
    @OneToOne
    private BankAccount bankAccount;

    @Column(nullable = false)
    private Instant createdOn;

    private TypeOperation typeOperation;

    private int amount;

    public Operation(Instant createdOn, TypeOperation typeOperation, int amount, BankAccount bankAccount){
        setCreatedOn(createdOn);
        setTypeOperation(typeOperation);
        setAmount(amount);
        setBankAccount(bankAccount);
    }

    public void setCreatedOn(Instant createdOn){
        this.createdOn = Objects.requireNonNull(createdOn, "createdOn must not be null");
    }

    public void setTypeOperation(TypeOperation typeOperation){
        this.typeOperation = Objects.requireNonNull(typeOperation, "typeOperation must not be null");
    }

    public void setAmount(int amount){
        this.amount = Objects.requireNonNull(amount, "amount must not be null");
    }

    public void setBankAccount(BankAccount bankAccount){
        this.bankAccount = Objects.requireNonNull(bankAccount, "bankAccount must not be null");
    }
}
