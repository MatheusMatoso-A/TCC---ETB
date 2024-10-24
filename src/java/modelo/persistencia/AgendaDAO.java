package modelo.persistencia;

import java.util.List;
import modelo.entidades.Agenda;

public class AgendaDAO  extends DataBaseDAO implements InterfaceDAO<Integer, Agenda>{

    
    public AgendaDAO() throws Exception{
        
    }
    
    @Override
    public void salvar(Agenda ag) throws Exception {
        
          String sql = "INSERT INTO agenda ( dataComparecimento, vendas_id) VALUES (?, ?)";
          
          conectar();
                  
        
        
        
    }

    @Override
    public void modificar(Agenda ag) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Agenda ag) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Agenda buscarPorId(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Agenda> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
