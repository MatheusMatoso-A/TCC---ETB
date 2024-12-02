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
    
     @Override
    public String toString() {
        return String.valueOf(this.id); // Substitua "id" pela chave primária ou outro atributo relevante
    }

}
