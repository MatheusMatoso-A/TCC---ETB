package modelo.entidades;

import java.util.Date;
import lombok.*;



@Getter
@Setter
public class Usuario {
    
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private String login;
    private String senha;
    private Boolean ativo;
    private Perfil perfil;
    
    
    
     @Override
    public String toString() {
        return String.valueOf(this.id); // Substitua "id" pela chave primária ou outro atributo relevante
    }


    

}
