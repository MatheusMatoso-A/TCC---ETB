package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Perfil;

public class PerfilDAO extends DataBaseDAO implements InterfaceDAO<Integer, Perfil> {

    public PerfilDAO() throws Exception {

    }

    @Override
    public void salvar(Perfil p) throws Exception {

        String sql = "INSERT INTO perfil (perfil) VALUES (?)";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, p.getPerfil());

        pst.execute();

        desconectar();

    }

    @Override
    public void modificar(Perfil p) throws Exception {

        String sql = "UPDATE perfil SET perfil=? WHERE id=?";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, p.getPerfil());

        pst.setInt(2, p.getId());

        pst.execute();

        desconectar();

    }

    @Override
    public void deletar(Perfil p) throws Exception {

        String sql = "DELETE FROM perfil WHERE id=?";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, p.getId());

        pst.execute();

        desconectar();

    }

    @Override
    public Perfil buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM perfil WHERE id=?";

        Perfil p = new Perfil();

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {

            p.setId(rs.getInt("id"));
            p.setPerfil(rs.getString("perfil"));

        }

        desconectar();

        return p;

    }

    @Override
    public List<Perfil> listar() throws Exception {

        String sql = "SELECT * FROM perfil";

        List<Perfil> listaP = new ArrayList<Perfil>();

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Perfil p = new Perfil();

            p.setId(rs.getInt("id"));
            p.setPerfil(rs.getString("perfil"));

            listaP.add(p);

        }

        desconectar();

        return listaP;

    }

}
