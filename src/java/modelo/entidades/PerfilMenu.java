package modelo.entidades;

import lombok.*;


@Getter
@Setter
public class PerfilMenu {
    
    private Perfil perfil;
    private Menu menu;
    
    public PerfilMenu() {
    
    }
    
    public PerfilMenu(Perfil perfil, Menu menu){
        
        this.perfil = perfil;
        this.menu = menu;
        
    }

}
