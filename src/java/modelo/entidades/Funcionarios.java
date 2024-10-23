package modelo.entidades;

import lombok.*;

@Getter
@Setter
public class Funcionarios extends Usuarios {

    private int id;
    private String cargo;
    private double salario;
    private String matricula;
    private Usuarios usuario;
    
    public Funcionarios() {

    }

    public Funcionarios(int id, String cargo, double salario, String matricula, Usuarios usuario) {
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
        this.matricula = matricula;
        this.usuario = usuario;
    
    }
}
