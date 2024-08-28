package modelo;

import lombok.*;


@Getter
@Setter
public class Funcionarios {

    private int id;
    private String nome;
    private String cpf;
    private String cargo;
    private double salario;
    private String matricula;
    private String login;
    private String senha;

    public Funcionarios(int id, String nome, String cpf, String cargo, double salario, String matricula, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.matricula = matricula;
        this.login = login;
        this.senha = senha;
    }
}
