package modelo.persistencia;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Clientes;

public class ClientesDAO extends DataBaseDAO implements InterfaceLoggable, InterfaceDAO<Integer, Clientes> {

    private static final Logger LOGGER = Logger.getLogger(ClientesDAO.class.getName());

    UsuarioDAO daoUsuario = new UsuarioDAO();

    public ClientesDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    @Override
    public void salvar(Clientes c) throws Exception {

        String sql = "INSERT INTO cliente ( tipoEndereco, endereco, numero, complemento, "
                + "pontoReferencia, preCadasto, usuarios_id ) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        conectar();
        
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            
            
            
        } catch (Exception e) {
        }
        

    }

    @Override
    public void modificar(Clientes c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Clientes c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Clientes buscarPorId(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Clientes> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
