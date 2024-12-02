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
    

    
    
    public PreCadastro(){
        
    }
    
    public PreCadastro(int id, String nome, String cep, String telefone, String cidade, String email, AreaCobertura areaCobertura) {
        this.id = id;
        this.nome = nome;
        this.cep = cep;
        this.telefone = telefone;
        this.cidade = cidade;
        this.email = email;
        this.areaCobertura = areaCobertura;
    }

    public void setAreaCobertura(int na) {
        
        this.areaCobertura = new AreaCobertura();
        this.areaCobertura.setId(na);
    }

    public void setAreaCobertura(AreaCobertura ac) {

        this.areaCobertura = ac;
        
    }
     @Override
    public String toString() {
        return String.valueOf(this.id); // Substitua "id" pela chave primária ou outro atributo relevante
    }
}
