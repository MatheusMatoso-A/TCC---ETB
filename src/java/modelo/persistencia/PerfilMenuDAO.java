package modelo.persistencia;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Menu;
import modelo.entidades.Perfil;
import modelo.entidades.PerfilMenu;

public class PerfilMenuDAO extends DataBaseDAO implements InterfaceLoggable {

    private static final Logger LOGGER = Logger.getLogger(PerfilMenuDAO.class.getName());

    public PerfilMenuDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    public List<PerfilMenu> perfilMenuVinculado(Integer perfil_id) throws Exception {

        List<PerfilMenu> listaPM = new ArrayList<PerfilMenu>();

        String sql = "SELECT m.*, p.* FROM menu m "
                + "JOIN menu_perfil mp ON m.id = mp.menu_id "
                + "JOIN perfil p ON p.id = mp.perfil_id "
                + "WHERE p.id = ?";

        conectar();

        LOGGER.log(Level.INFO, "Iniciando a consulta de menus vinculados para o perfil_id: {0}", perfil_id);

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, perfil_id);

            try (ResultSet rs = pst.executeQuery();) {

                while (rs.next()) {

                    Menu m = new Menu();

                    m.setId(rs.getInt("m.id"));
                    m.setIcone(rs.getString("m.icone"));
                    m.setMenu(rs.getString("m.menu"));
                    m.setLink(rs.getString("m.link"));

                    Perfil p = new Perfil();

                    p.setId(rs.getInt("p.id"));
                    p.setPerfil(rs.getString("p.perfil"));

                    PerfilMenu pm = new PerfilMenu(p, m);

                    listaPM.add(pm);

                }

            }

            LOGGER.log(Level.INFO, "Consulta realizada com sucesso para o perfil_id: {0}", perfil_id);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao consultar PerfilMenu vinculados para o perfil_id: {0}. Mensagem: {1}",
                    new Object[]{perfil_id, e.getMessage()});
            throw e; // Re-lançar a exceção para que ela possa ser tratada em um nível superior
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro inesperado ao consultar PerfilMenu vinculados: {0}", e.getMessage());
            throw e; // Re-lançar a exceção para que ela possa ser tratada em um nível superior
        } finally {

            desconectar();
        }

        return listaPM;
    }

    public List<PerfilMenu> perfilMenuDesvinculado(Integer perfil_id) throws Exception {

        List<PerfilMenu> listaPM = new ArrayList<PerfilMenu>();

        String sql = "SELECT * FROM menu "
                + "WHERE id NOT IN (SELECT menu_id FROM menu_perfil WHERE perfil_id = ?)";

        conectar();

        LOGGER.log(Level.INFO, "Iniciando a consulta de menus vinculados para o perfil_id: {0}", perfil_id);

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, perfil_id);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {

                    Menu m = new Menu();

                    m.setId(rs.getInt("id"));
                    m.setIcone(rs.getString("icone"));
                    m.setMenu(rs.getString("menu"));
                    m.setLink(rs.getString("link"));

                    Perfil p = new Perfil();

                    p.setId(rs.getInt("perfil_id"));

                    PerfilMenu pm = new PerfilMenu(p, m);
                    listaPM.add(pm);

                }

            }
            LOGGER.log(Level.INFO, "Consulta realizada com sucesso para o perfil_id: {0}", perfil_id);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao consultar PerfilMenu vinculados para o perfil_id: {0}. Mensagem: {1}",
                    new Object[]{perfil_id, e.getMessage()});
            throw e; // Re-lançar a exceção para que ela possa ser tratada em um nível superior
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro inesperado ao consultar PerfilMenu vinculados: {0}", e.getMessage());
            throw e; // Re-lançar a exceção para que ela possa ser tratada em um nível superior
        } finally {

            desconectar();
        }

        return listaPM;
    }

    public void vincularPerfilMenu(PerfilMenu pm) throws Exception {

        String sql = "INSERT INTO menu_perfil (menu_id, perfil_id) VALUES(?,?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            // Logging de informações sobre o SQL e IDs que estão sendo processados
            LOGGER.log(Level.INFO, "Executando SQL: ", sql);
            LOGGER.log(Level.FINE, "Menu ID: {0}, Perfil ID: {1}", new Object[]{pm.getMenu().getId(), pm.getPerfil().getId()});

            pst.setInt(1, pm.getMenu().getId());
            pst.setInt(2, pm.getPerfil().getId());

            pst.execute();

            // Logging de sucesso
            LOGGER.log(Level.INFO, "Inserção bem-sucedida no banco de dados para Menu ID: {0} e Perfil ID: {1}", new Object[]{pm.getMenu().getId(), pm.getPerfil().getId()});

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            LOGGER.log(Level.SEVERE, "Erro ao inserir no banco de dados: {0}", e.getMessage());
            throw e; // relançando a exceção para tratamento em um nível superior, se necessário
        } catch (Exception e) {
            // Logging de erro para exceções gerais
            LOGGER.log(Level.SEVERE, "Erro inesperado: {0}", e.getMessage());
            throw e;
        } finally {

            desconectar();
        }

    }

    public void desvincularPerfilMenu(PerfilMenu pm) throws Exception {

        String sql = "DELETE FROM menu_perfil WHERE menu_id = ? AND perfil_id = ?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            // Logging de informações sobre o SQL e IDs que estão sendo processados
            LOGGER.log(Level.INFO, "Executando SQL: ", sql);
            LOGGER.log(Level.FINE, "Menu ID: {0}, Perfil ID: {1}", new Object[]{pm.getMenu().getId(), pm.getPerfil().getId()});

            pst.setInt(1, pm.getMenu().getId());
            pst.setInt(2, pm.getPerfil().getId());

            pst.execute();

            // Logging de sucesso
            LOGGER.log(Level.INFO, "Inserção bem-sucedida no banco de dados para Menu ID: {0} e Perfil ID: {1}", new Object[]{pm.getMenu().getId(), pm.getPerfil().getId()});

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            LOGGER.log(Level.SEVERE, "Erro ao inserir no banco de dados: {0}", e.getMessage());
            throw e; // relançando a exceção para tratamento em um nível superior, se necessário
        } catch (Exception e) {
            // Logging de erro para exceções gerais
            LOGGER.log(Level.SEVERE, "Erro inesperado: {0}", e.getMessage());
            throw e;
        } finally {

            desconectar();
        }

    }

}
