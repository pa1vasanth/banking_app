package net.java.bankingapp.Repository;

import net.java.bankingapp.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction,String>
{
    List<Transaction> findById(Long id);
}
