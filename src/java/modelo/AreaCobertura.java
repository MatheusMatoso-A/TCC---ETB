package modelo;

import lombok.*;

@Getter
@Setter

public class AreaCobertura {
    
    private int id;
    private String cep;
    private String cidade;
    private String estado;

    public AreaCobertura(int id, String cep, String cidade, String estado) {
        this.id = id;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }
}
