package modelo.persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            logFine("Nome: {0}, CPF: {1}, Telefone: {2}, Email: {3}, DataNascimento: {4}, Login: {5}, "
                    + "Senha: {6}, Ativo: {7}, Perfil_ID: {8} ", new Object[]{u.getNome(), u.getCpf(),
                        u.getTelefone(), u.getEmail(), u.getDataNascimento(), u.getLogin(), u.getSenha(), u.getAtivo(),
                        u.getPerfil().getId()});

            pst.setString(1, u.getNome());
            pst.setString(2, u.getCpf());
            pst.setString(3, u.getTelefone());
            pst.setString(4, u.getEmail());
            pst.setDate(5, (Date) u.getDataNascimento());
            pst.setString(6, u.getLogin());
            pst.setString(7, u.getSenha());
            pst.setBoolean(8, u.getAtivo());
            pst.setInt(9, u.getPerfil().getId());

            pst.execute();

            logInfo("Inserido com sucesso no banco de dados para Nome: {0}, CPF: {1}, Telefone: {2}, Email: {3}, DataNascimento: {4}, Login: {5}, "
                    + "Senha: {6}, Ativo: {7}, Perfil_ID: {8} ", new Object[]{u.getNome(), u.getCpf(),
                        u.getTelefone(), u.getEmail(), u.getDataNascimento(), u.getLogin(), u.getSenha(), u.getAtivo(),
                        u.getPerfil().getId()});

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            logSevere("Erro ao inserir no banco de dados: {0}", e.getMessage());
            throw e;

        } catch (Exception e) {
            // Logging de erro para exceções gerais
            logSevere("Erro inesperado: {0}", e.getMessage());
            throw e;

        } finally {

            desconectar();
        }

    }

    @Override
    public void modificar(Usuario u) throws Exception {

        String sql = "UPDATE usuarios SET nome=?, cpf=?, telefone=?, email=?, dataNascimento=?, "
                + "login=?, senha=?, ativo=?, perfil_id=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: ", sql);
            logFine("Nome: {0}, CPF: {1}, Telefone: {2}, Email: {3}, DataNascimento: {4}, Login: {5}, "
                    + "Senha: {6}, Ativo: {7}, Perfil_ID: {8}, ID: {9} ", new Object[]{u.getNome(), u.getCpf(),
                        u.getTelefone(), u.getEmail(), u.getDataNascimento(), u.getLogin(), u.getSenha(), u.getAtivo(),
                        u.getPerfil().getId(), u.getId()});

            pst.setString(1, u.getNome());
            pst.setString(2, u.getCpf());
            pst.setString(3, u.getTelefone());
            pst.setString(4, u.getEmail());
            pst.setDate(5, (Date) u.getDataNascimento());
            pst.setString(6, u.getLogin());
            pst.setString(7, u.getSenha());
            pst.setBoolean(8, u.getAtivo());
            pst.setInt(9, u.getPerfil().getId());

            pst.setInt(10, u.getId());

            pst.execute();

            logInfo("Modificado com sucesso no banco de dados para Nome: {0}, CPF: {1}, Telefone: {2}, Email: {3}, DataNascimento: {4}, Login: {5}, "
                    + "Senha: {6}, Ativo: {7}, Perfil_ID: {8}, ID: {9} ", new Object[]{u.getNome(), u.getCpf(),
                        u.getTelefone(), u.getEmail(), u.getDataNascimento(), u.getLogin(), u.getSenha(), u.getAtivo(),
                        u.getPerfil().getId(), u.getId()});

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            logSevere("Erro ao modificar no banco de dados: {0}", e.getMessage());
            throw e;

        } catch (Exception e) {
            // Logging de erro para exceções gerais
            logSevere("Erro inesperado: {0}", e.getMessage());
            throw e;

        } finally {

            desconectar();
        }

    }

    @Override
    public void deletar(Usuario u) throws Exception {

        String sql = "DELETE FROM usuarios WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: ", sql);
            logFine("ID: {0}", u.getId());

            pst.setInt(1, u.getId());

            pst.execute();

            logInfo("Excluido com sucesso no banco de dados para ID: {0}", u.getId());
            
        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            logSevere("Erro ao deletar no banco de dados: {0}", e.getMessage());
            throw e;

        } catch (Exception e) {
            // Logging de erro para exceções gerais
            logSevere("Erro inesperado: {0}", e.getMessage());
            throw e;

        } finally {

            desconectar();
        }

    }

    @Override
    public Usuario buscarPorId(Integer id) throws Exception {
        
        String sql = "SELECT * FROM usuarios WHERE id=?";
        
        Usuario u = new Usuario() ;

        
        
        
        
        return null;
    }

    @Override
    public List<Usuario> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Método para verificar se o Email,CPF ou Login já está em uso 
    public boolean usuarioUnico(String cpf, String email, String login) throws Exception {

        String sql = "SELECT COUNT(*) FROM usuarios WHERE cpf = ? OR email = ? OR login = ?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: ", sql);
            logFine("CPF: {0}, Email: {1}, Login {2} ", new Object[]{cpf, email, login});

            pst.setString(1, cpf);
            pst.setString(2, email);
            pst.setString(3, login);

            //Executa consulta
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0; //Retorna verdadeira se algum dos campos já estiver em uso

            }

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            logSevere("Erro ao verificar a unicidade dos campos: {0}", e.getMessage());
            throw e;

        } catch (Exception e) {
            // Logging de erro para exceções gerais
            logSevere("Erro inesperado: {0}", e.getMessage());
            throw e;

        } finally {

            desconectar();
        }

        // Retorna false se nenhum campo estiver em uso. 
        return false;

    }

}
