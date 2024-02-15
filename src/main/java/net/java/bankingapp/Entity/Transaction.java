package net.java.bankingapp.Entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Transaction")
public class Transaction{

    @Id
    private String trans_id;


    private Long id;

    private double amount;

}
