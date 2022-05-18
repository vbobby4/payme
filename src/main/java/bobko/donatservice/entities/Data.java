package bobko.donatservice.entities;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Data {
    private String account;
    private Transaction statementItem;
}
