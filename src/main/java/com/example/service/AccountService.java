package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.AccountAlreadyExistsException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account addUser(Account account) {
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if(existingAccount.isPresent()) {
            throw new AccountAlreadyExistsException("Account already in the database");
        }
        if (account.getUsername().length() != 0 && account.getPassword().length() > 3) {
            return accountRepository.save(account);
        }
        return null;
    }
    public Account login(Account account) {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }
}
