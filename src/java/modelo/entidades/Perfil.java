package modelo.entidades;

import lombok.*;

@Getter
@Setter

public class Perfil {

    private int id;
    private String perfil;

    public Perfil() {

    }

    public Perfil(int id, String perfil) {

        this.id = id;
        this.perfil = perfil;

    }
     @Override
    public String toString() {
        return String.valueOf(this.id); // Substitua "id" pela chave primária ou outro atributo relevante
    }
}
