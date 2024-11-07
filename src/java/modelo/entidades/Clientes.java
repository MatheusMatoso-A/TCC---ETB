package modelo.entidades;

import lombok.*;

@Getter
@Setter
public class Clientes {

    private int id;
    private Usuario usuario;
    private PreCadastro preCadastro;
    private String tipo;
    private String endereco;
    private Integer numero;
    private Integer apartamento;
    private String bloco;
    private String condominio;
    private String pontoReferencia;

    public Clientes() {

    }

    public Clientes(int id, Usuario usuario, PreCadastro preCadastro, String tipo, String endereco, Integer numero, Integer apartamento, String bloco, String condominio, String pontoReferencia) {
        
        this.id = id;
        this.usuario = usuario;
        this.preCadastro = preCadastro;
        this.tipo = tipo;
        this.endereco = endereco;
        this.numero = numero;
        this.apartamento = apartamento;
        this.bloco = bloco;
        this.condominio = condominio;
        this.pontoReferencia = pontoReferencia;
    }
}
