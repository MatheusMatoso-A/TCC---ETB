
package modelo;

import java.time.LocalDateTime;
import lombok.*;


@Getter
@Setter
public class Agenda {
    
    private int id;
    private LocalDateTime dataComparecimento;
    private Funcionarios funcionario;
    
}
