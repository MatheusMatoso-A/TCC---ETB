package modelo.entidades;

import lombok.*;

@Getter
@Setter
public class Clientes {

    private int id;
    private Usuario usuario;
    private PreCadastro preCadastro;
    
    public Clientes(){
        
    }

    public Clientes(int id, Usuario usuario,PreCadastro preCadastro) {
        this.id = id;
        this.usuario = usuario;
        this.preCadastro = preCadastro;
    }
}
