package ma.enset.bankacountservice.web;

import ma.enset.bankacountservice.dtos.BankAccountRequestDTO;
import ma.enset.bankacountservice.dtos.BankAccountResponseDTO;
import ma.enset.bankacountservice.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankAccounts")
public class AccountRestController {

    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<BankAccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public BankAccountResponseDTO getAccount(@PathVariable String id) {
        return accountService.getAccount(id);
    }

    @PostMapping
    public BankAccountResponseDTO createAccount(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/{id}")
    public BankAccountResponseDTO updateAccount(@PathVariable String id, @RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.updateAccount(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
    }
}