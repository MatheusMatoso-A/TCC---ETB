
package modelo.entidades;

import java.time.LocalDateTime;
import lombok.*;


@Getter
@Setter
public class Agenda {
    
    private int id;
    private LocalDateTime dataComparecimento;
    private String status;
    private Vendas vendas;

    
    public Agenda(){
        
    }
    
    public Agenda(int id, LocalDateTime dataComparecimento, String status, Vendas vendas) {
        this.id = id;
        this.dataComparecimento = dataComparecimento;
        this.status = status;
        this.vendas = vendas;
    }
}
