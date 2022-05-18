package bobko.donatservice.entities;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class FrontTransaction {
    private long transID;
    private String sender;
    private String comment;
    private float amount;
    private float balance;

    public FrontTransaction(long transID, String comment, float amount, float balance) {
        this.transID = transID;
        this.comment = comment;
        this.amount = amount;
        this.balance = balance;
    }
}
