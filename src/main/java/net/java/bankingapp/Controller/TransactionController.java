package net.java.bankingapp.Controller;

import net.java.bankingapp.Entity.Transaction;
import net.java.bankingapp.Entity.Transfer;
import net.java.bankingapp.Service.AccountService;
import net.java.bankingapp.Service.TransactionService;
import net.java.bankingapp.dto.AccountDto;
import net.java.bankingapp.dto.AccountTransactionDto;
import net.java.bankingapp.dto.TransactionDto;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/transfer/{amount}")
    public ResponseEntity<Transfer> getTransfer(@PathVariable Double amount, @RequestBody Map<String,Long> request){

        System.out.println();



        Transfer transfer_success=transactionService.TransferTransaction(request.get("Sender_Id"),request.get("Reciever_Id"),amount);
        return ResponseEntity.ok(transfer_success);
    }

}
