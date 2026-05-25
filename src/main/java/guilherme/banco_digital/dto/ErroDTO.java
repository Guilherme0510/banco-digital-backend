package guilherme.banco_digital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErroDTO {
    public int status;
    public String message;
}
