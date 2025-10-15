package ma.enset.bankacountservice.web;

import lombok.RequiredArgsConstructor;
import ma.enset.bankacountservice.entities.BankAccount;
import ma.enset.bankacountservice.entities.Customer;
import ma.enset.bankacountservice.enums.AccountType;
import ma.enset.bankacountservice.repositories.BankAccountRepository;
import ma.enset.bankacountservice.repositories.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BankAccountGraphQLController {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    // AJOUTEZ CETTE MÉTHODE MANQUANTE ↓
    @QueryMapping
    public List<Customer> customers() {
        return customerRepository.findAll();
    }

    @QueryMapping
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount account(@Argument String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @MutationMapping
    public BankAccount createAccount(@Argument BankAccountInput account) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(account.balance())
                .currency(account.currency())
                .type(account.type())
                .build();

        return bankAccountRepository.save(bankAccount);
    }

    @MutationMapping
    public BankAccount updateAccount(@Argument String id, @Argument BankAccountInput account) {
        BankAccount existingAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        existingAccount.setBalance(account.balance());
        existingAccount.setCurrency(account.currency());
        existingAccount.setType(account.type());

        return bankAccountRepository.save(existingAccount);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        bankAccountRepository.delete(account);
        return true;
    }

    @SchemaMapping(typeName = "Customer", field = "bankAccounts")
    public List<BankAccount> bankAccounts(Customer customer) {
        return bankAccountRepository.findByCustomerId(customer.getId());
    }

    @SchemaMapping
    public Customer customer(BankAccount bankAccount) {
        return bankAccount.getCustomer();
    }
}