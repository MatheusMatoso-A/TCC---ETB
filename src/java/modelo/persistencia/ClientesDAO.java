package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Clientes;

public class ClientesDAO extends DataBaseDAO implements InterfaceLoggable, InterfaceDAO<Integer, Clientes> {

    private static final Logger LOGGER = Logger.getLogger(ClientesDAO.class.getName());

    private UsuarioDAO daoUsuario = new UsuarioDAO();
    private PreCadastroDAO daoPreCadastro = new PreCadastroDAO();

    public ClientesDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    @Override
    public void salvar(Clientes c) throws Exception {

        String sql = "INSERT INTO cliente ( tipoEndereco, endereco, numero, complemento, "
                + "pontoReferencia, preCadasto_id, usuarios_id ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("TipoEndereco: {0}, Endereco: {1}, Numero: {2}, Complemento: {3}, PontoReferencia: {4}, PreCadastro_ID: {5}, Usuarios_ID: {6} ",
                    new Object[]{c.getTipoEndereco(), c.getEndereco(), c.getNumero(), c.getComplemento(), c.getPontoReferencia(),
                        c.getPreCadastro().getId(), c.getUsuario().getId()});

            pst.setString(1, c.getTipoEndereco());
            pst.setString(2, c.getEndereco());
            pst.setInt(3, c.getNumero());
            pst.setString(4, c.getComplemento());
            pst.setString(5, c.getPontoReferencia());
            pst.setInt(6, c.getPreCadastro().getId());
            pst.setInt(7, c.getUsuario().getId());

            pst.execute();

            logInfo("Inserido com sucesso no banco de dados para TipoEndereco: {0}, Endereco: {1}, Numero: {2}, Complemento: {3}, PontoReferencia: {4}, "
                    + "PreCadastro_ID: {5}, Usuarios_ID: {6} ", new Object[]{c.getTipoEndereco(), c.getEndereco(), c.getNumero(), c.getComplemento(), c.getPontoReferencia(),
                        c.getPreCadastro().getId(), c.getUsuario().getId()});

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
    public void modificar(Clientes c) throws Exception {

        String sql = "UPDATE cliente SET tipoEndereco=?, endereco=?, numero=?, complemento=?, pontoReferencia=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("TipoEndereco: {0}, Endereco: {1}, Numero: {2}, Complemento: {3}, PontoReferencia: {4}, ID: {5} ",
                    new Object[]{c.getTipoEndereco(), c.getEndereco(), c.getNumero(), c.getComplemento(), c.getPontoReferencia(), c.getId()});

            pst.setString(1, c.getTipoEndereco());
            pst.setString(2, c.getEndereco());
            pst.setInt(3, c.getNumero());
            pst.setString(4, c.getComplemento());
            pst.setString(5, c.getPontoReferencia());

            pst.setInt(6, c.getId());

            logInfo("Modificado com sucesso no banco de dados para TipoEndereco: {0}, Endereco: {1}, Numero: {2}, Complemento: {3}, "
                    + "PontoReferencia: {4}, ID: {5}  ", new Object[]{c.getTipoEndereco(), c.getEndereco(), c.getNumero(),
                        c.getComplemento(), c.getPontoReferencia(), c.getId()});

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
    public void deletar(Clientes c) throws Exception {

        String sql = "DELETE FROM cliente WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", c.getId());

            pst.setInt(1, c.getId());

            pst.execute();

            logInfo("Excluido com sucesso no banco de dados para ID: {0}", c.getId());

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
    public Clientes buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM cliente WHERE id=?";

        Clientes c = new Clientes();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    c.setId(rs.getInt("id"));
                    c.setTipoEndereco(rs.getString("tipoEndereco"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setNumero(rs.getInt("numero"));
                    c.setComplemento(rs.getString("complemento"));
                    c.setPontoReferencia(rs.getString("pontoReferencia"));
                    c.setPreCadastro(daoPreCadastro.buscarPorId(rs.getInt("preCadastro_id")));
                    c.setUsuario(daoUsuario.buscarPorId(rs.getInt("usuarios_id")));

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

        return c;
    }

    @Override
    public List<Clientes> listar() throws Exception {

        String sql = "SELECT * FROM cliente";

        List<Clientes> listaC = new ArrayList<Clientes>();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Clientes c = new Clientes();

                c.setId(rs.getInt("id"));
                c.setTipoEndereco(rs.getString("tipoEndereco"));
                c.setEndereco(rs.getString("endereco"));
                c.setNumero(rs.getInt("numero"));
                c.setComplemento(rs.getString("complemento"));
                c.setPontoReferencia(rs.getString("pontoReferencia"));
                c.setPreCadastro(daoPreCadastro.buscarPorId(rs.getInt("preCadastro_id")));
                c.setUsuario(daoUsuario.buscarPorId(rs.getInt("usuarios_id")));

                listaC.add(c);

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

        return listaC;
    }

}
