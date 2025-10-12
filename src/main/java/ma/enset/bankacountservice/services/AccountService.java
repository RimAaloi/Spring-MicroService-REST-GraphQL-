package ma.enset.bankacountservice.services;

import ma.enset.bankacountservice.dtos.BankAccountRequestDTO;
import ma.enset.bankacountservice.dtos.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {
     BankAccountResponseDTO addAccount(BankAccountRequestDTO account);
     BankAccountResponseDTO getAccount(String id);
     List<BankAccountResponseDTO> getAllAccounts();
     BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO account);
     void deleteAccount(String id);
}