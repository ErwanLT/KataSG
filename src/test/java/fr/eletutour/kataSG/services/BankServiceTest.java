package fr.eletutour.kataSG.services;

import fr.eletutour.kataSG.exceptions.BankAccountNotFoundException;
import fr.eletutour.kataSG.exceptions.NegativeBalanceException;
import fr.eletutour.kataSG.model.BankAccount;
import fr.eletutour.kataSG.repository.BankAccountRepository;
import fr.eletutour.kataSG.services.impl.BankService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BankServiceTest {

    @MockBean
    private BankAccountRepository bankAccountRepository;

    private BankService bankService;

    @Before
    public void init(){
        this.bankService = new BankService(bankAccountRepository);
    }

    @Test(expected = BankAccountNotFoundException.class)
    public void shouldThrowBankAccountNotFound_deposit(){
        when(bankAccountRepository.findOneById(anyInt())).thenReturn(null);

        this.bankService.deposit(1, 1000);

    }

    @Test(expected = BankAccountNotFoundException.class)
    public void shouldThrowBankAccountNotFound_withdrawal(){
        when(bankAccountRepository.findOneById(anyInt())).thenReturn(null);

        this.bankService.withdraw(1, 1000);

    }

    @Test(expected = NegativeBalanceException.class)
    public void shouldThrowNegativeBalanceException(){
        when(bankAccountRepository.findOneById(anyInt())).thenReturn(initBankAccount());

        this.bankService.withdraw(1, 100);
    }

    private BankAccount initBankAccount() {
        BankAccount account = new BankAccount();
        account.setId(1);
        account.setName("Erwan");
        account.setAmount(15);

        return account;
    }

}
