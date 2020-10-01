package fr.eletutour.kataSG.controller;

import fr.eletutour.kataSG.dto.BankAccountDTO;
import fr.eletutour.kataSG.dto.OperationDto;
import fr.eletutour.kataSG.exceptions.BankAccountNotFoundException;
import fr.eletutour.kataSG.exceptions.NegativeBalanceException;
import fr.eletutour.kataSG.model.BankAccount;
import fr.eletutour.kataSG.services.IBankService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bank")
@Slf4j
public class BankController {

    private final IBankService bankService;

    @Autowired
    public BankController(IBankService bankService){
        this.bankService = bankService;
    }

    @PostMapping()
    @ApiOperation(value = "create a bank account",
                  produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "account created", response = BankAccount.class),
            @ApiResponse(code = 500, message = "An error occured")
        }
    )
    public ResponseEntity createAccount(@ApiParam @RequestBody BankAccountDTO bankAccountDTO){
        try{
            return new ResponseEntity(bankService.createAccount(bankAccountDTO), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/withdraw")
    @ApiOperation(value = "make a withdrawal from bank account",
                  produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "operation created", response = BankAccount.class),
            @ApiResponse(code = 400, message = "The balance of your account will be negative"),
            @ApiResponse(code = 404, message = "Bank account not found"),
            @ApiResponse(code = 500, message = "An error occured")
        }
    )
    public ResponseEntity whithdraw(@RequestParam(value = "idAccount", required = true) int idAccount,
                                    @RequestParam(value = "amount", required = true) int amount){
        try{
            return new ResponseEntity(bankService.withdraw(idAccount, amount), HttpStatus.OK);
        } catch (BankAccountNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NegativeBalanceException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/deposit")
    @ApiOperation(value = "make a deposit to bank account",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "operation created", response = BankAccount.class),
            @ApiResponse(code = 404, message = "Bank account not found"),
            @ApiResponse(code = 500, message = "An error occured")
        }
    )
    public ResponseEntity deposit(@RequestParam(value = "idAccount", required = true) int idAccount,
                                  @RequestParam(value = "amount", required = true) int amount) {
        try {
            return new ResponseEntity(bankService.deposit(idAccount, amount), HttpStatus.OK);
        }catch (BankAccountNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/operations")
    @ApiOperation(value = "make a deposit to bank account",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the list of operations", response = OperationDto.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Bank account not found"),
            @ApiResponse(code = 500, message = "An error occured")
    }
    )
    public ResponseEntity getBankAccountOperations(@RequestParam(value = "idAccount", required = true) int idAccount){
        try {
            return new ResponseEntity(bankService.getAccountOperation(idAccount), HttpStatus.OK);
        }catch (BankAccountNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
