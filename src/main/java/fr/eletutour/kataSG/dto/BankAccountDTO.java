package fr.eletutour.kataSG.dto;

import fr.eletutour.kataSG.model.BankAccount;
import fr.eletutour.kataSG.model.Operation;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {

    @Null(message = "cannot be filled")
    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(name = "name", required = true, dataType = "String", example = "Erwan", position = 0)
    private String name;

    @ApiModelProperty(name = "amount", required = true, dataType = "int", example = "1000", position = 1)
    private int Amount;

    private List<OperationDto> operationsDTO;

    public List<OperationDto> getOperationsDTO() {
        if(!CollectionUtils.isEmpty(this.operationsDTO)){
            return this.operationsDTO;
        } else {
            return new ArrayList<>();
        }
    }
}
