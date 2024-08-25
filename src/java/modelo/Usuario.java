package modelo;

import lombok.*;

@Getter
@Setter

public class Usuario {

    private int id;
    private String login;
    private String senha;
    private Clientes cliente;
    private Funcionarios funcionario;

    public Usuario(int id, String login, String senha, Clientes cliente, Funcionarios funcionario) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }
}
