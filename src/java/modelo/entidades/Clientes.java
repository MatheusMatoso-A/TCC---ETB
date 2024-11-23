package modelo.entidades;

import lombok.*;

@Getter
@Setter
public class Clientes {

    private int id;
    private String tipoEndereco;
    private String cep;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String pontoReferencia;
    private Usuario usuario;
    private PreCadastro preCadastro;

    public Clientes() {

    }

    public Clientes(int id, Usuario usuario, PreCadastro preCadastro, String tipoEndereco,String cep, String endereco, Integer numero, String complemento, String pontoReferencia) {

        this.id = id;
        this.usuario = usuario;
        this.preCadastro = preCadastro;
        this.tipoEndereco = tipoEndereco;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.pontoReferencia = pontoReferencia;
    }
}
