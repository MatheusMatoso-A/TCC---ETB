package modelo.entidades;

import lombok.*;


@Getter
@Setter
public class Menu {
    
    private int id;
    private String icone;
    private String menu;
    private String link;
    
    
    public Menu(){
        
    }
    
    public Menu(int id, String icone, String menu, String link){
        
        this.id = id;
        this.icone = icone;
        this.menu = menu;
        this.link = link;
        
        
    }
    
    

}
