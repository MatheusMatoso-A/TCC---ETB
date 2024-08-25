
package modelo;

import java.time.LocalDateTime;
import lombok.*;


@Getter
@Setter
public class Agenda {
    
    private int id;
    private LocalDateTime dataComparecimento;
    private Funcionarios funcionario;

    public Agenda(int id, LocalDateTime dataComparecimento, Funcionarios funcionario) {
        this.id = id;
        this.dataComparecimento = dataComparecimento;
        this.funcionario = funcionario;
    }
}
