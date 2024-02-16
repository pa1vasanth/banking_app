package net.java.bankingapp.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transfer_Records")
public class Transfer {
    @Id
    private String trans_id;

    @Column(name="SenderId")
    private Long senderid;

    @Column(name="RecieverId")
    private Long recieverid;

    private double amount;

}
