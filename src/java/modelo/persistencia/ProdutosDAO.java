package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Produtos;

/**
 *
 * @author mathe
 */
public class ProdutosDAO extends DataBaseDAO implements InterfaceDAO<Integer, Produtos>, InterfaceLoggable {

    private static final Logger LOGGER = Logger.getLogger(ProdutosDAO.class.getName());

    public ProdutosDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    @Override
    public void salvar(Produtos p) throws Exception {

        String sql = "INSERT INTO produtos ( nome, velocidade, valor) VALUES (?, ?, ?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: ", sql);
            logFine("Nome: {0}, Velocidade: {1}, Valor: {2}", new Object[]{p.getNome(), p.getVelocidade(), p.getValor()});

            pst.setString(1, p.getNome());
            pst.setString(2, p.getVelocidade());
            pst.setDouble(3, p.getValor());

            pst.execute();

            logInfo("Inserção bem-sucedida no banco de dados para Nome: {0}, Velocidade: {1}, Valor: {2}", new Object[]{p.getNome(), p.getVelocidade(), p.getValor()});

        } catch (SQLException e) {
            // Logging de erro com detalhes específicos da SQLException
            logSevere("Erro ao inserir no banco de dados: {0}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Logging de erro para exceções gerais
            logSevere("Erro inesperado: {0}", e.getMessage());
            throw e;
        }

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

        while (rs.next()) {
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
