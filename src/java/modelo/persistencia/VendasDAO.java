package modelo.persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Vendas;

public class VendasDAO extends DataBaseDAO implements InterfaceLoggable, InterfaceDAO<Integer, Vendas> {

    private static final Logger LOGGER = Logger.getLogger(VendasDAO.class.getName());
    private ProdutosDAO daoProdutos = new ProdutosDAO();
    private FuncionariosDAO daoFuncionarios = new FuncionariosDAO();
    private ClientesDAO daoClientes = new ClientesDAO();

    public VendasDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    @Override
    public void salvar(Vendas v) throws Exception {

        String sql = "INSERT INTO vendas ( foiPago, dataVenda, dataVencimento, produtos_id, funcionarios_id, cliente_id ) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("FoiPago: {0}, DataVenda: {1}, DataVencimento: {2}, Produtos_ID: {3}, "
                    + "Funcionarios_ID: {4}, Clientes_ID: {5} ", new Object[]{v.isFoiPago(), v.getDataVenda(), v.getDataVencimento(),
                        v.getProdutos().getId(), v.getFuncionario().getId(), v.getCliente().getId()});

            pst.setBoolean(1, v.isFoiPago());
            pst.setDate(2, new java.sql.Date(v.getDataVenda().getTime()));
            pst.setString(3, v.getDataVencimento());
            pst.setInt(4, v.getProdutos().getId());
            pst.setInt(5, v.getFuncionario().getId());
            pst.setInt(6, v.getCliente().getId());

            int valoresInseridos = pst.executeUpdate();

            if (valoresInseridos > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        v.setId(rs.getInt(1));

                    }

                }

            }

            logInfo("Inserido com sucesso no banco de dados para FoiPago: {0}, DataVenda: {1}, DataVencimento: {2}, Produtos_ID: {3}, "
                    + "Funcionarios_ID: {4}, Clientes_ID: {5} ", new Object[]{v.isFoiPago(), v.getDataVenda(), v.getDataVencimento(),
                        v.getProdutos().getId(), v.getFuncionario().getId(), v.getCliente().getId()});

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
    public void modificar(Vendas v) throws Exception {

        String sql = "UPDATE vendas SET foiPago=?, dataVenda=?, dataVencimento=?, produtos_id=?, funcionarios_id=?, cliente_id=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            logInfo("Executando SQL: {0}", sql);
            logFine("FoiPago: {0}, DataVenda: {1}, DataVencimento: {2}, Produtos_ID: {3}, "
                    + "Funcionarios_ID: {4}, Clientes_ID: {5}, ID: {6} ", new Object[]{v.isFoiPago(), v.getDataVenda(), v.getDataVencimento(),
                        v.getProdutos().getId(), v.getFuncionario().getId(), v.getCliente().getId(), v.getId()});

            pst.setBoolean(1, v.isFoiPago());
            pst.setDate(2, new java.sql.Date(v.getDataVenda().getTime()));
            pst.setString(3, v.getDataVencimento());
            pst.setInt(4, v.getProdutos().getId());
            pst.setInt(5, v.getFuncionario().getId());
            pst.setInt(6, v.getCliente().getId());

            pst.setInt(7, v.getId());

            pst.execute();

            logInfo("Modificado com sucesso no banco de dados para FoiPago: {0}, DataVenda: {1}, DataVencimento: {2}, Produtos_ID: {3}, "
                    + "Funcionarios_ID: {4}, Clientes_ID: {5}, ID: {6} ", new Object[]{v.isFoiPago(), v.getDataVenda(), v.getDataVencimento(),
                        v.getProdutos().getId(), v.getFuncionario().getId(), v.getCliente().getId(), v.getId()});

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
    public void deletar(Vendas v) throws Exception {

        String sql = "DELETE FROM vendas WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", v.getId());

            pst.setInt(1, v.getId());

            pst.execute();

            logInfo("Excluido com sucesso no banco de dados para ID: {0}", v.getId());

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
    public Vendas buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM vendas WHERE id=?";

        Vendas v = new Vendas();
        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    v.setId(rs.getInt("id"));
                    v.setFoiPago(rs.getBoolean("foiPago"));
                    v.setDataVenda(rs.getDate("dataVenda"));
                    v.setDataVencimento(rs.getString("dataVencimento"));
                    v.setProdutos(daoProdutos.buscarPorId(rs.getInt("produtos_id")));
                    v.setFuncionario(daoFuncionarios.buscarPorId(rs.getInt("funcionarios_id")));
                    v.setCliente(daoClientes.buscarPorId(rs.getInt("cliente_id")));

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

        return v;
    }

    @Override
    public List<Vendas> listar() throws Exception {

        String sql = "SELECT * FROM vendas";

        List<Vendas> listaV = new ArrayList<Vendas>();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Vendas v = new Vendas();

                v.setId(rs.getInt("id"));
                v.setFoiPago(rs.getBoolean("foiPago"));
                v.setDataVenda(rs.getDate("dataVenda"));
                v.setDataVencimento(rs.getString("dataVencimento"));
                v.setProdutos(daoProdutos.buscarPorId(rs.getInt("produtos_id")));
                v.setFuncionario(daoFuncionarios.buscarPorId(rs.getInt("funcionarios_id")));
                v.setCliente(daoClientes.buscarPorId(rs.getInt("cliente_id")));

                listaV.add(v);

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

        return listaV;
    }

}
