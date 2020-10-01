package fr.eletutour.kataSG.dto;

import fr.eletutour.kataSG.enums.TypeOperation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OperationDto {

    @ApiModelProperty(name = "id operation", dataType = "int")
    private int idOperation;

    @ApiModelProperty(name = "date operation", dataType = "Date", position = 1)
    private Date dateOperation;

    @ApiModelProperty(name = "type operation", dataType = "String", allowableValues = "WHITHDRAWAL,DEPOSIT", position = 2, example = "WHITHDRAWAL")
    private TypeOperation typeOperation;

    @ApiModelProperty(name = "amount", dataType = "int", position = 3, example = "10")
    private int amount;

}
