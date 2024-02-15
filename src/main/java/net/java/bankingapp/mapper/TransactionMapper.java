package net.java.bankingapp.mapper;

import net.java.bankingapp.Entity.Transaction;
import net.java.bankingapp.dto.TransactionDto;

public class TransactionMapper {

    public static Transaction mapToTransaction(TransactionDto transactionDto){
        Transaction transaction=new Transaction(transactionDto.getTrans_id(),
                transactionDto.getId(),
                transactionDto.getAmount());

        return transaction;
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction){
        TransactionDto transactionDto=new TransactionDto( transaction.getTrans_id(),
                transaction.getId(),
                transaction.getAmount());

        return transactionDto;
    }
}
