package bobko.donatservice.entities;

import lombok.*;


@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Response {
    private String type;
    private Data data;
}
