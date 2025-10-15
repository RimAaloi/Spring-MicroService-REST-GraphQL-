package ma.enset.bankacountservice.web;

import ma.enset.bankacountservice.enums.AccountType;

public record BankAccountInput(
        Double balance,
        String currency,
        AccountType type
) {}
