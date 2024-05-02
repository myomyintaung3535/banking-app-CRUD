package dev.startup.banking.controller;

import dev.startup.banking.dto.AccountDto;
import dev.startup.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }


    // Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto dto = accountService.getAccountById(id);
        return ResponseEntity.ok(dto);
    }
    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> getDeposit(@PathVariable Long id,
                                                  @RequestBody  Map<String,Double> request){
        AccountDto accountDto = accountService.deposit(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    // Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id,@RequestBody Map<String,Double> request){
        AccountDto accountDto = accountService.withdraw(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    // Get All Account REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccount(){
       List<AccountDto> account = accountService.getAllAccount();
       return ResponseEntity.ok(account);
    }

    // Delete Account REST API
    @DeleteMapping("/{id} ")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id){
        accountService.deleteById(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
