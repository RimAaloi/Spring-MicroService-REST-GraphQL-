package ma.enset.bankacountservice.repositories;

import ma.enset.bankacountservice.entities.BankAccount;
import ma.enset.bankacountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    @RestResource(path="/bytype")
    List<BankAccount> findByType(@Param("T") AccountType type);
    List<BankAccount> findByCustomerId(String customerId);


}
