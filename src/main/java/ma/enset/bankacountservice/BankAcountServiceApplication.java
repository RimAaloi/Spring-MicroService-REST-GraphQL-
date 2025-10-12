package ma.enset.bankacountservice;

import ma.enset.bankacountservice.entities.BankAccount;
import ma.enset.bankacountservice.enums.AccountType;
import ma.enset.bankacountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAcountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAcountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository accountRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                BankAccount account = BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .balance(Math.random() * 10000)
                        .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .createdAt(new Date())
                        .currency("MAD")
                        .build();
                accountRepository.save(account);
            }
        };
    }
}
