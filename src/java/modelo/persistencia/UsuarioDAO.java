package modelo.persistencia;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Usuario;

public class UsuarioDAO extends DataBaseDAO implements InterfaceLoggable, InterfaceDAO<Integer, Usuario> {

    private static final Logger LOGGER = Logger.getLogger(UsuarioDAO.class.getName());

    public UsuarioDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    @Override
    public void salvar(Usuario u) throws Exception {

        String sql = "INSERT INTO usuarios ( nome, cpf, telefone, email, dataNascimento, login, senha, ativo, perfil_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: ", sql);

            pst.setString(1, u.getNome());
            pst.setString(2, u.getCpf());
            pst.setString(3, u.getTelefone());
            pst.setString(4, u.getEmail());
            

        } catch (Exception e) {
        }

    }

    @Override
    public void modificar(Usuario u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Usuario u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario buscarPorId(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
