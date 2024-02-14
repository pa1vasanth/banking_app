package net.java.bankingapp.Repository;

import net.java.bankingapp.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
