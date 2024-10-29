package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Menu;

public class MenuDAO extends DataBaseDAO implements InterfaceDAO<Integer, Menu> {

    public MenuDAO() throws Exception {

    }

    @Override
    public void salvar(Menu m) throws Exception {

        String sql = "INSERT INTO menu (icone, menu, link) VALUES (?,?,?)";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, m.getIcone());
        pst.setString(2, m.getMenu());
        pst.setString(3, m.getLink());

        pst.execute();

        desconectar();

    }

    @Override
    public void modificar(Menu m) throws Exception {

        String sql = "UPDATE menu SET icone=?, menu=?, link=? WHERE id=?";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, m.getIcone());
        pst.setString(2, m.getMenu());
        pst.setString(3, m.getLink());

        pst.setInt(4, m.getId());

        pst.execute();

        desconectar();

    }

    @Override
    public void deletar(Menu m) throws Exception {

        String sql = "DELETE FROM menu WHERE id=?";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, m.getId());

        pst.execute();

        desconectar();

    }

    @Override
    public Menu buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM menu WHERE id=?";

        Menu m = new Menu();

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            m.setId(rs.getInt("id"));
            m.setIcone(rs.getString("icone"));
            m.setMenu(rs.getString("menu"));
            m.setLink(rs.getString("link"));

        }

        desconectar();
        return m;

    }

    @Override
    public List<Menu> listar() throws Exception {

        String sql = "SELECT * FROM menu";
        List<Menu> listaM = new ArrayList<Menu>();

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Menu m = new Menu();

            m.setId(rs.getInt("id"));
            m.setIcone(rs.getString("icone"));
            m.setMenu(rs.getString("menu"));
            m.setLink(rs.getString("link"));

            listaM.add(m);

        }

        desconectar();

        return listaM;

    }

}
