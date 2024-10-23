package modelo.entidades;

import lombok.*;

@Getter
@Setter
class Perfil {

    private int id;
    private String perfil;

    
    
    public Perfil(){
        
    }
    
    public Perfil(int id, String perfil){
        
        this.id = id;
        this.perfil = perfil;
        
    }
}