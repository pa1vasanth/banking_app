package net.java.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.java.bankingapp.Entity.Account;
import net.java.bankingapp.Entity.Transaction;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class AccountTransactionDto {
    private  String name;
    private double balance;
    private List<Transaction> transactions;
}
