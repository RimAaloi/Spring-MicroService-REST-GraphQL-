package ma.enset.bankacountservice.services;

import lombok.extern.slf4j.Slf4j;
import ma.enset.bankacountservice.dtos.BankAccountRequestDTO;
import ma.enset.bankacountservice.dtos.BankAccountResponseDTO;
import ma.enset.bankacountservice.entities.BankAccount;
import ma.enset.bankacountservice.mappers.AccountMapper;
import ma.enset.bankacountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final BankAccountRepository bankAccountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO accountDTO) {
        log.info("Creating new bank account");
        BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(accountDTO);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        log.info("Bank account created with ID: {}", savedBankAccount.getId());
        return accountMapper.fromBankAccount(savedBankAccount);
    }

    @Override
    public BankAccountResponseDTO getAccount(String id) {
        log.info("Fetching bank account with ID: {}", id);
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found with ID: " + id));
        return accountMapper.fromBankAccount(bankAccount);
    }

    @Override
    public List<BankAccountResponseDTO> getAllAccounts() {
        log.info("Fetching all bank accounts");
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.stream()
                .map(accountMapper::fromBankAccount)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO accountDTO) {
        log.info("Updating bank account with ID: {}", id);
        BankAccount existingAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found with ID: " + id));

        accountMapper.updateBankAccountFromDTO(accountDTO, existingAccount);
        BankAccount updatedAccount = bankAccountRepository.save(existingAccount);
        log.info("Bank account updated successfully with ID: {}", id);
        return accountMapper.fromBankAccount(updatedAccount);
    }

    @Override
    public void deleteAccount(String id) {
        log.info("Deleting bank account with ID: {}", id);
        if (!bankAccountRepository.existsById(id)) {
            throw new RuntimeException("Bank account not found with ID: " + id);
        }
        bankAccountRepository.deleteById(id);
        log.info("Bank account deleted successfully with ID: {}", id);
    }
}