package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Produtos;

/**
 *
 * @author mathe
 */
public class ProdutosDAO extends DataBaseDAO implements InterfaceDAO<Integer, Produtos> {

    public ProdutosDAO() throws Exception {

    }

    @Override
    public void salvar(Produtos p) throws Exception {

        String sql = "INSERT INTO produtos ( nome, velocidade, valor) VALUES (?, ?, ?)";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, p.getNome());
        pst.setString(2, p.getVelocidade());
        pst.setDouble(3, p.getValor());

        pst.execute();

        desconectar();
    }

    @Override
    public void modificar(Produtos p) throws Exception {

        String sql = "UPDATE produtos SET nome=?, velocidade=?, valor=?, ativo=? WHERE id=?";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, p.getNome());
        pst.setString(2, p.getVelocidade());
        pst.setDouble(3, p.getValor());
        pst.setBoolean(4, p.isAtivo());

        pst.setInt(5, p.getId());
        pst.execute();

        desconectar();

    }

    @Override
    public void deletar(Produtos p) throws Exception {

        String sql = "DELETE FROM produtos WHERE id=?";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, p.getId());
        pst.execute();

        desconectar();
    }

    @Override
    public Produtos buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM produtos WHERE id=?";

        Produtos p = new Produtos();

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setVelocidade(rs.getString("velocidade"));
            p.setValor(rs.getDouble("valor"));
            p.setAtivo(rs.getBoolean("ativo"));
        }

        desconectar();
        return p;

    }

    @Override
    public List<Produtos> listar() throws Exception {

        String sql = "SELECT * FROM produtos";
        List<Produtos> listaP = new ArrayList<Produtos>();
        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            Produtos p = new Produtos();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setVelocidade(rs.getString("velocidade"));
            p.setValor(rs.getDouble("valor"));
            p.setAtivo(rs.getBoolean("ativo"));
            
            listaP.add(p);
            
        }
        desconectar();
        return listaP;

    }

}
