package modelo.entidades;

import lombok.*;

@Getter
@Setter

public class Produtos {

    private int id;
    private String nome;
    private String velocidade;
    private double valor;

    public Produtos(int id, String nome, String velocidade, double valor) {
        this.id = id;
        this.nome = nome;
        this.velocidade = velocidade;
        this.valor = valor;
    }
}
