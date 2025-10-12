package ma.enset.bankacountservice.mappers;

import ma.enset.bankacountservice.dtos.BankAccountRequestDTO;
import ma.enset.bankacountservice.dtos.BankAccountResponseDTO;
import ma.enset.bankacountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }

    public BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDTO, bankAccount);
        return bankAccount;
    }

    public BankAccount fromBankAccountResponseDTO(BankAccountResponseDTO bankAccountResponseDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountResponseDTO, bankAccount);
        return bankAccount;
    }

    public void updateBankAccountFromDTO(BankAccountRequestDTO bankAccountRequestDTO, BankAccount bankAccount) {
        BeanUtils.copyProperties(bankAccountRequestDTO, bankAccount);
    }

    public void updateBankAccountFromResponseDTO(BankAccountResponseDTO bankAccountResponseDTO, BankAccount bankAccount) {
        BeanUtils.copyProperties(bankAccountResponseDTO, bankAccount);
    }
}