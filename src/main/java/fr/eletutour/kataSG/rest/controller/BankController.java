package fr.eletutour.kataSG.rest.controller;

import fr.eletutour.kataSG.domain.BankAccount;
import fr.eletutour.kataSG.domain.Operation;
import fr.eletutour.kataSG.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bank")
@Slf4j
public class BankController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankController(BankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/createAccount")
    public BankAccount createAccount(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "amount") int amount){
        return bankAccountService.createAccount(name, amount);
    }

    @GetMapping("/operations/{accountId}")
    public List<Operation> getOperation(@PathVariable(value = "accountId") String accountId){
        return bankAccountService.getAccountOperation(accountId);
    }

    @PutMapping("/make/{typeOperation}/account/{accountId}")
    public void makeOperation(@PathVariable("typeOperation")String typeOperation,
                              @PathVariable("accountId")String accountId,
                              @RequestParam("amount") int amount){
        bankAccountService.makeOperation(typeOperation, accountId, amount);
    }


}
