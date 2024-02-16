package net.java.bankingapp.Repository;

import net.java.bankingapp.Entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer,String> {
    List<Transfer> findBySenderid(Long senderid );

    List<Transfer> findByRecieverid(Long recieverid);
}
