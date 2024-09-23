package modelo.entidades;

import java.util.Date;
import lombok.*;

@Getter
@Setter
public class Clientes {

    private int id;
    private String nome;
    private String telefone;
    private String cep;
    private String cpf;
    private String email;
    private String rg;
    private Date dataNascimento;
    private String login;
    private String senha;
    private Boolean ativo;
    private PreCadastro preCadastro;
    
    public Clientes(){
        
    }

    public Clientes(int id, String nome, String telefone, String cep, String cpf, String email, String rg, String login, String senha, Boolean ativo, Date dataNascimento,PreCadastro preCadastro) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cep = cep;
        this.cpf = cpf;
        this.email = email;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.preCadastro = preCadastro;
    }
}