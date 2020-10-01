package fr.eletutour.kataSG.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eletutour.kataSG.dto.BankAccountDTO;
import fr.eletutour.kataSG.exceptions.BankAccountNotFoundException;
import fr.eletutour.kataSG.exceptions.NegativeBalanceException;
import fr.eletutour.kataSG.repository.BankAccountRepository;
import fr.eletutour.kataSG.services.impl.BankService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BankController.class)
public class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @MockBean
    private BankAccountRepository bankAccountRepository;

    private BankAccountDTO bankAccountDTO;

    @Before
    public void init(){
        bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setName("Erwan");
        bankAccountDTO.setAmount(1000);
    }

    @Test
    public void shouldCreateAccount() throws Exception {
        given(this.bankService.createAccount(any())).willReturn(bankAccountDTO);

        this.mockMvc.perform(post("/bank")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(bankAccountDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn_notFound_whenTryingToDoWithDrawOperation() throws Exception {

        when(this.bankAccountRepository.findOneById(anyInt())).thenReturn(null);
        when(this.bankService.withdraw(anyInt(), anyInt())).thenThrow(new BankAccountNotFoundException(""));

        this.mockMvc.perform(get("/bank/withdraw?idAccount=9999&amount=150"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn_notFound_whenTryingToDoDepositOperation() throws Exception {

        when(this.bankAccountRepository.findOneById(anyInt())).thenReturn(null);
        when(this.bankService.deposit(anyInt(), anyInt())).thenThrow(new BankAccountNotFoundException(""));

        this.mockMvc.perform(get("/bank/deposit?idAccount=9999&amount=150"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn_badRequest_whenTryingToDoWithdrawOperation() throws Exception {
        when(this.bankService.withdraw(anyInt(), anyInt())).thenThrow(new NegativeBalanceException(""));

        this.mockMvc.perform(get("/bank/withdraw?idAccount=9999&amount=150"))
                .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
