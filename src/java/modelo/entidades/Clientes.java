package modelo.entidades;

import java.util.Date;
import lombok.*;

@Getter
@Setter
public class Clientes extends Usuarios{

    private int id;
    private Usuarios usuario;
    private PreCadastro preCadastro;
    
    public Clientes(){
        
    }

    public Clientes(int id, Usuarios usuario,PreCadastro preCadastro) {
        this.id = id;
        this.usuario = usuario;
        this.preCadastro = preCadastro;
    }
}
