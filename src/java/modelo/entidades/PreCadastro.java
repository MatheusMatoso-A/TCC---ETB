package modelo.entidades;

import lombok.*;

@Getter
@Setter

public class PreCadastro {

    
    private int id;
    private String nome;
    private String cep;
    private String telefone;
    private String cidade;
    private String email;
    private AreaCobertura areaCobertura;

    public PreCadastro(int id, String nome, String cep, String telefone, String cidade, String email, AreaCobertura areaCobertura) {
        this.id = id;
        this.nome = nome;
        this.cep = cep;
        this.telefone = telefone;
        this.cidade = cidade;
        this.email = email;
        this.areaCobertura = areaCobertura;
    }
}
