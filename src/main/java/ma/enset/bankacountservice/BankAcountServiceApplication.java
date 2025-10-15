package ma.enset.bankacountservice;

import ma.enset.bankacountservice.entities.BankAccount;
import ma.enset.bankacountservice.entities.Customer;
import ma.enset.bankacountservice.enums.AccountType;
import ma.enset.bankacountservice.repositories.BankAccountRepository;
import ma.enset.bankacountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAcountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAcountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository accountRepository, CustomerRepository customerRepository) {
        return args -> {
            Stream.of("Rim", "asile", "noor")
                    .forEach(c -> {
                        Customer customer = Customer.builder()
                                .id(UUID.randomUUID().toString()) // ← AJOUTEZ CETTE LIGNE
                                .name(c)
                                .build();
                        customerRepository.save(customer);
                    });
            customerRepository.findAll().forEach(c -> {
                for (int i = 0; i < 10; i++) {
                    BankAccount account = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .balance(Math.random() * 10000)
                            .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .createdAt(new Date())
                            .currency("MAD")
                            .customer(c)
                            .build();
                    accountRepository.save(account);
                }
            });
        };

    }
}