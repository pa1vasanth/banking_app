package net.java.bankingapp.Controller;

import net.java.bankingapp.Entity.Transaction;
import net.java.bankingapp.Service.AccountService;
import net.java.bankingapp.Service.TransactionService;
import net.java.bankingapp.dto.AccountDto;
import net.java.bankingapp.dto.AccountTransactionDto;
import net.java.bankingapp.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private TransactionService transactionService;



    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTransactionDto> getTransactions(@PathVariable Long id){
        AccountTransactionDto accountDto=transactionService.getTransactionById(id);

        return ResponseEntity.ok(accountDto);
    }
}
