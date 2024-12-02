package modelo.entidades;

import lombok.*;

@Getter
@Setter
public class Funcionarios  {

    private int id;
    private double salario;
    private String matricula;
    private Usuario usuario;
    
    public Funcionarios() {

    }

    public Funcionarios(int id, double salario, String matricula, Usuario usuario) {
        this.id = id;
        this.salario = salario;
        this.matricula = matricula;
        this.usuario = usuario;
    
    }
    
     @Override
    public String toString() {
        return String.valueOf(this.id); // Substitua "id" pela chave primária ou outro atributo relevante
    }
}
