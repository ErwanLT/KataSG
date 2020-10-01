package fr.eletutour.kataSG.repository;

import fr.eletutour.kataSG.model.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankAccountRepositoryTest {


    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void whenFindById_thenReturnAccount(){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setName("Erwan");
        bankAccount.setAmount(1000);

        bankAccount = bankAccountRepository.save(bankAccount);

        assertThat(bankAccount.getId()).isNotNull();
        int id = bankAccount.getId();

        BankAccount bankAccount1 = bankAccountRepository.findOneById(id);
        assertThat(bankAccount1).isNotNull();
        assertThat(bankAccount1.getId()).isEqualTo(id);
        assertThat(bankAccount1.getName()).isEqualTo(bankAccount.getName());
        assertThat(bankAccount1.getAmount()).isEqualTo(bankAccount.getAmount());

    }
}
