package dev.startup.banking.mapper;

import dev.startup.banking.dto.AccountDto;
import dev.startup.banking.entity.Account;

public class AccountMapper {

    public  static Account mapToAccount(AccountDto accountDto){
        return new Account(accountDto.getId(),accountDto.getAccountHolderName(), accountDto.getBalance());
    }

    public static AccountDto mapToAccountDto(Account account)  {
        return new AccountDto(account.getId(),account.getAccountHolderName(),account.getBalance());
    }
}
