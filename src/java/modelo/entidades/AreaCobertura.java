package modelo.entidades;

import lombok.*;

@Getter
@Setter

public class AreaCobertura {

    private int id;
    private String cep;
    private String cidade;
    private String estado;

    public AreaCobertura() {

    }

    public AreaCobertura(int id, String cep, String cidade, String estado) {
        this.id = id;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id); // Substitua "id" pela chave primária ou outro atributo relevante
    }
}
