package net.java.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDto {

    private String trans_id;

    private Long id;

    private double amount;
}
