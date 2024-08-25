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
    
}
