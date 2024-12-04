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

            logInfo("Executando SQL: {0}", sql);
            logFine("Nome: {0}, Velocidade: {1}, Valor: {2}", new Object[]{p.getNome(), p.getVelocidade(), p.getValor()});

            pst.setString(1, new String(p.getNome().getBytes("ISO-8859-1"), "UTF-8"));
            pst.setString(2, new String(p.getVelocidade().getBytes("ISO-8859-1"), "UTF-8"));
            pst.setDouble(3, p.getValor());

            pst.execute();

            logInfo("Inserido com sucesso no banco de dados para Nome: {0}, Velocidade: {1}, Valor: {2}", new Object[]{p.getNome(), p.getVelocidade(), p.getValor()});

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
    public void modificar(Produtos p) throws Exception {

        String sql = "UPDATE produtos SET nome=?, velocidade=?, valor=?, ativo=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("Nome: {0}, Velocidade: {1}, Valor: {2}, "
                    + "Ativo: {3}, ID: {4} ", new Object[]{p.getNome(), p.getVelocidade(), p.getValor(), p.isAtivo(), p.getId()});

            pst.setString(1, new String(p.getNome().getBytes("ISO-8859-1"), "UTF-8"));
            pst.setString(2, new String(p.getVelocidade().getBytes("ISO-8859-1"), "UTF-8"));
            pst.setDouble(3, p.getValor());
            pst.setBoolean(4, p.isAtivo());

            pst.setInt(5, p.getId());

            pst.execute();

            logInfo("Modificado com sucesso no banco de dados para Nome: {0}, Velocidade: {1}, Valor: {2}, "
                    + "Ativo: {3}, ID: {4} ", new Object[]{p.getNome(), p.getVelocidade(), p.getValor(), p.isAtivo(), p.getId()});

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
    public void deletar(Produtos p) throws Exception {

        String sql = "DELETE FROM produtos WHERE id=?";

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
    public Produtos buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM produtos WHERE id=?";

        Produtos p = new Produtos();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setVelocidade(rs.getString("velocidade"));
                    p.setValor(rs.getDouble("valor"));
                    p.setAtivo(rs.getBoolean("ativo"));
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
    public List<Produtos> listar() throws Exception {

        String sql = "SELECT * FROM produtos";
        List<Produtos> listaP = new ArrayList<Produtos>();
        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Produtos p = new Produtos();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setVelocidade(rs.getString("velocidade"));
                p.setValor(rs.getDouble("valor"));
                p.setAtivo(rs.getBoolean("ativo"));

                listaP.add(p);

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

        return listaP;

    }

    public List<Produtos> listarAtivos() throws Exception {

        String sql = "SELECT * FROM produtos WHERE ativo=1";
        List<Produtos> listaP = new ArrayList<Produtos>();
        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Produtos p = new Produtos();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setVelocidade(rs.getString("velocidade"));
                p.setValor(rs.getDouble("valor"));
                p.setAtivo(rs.getBoolean("ativo"));

                listaP.add(p);

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

        return listaP;

    }

}
