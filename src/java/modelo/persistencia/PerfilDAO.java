package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Perfil;

public class PerfilDAO extends DataBaseDAO implements InterfaceDAO<Integer, Perfil>, InterfaceLoggable {

    private static final Logger LOGGER = Logger.getLogger(PerfilDAO.class.getName());

    public PerfilDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    @Override
    public void salvar(Perfil p) throws Exception {

        String sql = "INSERT INTO perfil (perfil) VALUES (?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("Perfil: {0} ", p.getPerfil());

            pst.setString(1, p.getPerfil());

            pst.execute();

            logInfo("Inserido com sucesso no banco de dados para Perfil: {0} ", p.getPerfil());

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
    public void modificar(Perfil p) throws Exception {

        String sql = "UPDATE perfil SET perfil=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("Perfil: {0}, ID: {1} ", new Object[]{p.getPerfil(), p.getId()});

            pst.setString(1, p.getPerfil());

            pst.setInt(2, p.getId());

            pst.execute();
            logInfo("Modificado com sucesso no banco de dados para Perfil: {0}, ID: {1} ", new Object[]{p.getPerfil(), p.getId()});

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
    public void deletar(Perfil p) throws Exception {

        String sql = "DELETE FROM perfil WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", p.getId());

            pst.setInt(1, p.getId());

            pst.execute();

            logInfo("Excluido com sucesso no banco de dados para ID: {0}", p.getId());

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
    public Perfil buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM perfil WHERE id=?";

        Perfil p = new Perfil();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    p.setId(rs.getInt("id"));
                    p.setPerfil(rs.getString("perfil"));

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

        return p;

    }

    @Override
    public List<Perfil> listar() throws Exception {

        String sql = "SELECT * FROM perfil";

        List<Perfil> listaP = new ArrayList<Perfil>();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Perfil p = new Perfil();

                p.setId(rs.getInt("id"));
                p.setPerfil(rs.getString("perfil"));

                listaP.add(p);

            }

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

        return listaP;

    }

}
