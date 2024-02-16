package net.java.bankingapp.Service.impl;

import net.java.bankingapp.Entity.Account;
import net.java.bankingapp.Entity.Transaction;
import net.java.bankingapp.Entity.Transfer;
import net.java.bankingapp.Repository.AccountRepository;
import net.java.bankingapp.Repository.TransactionRepository;
import net.java.bankingapp.Repository.TransferRepository;
import net.java.bankingapp.Service.TransactionService;
import net.java.bankingapp.dto.AccountDto;
import net.java.bankingapp.dto.AccountTransactionDto;
import net.java.bankingapp.dto.TransactionDto;
import net.java.bankingapp.mapper.AccountMapper;
import net.java.bankingapp.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{


    private TransactionRepository transactionRepository;

    private AccountRepository accountRepository;

    private TransferRepository transferRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository,  TransferRepository transferRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transferRepository=transferRepository;
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


        List<Transaction> trans= transfer_trans(id, transactions);


        accountsdto.setTransactions(trans);

        return accountsdto;

    }

    private List<Transaction> transfer_trans(Long id, List<Transaction> transactions) {
        List<Transfer> send_list=transferRepository.findBySenderid(id);
        List<Transfer> recieved_list=transferRepository.findByRecieverid(id);

        for( Transfer transfer:send_list){
            Transaction transaction=new Transaction();
            transaction.setTrans_id(transfer.getTrans_id());
            transaction.setId(id);
            transaction.setAmount(-transfer.getAmount());

            System.out.println(transaction);
            transactions.add(transaction);

        }
        for( Transfer transfer:recieved_list){
            Transaction transaction=new Transaction();
            transaction.setTrans_id(transfer.getTrans_id());
            transaction.setId(id);
            transaction.setAmount(transfer.getAmount());
            System.out.println(transfer.getAmount());
            transactions.add(transaction);

        }
        return transactions;
    }

    @Override
    public Transfer TransferTransaction(Long s_id, Long r_id, double amount){
        Account Sender_account=accountRepository
                .findById(s_id)
                .orElseThrow(()->new RuntimeException("Account Doesnt exists"));

        Account Reciever_account=accountRepository
                .findById(r_id)
                .orElseThrow(()->new RuntimeException("Account Doesnt exists"));

        if(Sender_account.getBalance()<amount) {
            throw new RuntimeException("No sufficient Balance");
        }

        account_trans(amount, Sender_account, Reciever_account);

        String uniqueId = UUID.randomUUID().toString();

        //trans_extracted(s_id,-amount,uniqueId,transactionRepository);

       //trans_extracted(r_id,amount,uniqueId,transactionRepository);



        Transfer transfer=new Transfer();
        transfer.setSenderid(s_id);
        transfer.setRecieverid(r_id);
        transfer.setTrans_id(uniqueId);
        transfer.setAmount(amount);

        transferRepository.save(transfer);
        return transfer;

    }


    private void account_trans(double amount, Account Sender_account, Account Reciever_account) {
        double s_total = Sender_account.getBalance() - amount;
        double r_total = Reciever_account.getBalance() + amount;
        Sender_account.setBalance(s_total);
        Reciever_account.setBalance(r_total);
        accountRepository.save(Sender_account);
        accountRepository.save(Reciever_account);
    }

    private static void trans_extracted(Long s_id, double amount, String uniqueId,TransactionRepository transactionRepository) {
        Transaction transaction=new Transaction();
        transaction.setId(s_id);
        transaction.setAmount(amount);
        transaction.setTrans_id(uniqueId);
        transactionRepository.save(transaction);
        
    }
}
