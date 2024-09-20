package modelo.entidades;

import lombok.*;

@Getter
@Setter
public class Moradia {

    private int id;
    private String tipo;
    private String endereco;
    private Integer numero;
    private Integer apartamento;
    private String bloco;
    private String condominio;
    private String pontoReferencia;
    private Clientes cliente;

    public Moradia(int id, String tipo, String endereco, Integer numero, Integer apartamento, String bloco, String condominio, String pontoReferencia, Clientes cliente) {

        this.id = id;
        this.tipo = tipo;
        this.endereco = endereco;
        this.numero = numero;
        this.apartamento = apartamento;
        this.bloco = bloco;
        this.condominio = condominio;
        this.pontoReferencia = pontoReferencia;
        this.cliente = cliente;

    }

}
