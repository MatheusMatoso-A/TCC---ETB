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

public class PerfilMenuDAO extends DataBaseDAO {

    private static final Logger logger = Logger.getLogger(PerfilMenuDAO.class.getName());

    public PerfilMenuDAO() throws Exception {

    }

    public List<PerfilMenu> perfilMenuVinculado(Integer perfil_id) throws Exception {

        List<PerfilMenu> listaPM = new ArrayList<PerfilMenu>();

        String sql = "SELECT m.*, p.* FROM menu m "
                + "JOIN menu_perfil mp ON m.id = mp.menu_id "
                + "JOIN perfil p ON p.id = mp.perfil_id "
                + "WHERE p.id = ?";

        conectar();

        logger.log(Level.INFO, "Iniciando a consulta de menus vinculados para o perfil_id:{0}", perfil_id);

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
            
            logger.log(Level.INFO, "Conulta realizada com sucesso para o perfil_id:{0}", perfil_id);
            
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao consultar PerfilMenu para o perfil_id: " + perfil_id, e);
            throw e; // Re-lançar a exceção para que ela possa ser tratada em um nível superior
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro inesperado ao consultar PerfilMenu", e);
            throw e; // Re-lançar a exceção para que ela possa ser tratada em um nível superior
        }

        return listaPM;
    }

}
