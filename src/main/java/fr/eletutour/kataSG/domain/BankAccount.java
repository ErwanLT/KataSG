package fr.eletutour.kataSG.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.eletutour.kataSG.exceptions.NegativeBalanceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class BankAccount {

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

    @Column(nullable = false)
    private Instant createdOn;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int amount;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Operation> operations;

    public BankAccount(Instant createdOn, String name, int amount){
        setCreatedOn(createdOn);
        setName(name);
        setAmount(amount);
        operations = new ArrayList<>();
    }


    private void setCreatedOn(Instant createdOn) {
        this.createdOn = Objects.requireNonNull(createdOn, "createdOn must not be null");
    }

    private void setName(String name){
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    private void setAmount(int amount){
        this.amount = Objects.requireNonNull(amount, "amount must not be null");
    }

    public Operation addOperation(Instant createdOn, TypeOperation typeOperation, int amount){
        if (typeOperation.equals(TypeOperation.WHITHDRAWAL) && this.amount - amount < 0){
            throw new NegativeBalanceException("The balance of your account will be negative");
        }
        Operation operation = new Operation(createdOn, typeOperation, amount, this);
        operations.add(operation);
        return operation;
    }
}
