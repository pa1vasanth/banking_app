package net.java.bankingapp.Service.impl;

import net.java.bankingapp.Controller.AccountController;
import net.java.bankingapp.Entity.Account;
import net.java.bankingapp.Repository.AccountRepository;
import net.java.bankingapp.Service.AccountService;
import net.java.bankingapp.dto.AccountDto;
import net.java.bankingapp.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private static  final Logger logger= LoggerFactory.getLogger(AccountServiceImpl.class);
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }



    @Override
    public AccountDto getAccountById(Long id) {

        logger.info("Is it working");
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Doesnt exists"));
        return AccountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        logger.info("Is it working");
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account Doesnt exists"));

        double total= account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }


    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account Doesnt exists"));

        if(account.getBalance()<amount) {
            throw new RuntimeException("No sufficient Balance");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }


    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account Doesnt exists"));

        accountRepository.deleteById(id);
    }
}
