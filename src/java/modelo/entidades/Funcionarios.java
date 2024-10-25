package modelo.entidades;

import lombok.*;

@Getter
@Setter
public class Funcionarios extends Usuarios {

    private int id;
    private double salario;
    private String matricula;
    private Usuarios usuario;
    
    public Funcionarios() {

    }

    public Funcionarios(int id, double salario, String matricula, Usuarios usuario) {
        this.id = id;
        this.salario = salario;
        this.matricula = matricula;
        this.usuario = usuario;
    
    }
}
