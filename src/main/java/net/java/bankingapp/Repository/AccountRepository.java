package net.java.bankingapp.Repository;

import net.java.bankingapp.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByaccountHolderName(String accountHolderName);

}
