package ma.enset.bankacountservice.entities;

import ma.enset.bankacountservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class, name = "accountProjection")
public interface AccountProjection {
    public String getId();
    public Double getBalance();
    public AccountType getType();

}
