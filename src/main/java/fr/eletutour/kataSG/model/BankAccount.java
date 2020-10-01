package fr.eletutour.kataSG.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.eletutour.kataSG.dto.BankAccountDTO;
import fr.eletutour.kataSG.dto.OperationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BankAccount database model
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique = true, updatable = false, nullable = false)
    private int id;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private int amount;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Operation> operations;

    public BankAccount(BankAccountDTO bankAccountDTO) {
        this.setName(bankAccountDTO.getName());
        this.setAmount(bankAccountDTO.getAmount());
    }

    public BankAccountDTO toDTO() {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setId(this.id);
        bankAccountDTO.setName(this.name);
        bankAccountDTO.setAmount(this.amount);

        List<OperationDto> operationDtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(this.operations)){
            for (Operation operation:this.operations) {
                operationDtos.add(operation.toDto());
            }
        }
        bankAccountDTO.setOperationsDTO(operationDtos);


        return bankAccountDTO;

    }
}
