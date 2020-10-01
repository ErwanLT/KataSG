package fr.eletutour.kataSG.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import fr.eletutour.kataSG.dto.OperationDto;
import fr.eletutour.kataSG.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Operation database model
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique = true, updatable = false, nullable = false)
    private int idOperation;

    @JoinColumn(name = "idBankAccount")
    @JsonBackReference
    @OneToOne
    private BankAccount bankAccount;

    @Column(name = "dateOperation", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOperation;

    @Column(name = "typeOperation", updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    @Column(name="amount", updatable = false, nullable = false)
    private int amount;

    public OperationDto toDto(){
        OperationDto operationDto = new OperationDto();
        operationDto.setIdOperation(this.getIdOperation());
        operationDto.setDateOperation(this.getDateOperation());
        operationDto.setAmount(this.getAmount());
        operationDto.setTypeOperation(this.typeOperation);

        return operationDto;
    }
}
