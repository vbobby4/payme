package bobko.donatservice.entities;

import lombok.*;

import javax.persistence.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "trans")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transID;
    private String id;
    private long time;
    private String description;
    private String comment;
    private String mcc;
    private int amount;
    private boolean hold;
    private int operationAmount;
    private int currencyCode;
    private int commissionRate;
    private int cashbackAmount;
    private int balance;
    private String receiptId;
    private String counterEdrpou;
    private String counterIban;

}