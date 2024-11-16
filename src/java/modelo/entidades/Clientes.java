package modelo.entidades;

import lombok.*;

@Getter
@Setter
public class Clientes {

    private int id;
    private String tipo;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String pontoReferencia;
    private Usuario usuario;
    private PreCadastro preCadastro;

    public Clientes() {

    }

    public Clientes(int id, Usuario usuario, PreCadastro preCadastro, String tipo, String endereco, Integer numero, String complemento, String pontoReferencia) {

        this.id = id;
        this.usuario = usuario;
        this.preCadastro = preCadastro;
        this.tipo = tipo;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.pontoReferencia = pontoReferencia;
    }
}
