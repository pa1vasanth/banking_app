package net.java.bankingapp.Service;

import net.java.bankingapp.dto.AccountTransactionDto;
import net.java.bankingapp.dto.TransactionDto;

import java.util.List;

public interface TransactionService {


    AccountTransactionDto getTransactionById(Long id);
}
