package modelo.persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Usuario;

public class UsuarioDAO extends DataBaseDAO implements InterfaceLoggable, InterfaceDAO<Integer, Usuario> {

    private static final Logger LOGGER = Logger.getLogger(UsuarioDAO.class.getName());
    private PerfilDAO daoPerfil = new PerfilDAO();

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

        try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            logInfo("Executando SQL: ", sql);
            logFine("Nome: {0}, CPF: {1}, Telefone: {2}, Email: {3}, DataNascimento: {4}, Login: {5}, "
                    + "Senha: {6}, Ativo: {7}, Perfil_ID: {8} ", new Object[]{u.getNome(), u.getCpf(),
                        u.getTelefone(), u.getEmail(), u.getDataNascimento(), u.getLogin(), u.getSenha(), u.getAtivo(),
                        u.getPerfil().getId()});

            pst.setString(1, u.getNome());
            pst.setString(2, u.getCpf());
            pst.setString(3, u.getTelefone());
            pst.setString(4, u.getEmail());
            pst.setDate(5, new java.sql.Date(u.getDataNascimento().getTime()));
            pst.setString(6, u.getLogin());
            pst.setString(7, u.getSenha());
            pst.setBoolean(8, u.getAtivo());
            pst.setInt(9, u.getPerfil().getId());

            int valoresInseridos = pst.executeUpdate();

            if (valoresInseridos > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        u.setId(rs.getInt(1));

                    }

                }

            }

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
            pst.setDate(5, new java.sql.Date(u.getDataNascimento().getTime()));
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

        Usuario u = new Usuario();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setCpf(rs.getString("cpf"));
                    u.setTelefone(rs.getString("telefone"));
                    u.setEmail(rs.getString("email"));
                    u.setDataNascimento(rs.getDate("dataNascimento"));
                    u.setLogin(rs.getString("login"));
                    u.setSenha(rs.getString("senha"));
                    u.setAtivo(rs.getBoolean("ativo"));
                    u.setPerfil(daoPerfil.buscarPorId(rs.getInt("perfil_id")));

                } else {

                    return null;
                }

            }

            logInfo("Pesquisa por ID bem-sucedida no banco de dados para o ID: {0}", id);

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            logSevere("Erro ao pesquisar por ID no banco de dados: {0}", e.getMessage());
            throw e;

        } catch (Exception e) {
            // Logging de erro para exceções gerais
            logSevere("Erro inesperado: {0}", e.getMessage());
            throw e;

        } finally {

            desconectar();
        }

        return u;

    }

    @Override
    public List<Usuario> listar() throws Exception {

        String sql = "SELECT * FROM usuarios";

        List<Usuario> listaU = new ArrayList<Usuario>();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setTelefone(rs.getString("telefone"));
                u.setEmail(rs.getString("email"));
                u.setDataNascimento(rs.getDate("dataNascimento"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setAtivo(rs.getBoolean("ativo"));
                u.setPerfil(daoPerfil.buscarPorId(rs.getInt("perfil_id")));

                listaU.add(u);

            }

            logInfo("Pesquisa realizada com sucesso", "");

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            logSevere("Erro ao pesquisar no banco de dados: {0}", e.getMessage());
            throw e;

        } catch (Exception e) {
            // Logging de erro para exceções gerais
            logSevere("Erro inesperado: {0}", e.getMessage());
            throw e;

        } finally {

            desconectar();
        }

        return listaU;
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

    public Usuario logar(String login, String senha) throws Exception {

        String sql = "SELECT * FROM usuarios WHERE login=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL {0} para o Login {1} :", new Object[]{sql, login});
            logFine("Login: {0}", login);

            pst.setString(1, login);

            try (ResultSet rs = pst.executeQuery()) {

                Usuario u = new Usuario();

                if (rs.next()) {

                    if (senha.equals(rs.getString("senha"))) {

                        u.setId(rs.getInt("id"));
                        u.setNome(rs.getString("nome"));
                        u.setCpf(rs.getString("cpf"));
                        u.setTelefone(rs.getString("telefone"));
                        u.setEmail(rs.getString("email"));
                        u.setDataNascimento(rs.getDate("dataNascimento"));
                        u.setLogin(rs.getString("login"));
                        u.setSenha(rs.getString("senha"));
                        u.setAtivo(rs.getBoolean("ativo"));
                        u.setPerfil(daoPerfil.buscarPorId(rs.getInt("perfil_id")));

                        return u;

                    }
                }

            }

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            logSevere("Erro ao verificar o login: {0}", e.getMessage());
            throw e;

        } catch (Exception e) {
            // Logging de erro para exceções gerais
            logSevere("Erro inesperado: {0}", e.getMessage());
            throw e;

        } finally {

            desconectar();
        }

        return null;
    }

    public List<Usuario> listarTecnico() throws Exception {

        String sql = "SELECT * FROM usuarios WHERE perfil_id='3' AND ativo='1'";

        List<Usuario> listaTec = new ArrayList<Usuario>();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setTelefone(rs.getString("telefone"));
                u.setEmail(rs.getString("email"));
                u.setDataNascimento(rs.getDate("dataNascimento"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setAtivo(rs.getBoolean("ativo"));
                u.setPerfil(daoPerfil.buscarPorId(rs.getInt("perfil_id")));

                listaTec.add(u);

            }

            logInfo("Pesquisa realizada com sucesso", "");

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            logSevere("Erro ao pesquisar no banco de dados: {0}", e.getMessage());
            throw e;

        } catch (Exception e) {
            // Logging de erro para exceções gerais
            logSevere("Erro inesperado: {0}", e.getMessage());
            throw e;

        } finally {

            desconectar();
        }

        return listaTec;
    }

}
