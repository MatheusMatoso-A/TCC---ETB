package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Funcionarios;

public class FuncionariosDAO extends DataBaseDAO implements InterfaceLoggable, InterfaceDAO<Integer, Funcionarios> {

    private static final Logger LOGGER = Logger.getLogger(FuncionariosDAO.class.getName());
    private UsuarioDAO daoUsuario = new UsuarioDAO();

    public FuncionariosDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    @Override
    public void salvar(Funcionarios f) throws Exception {

        String sql = "INSERT INTO funcionarios ( salario, matricula, usuarios_id ) VALUES (?, ?, ?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("Salario: {0}, Matricula{1}, Usuarios_ID: {2} ", new Object[]{f.getSalario(), f.getMatricula(), f.getUsuario().getId()});

            pst.setDouble(1, f.getSalario());
            pst.setString(2, f.getMatricula());
            pst.setInt(3, f.getUsuario().getId());

            pst.execute();

            logInfo("Inserido com sucesso no banco de dados para Salario: {0}, Matricula: {1}, Usuario_ID: {2}",
                    new Object[]{f.getSalario(), f.getMatricula(), f.getUsuario().getId()});

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
    public void modificar(Funcionarios f) throws Exception {

        String sql = "UPDATE funcionarios SET salario=?, matricula=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("Salario: {0}, Matricula{1}, ID: {2} ", new Object[]{f.getSalario(), f.getMatricula(), f.getId()});

            pst.setDouble(1, f.getSalario());
            pst.setString(2, f.getMatricula());

            pst.setInt(3, f.getId());

            pst.execute();

            logInfo("Modificado com sucesso no banco de dados para Salario: {0}, Matricula{1}, ID: {2} ",
                    new Object[]{f.getSalario(), f.getMatricula(), f.getId()});

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
    public void deletar(Funcionarios f) throws Exception {

        String sql = "DELETE FROM funcionarios WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", f.getId());

            pst.setInt(1, f.getId());
            pst.execute();

            logInfo("Excluido com sucesso no banco de dados para ID: {0}", f.getId());

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
    public Funcionarios buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM funcionarios WHERE id=?";

        Funcionarios f = new Funcionarios();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    f.setId(rs.getInt("id"));
                    f.setSalario(rs.getDouble("salario"));
                    f.setMatricula(rs.getString("matricula"));
                    f.setUsuario(daoUsuario.buscarPorId(rs.getInt("usuarios_id")));

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

        return f;
    }

    @Override
    public List<Funcionarios> listar() throws Exception {

        String sql = "SELECT * FROM funcionarios";

        List<Funcionarios> listaF = new ArrayList<Funcionarios>();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Funcionarios f = new Funcionarios();

                f.setId(rs.getInt("id"));
                f.setSalario(rs.getDouble("salario"));
                f.setMatricula(rs.getString("matricula"));
                f.setUsuario(daoUsuario.buscarPorId(rs.getInt("usuarios_id")));

                listaF.add(f);
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

        return listaF;

    }

}
