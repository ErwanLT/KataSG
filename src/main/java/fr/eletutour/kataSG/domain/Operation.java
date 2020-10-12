package fr.eletutour.kataSG.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Operation {

    private Date createdOn;

    private TypeOperation typeOperation;

    private int amount;
}
