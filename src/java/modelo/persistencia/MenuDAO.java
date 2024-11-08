package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Menu;

public class MenuDAO extends DataBaseDAO implements InterfaceDAO<Integer, Menu>, InterfaceLoggable {

    private static final Logger LOGGER = Logger.getLogger(MenuDAO.class.getName());

    public MenuDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    @Override
    public void salvar(Menu m) throws Exception {

        String sql = "INSERT INTO menu (icone, menu, link) VALUES (?,?,?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("Icone: {0}, Menu: {1}, Link: {2}", new Object[]{m.getIcone(), m.getMenu(), m.getLink()});

            pst.setString(1, m.getIcone());
            pst.setString(2, m.getMenu());
            pst.setString(3, m.getLink());

            pst.execute();

            logInfo("Inserido com sucesso no banco de dados para Icone: {0}, Menu: {1}, Link: {2}", new Object[]{m.getIcone(), m.getMenu(), m.getLink()});

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
    public void modificar(Menu m) throws Exception {

        String sql = "UPDATE menu SET icone=?, menu=?, link=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("Icone: {0}, Menu: {1}, Link: {2}, ID: {4} ", new Object[]{m.getIcone(), m.getMenu(), m.getLink(), m.getId()});

            pst.setString(1, m.getIcone());
            pst.setString(2, m.getMenu());
            pst.setString(3, m.getLink());

            pst.setInt(4, m.getId());

            pst.execute();

            logInfo("Modificado com sucesso no banco de dados paraIcone: {0}, Menu: {1}, Link: {2}, ID: {4} ", new Object[]{m.getIcone(), m.getMenu(), m.getLink(), m.getId()});

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
    public void deletar(Menu m) throws Exception {

        String sql = "DELETE FROM menu WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", m.getId());

            pst.setInt(1, m.getId());

            pst.execute();

            logInfo("Excluido com sucesso no banco de dados para ID: {0}", m.getId());

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
    public Menu buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM menu WHERE id=?";

        Menu m = new Menu();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    m.setId(rs.getInt("id"));
                    m.setIcone(rs.getString("icone"));
                    m.setMenu(rs.getString("menu"));
                    m.setLink(rs.getString("link"));

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

        return m;

    }

    @Override
    public List<Menu> listar() throws Exception {

        String sql = "SELECT * FROM menu";
        List<Menu> listaM = new ArrayList<Menu>();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Menu m = new Menu();

                m.setId(rs.getInt("id"));
                m.setIcone(rs.getString("icone"));
                m.setMenu(rs.getString("menu"));
                m.setLink(rs.getString("link"));

                listaM.add(m);

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

        return listaM;

    }

}
