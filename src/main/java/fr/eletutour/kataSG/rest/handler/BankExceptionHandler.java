package fr.eletutour.kataSG.rest.handler;

import fr.eletutour.kataSG.exceptions.BankAccountNotFoundException;
import fr.eletutour.kataSG.exceptions.NegativeBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankExceptionHandler {

    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity handleNoBankAccount(BankAccountNotFoundException ex){
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "an error occured with the bank account", ex));
    }

    @ExceptionHandler(NegativeBalanceException.class)
    public ResponseEntity handleNegativeBalance(NegativeBalanceException ex){
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "an error occured while doing an operation", ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
