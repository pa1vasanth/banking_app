package net.java.bankingapp.Service.impl;

import net.java.bankingapp.Entity.Account;
import net.java.bankingapp.Entity.Transaction;
import net.java.bankingapp.Repository.AccountRepository;
import net.java.bankingapp.Repository.TransactionRepository;
import net.java.bankingapp.Service.TransactionService;
import net.java.bankingapp.dto.AccountDto;
import net.java.bankingapp.dto.AccountTransactionDto;
import net.java.bankingapp.dto.TransactionDto;
import net.java.bankingapp.mapper.AccountMapper;
import net.java.bankingapp.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{


    private TransactionRepository transactionRepository;

    private AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountTransactionDto getTransactionById(Long id) {

        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account Doesnt exists"));


        AccountTransactionDto accountsdto=new AccountTransactionDto();
        List<Transaction> transactions=transactionRepository.findById(id);
        accountsdto.setName(account.getAccountHolderName());
        accountsdto.setBalance(account.getBalance());
        accountsdto.setTransactions(transactions);

        return accountsdto;

    }
}
