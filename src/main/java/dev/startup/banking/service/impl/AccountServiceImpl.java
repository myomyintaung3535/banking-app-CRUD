package dev.startup.banking.service.impl;

import dev.startup.banking.dto.AccountDto;
import dev.startup.banking.entity.Account;
import dev.startup.banking.mapper.AccountMapper;
import dev.startup.banking.repository.AccountRepository;
import dev.startup.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepo;

    public AccountServiceImpl(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAcccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAcccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(()->new RuntimeException("Account does not found"));
        account.setBalance(account.getBalance()+amount);
        Account savedAccount =accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not found"));
        if (account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepo.findAll();
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
       Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("Account does not found"));
       accountRepo.deleteById(account.getId());
    }


}
