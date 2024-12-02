package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.Agenda;

public class AgendaDAO extends DataBaseDAO implements InterfaceDAO<Integer, Agenda>, InterfaceLoggable {

    private static final Logger LOGGER = Logger.getLogger(AgendaDAO.class.getName());
    private VendasDAO daoVendas = new VendasDAO();

    public AgendaDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;
    }

    @Override
    public void salvar(Agenda ag) throws Exception {

        String sql = "INSERT INTO agenda ( dataComparecimento, status,  vendas_id) VALUES (?, ?, ?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("DataComparecimento: {0}, Status{1}, Vendas_ID: {2} ",
                    new Object[]{ag.getDataComparecimento(), ag.getStatus(), ag.getVendas().getId()});

            // Converte LocalDateTime para Timestamp e insere no PreparedStatement
            if (ag.getDataComparecimento() != null) {

                pst.setTimestamp(1, Timestamp.valueOf(ag.getDataComparecimento()));

            } else {
                pst.setNull(1, java.sql.Types.TIMESTAMP); // Caso seja null
            }

            pst.setString(2, ag.getStatus());
            pst.setInt(3, ag.getVendas().getId());

            pst.execute();

            logInfo("Inserido com sucesso no banco de dados para DataComparecimento: {0}, Status{1}, Vendas_ID: {2} ",
                    new Object[]{ag.getDataComparecimento(), ag.getStatus(), ag.getVendas().getId()});

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
    public void modificar(Agenda ag) throws Exception {

        String sql = "UPDATE agenda SET dataComparecimento=?, status=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("DataComparecimento: {0}, Status{1}, ID: {2} ",
                    new Object[]{ag.getDataComparecimento(), ag.getStatus(), ag.getId()});

            // Converte LocalDateTime para Timestamp e insere no PreparedStatement
            if (ag.getDataComparecimento() != null) {

                pst.setTimestamp(1, Timestamp.valueOf(ag.getDataComparecimento()));

            } else {
                pst.setNull(1, java.sql.Types.TIMESTAMP); // Caso seja null
            }

            pst.setString(2, ag.getStatus());

            pst.setInt(3, ag.getId());

            pst.execute();

            logInfo("Modificado com sucesso no banco de dados para DataComparecimento: {0}, Status{1}, ID: {2} ",
                    new Object[]{ag.getDataComparecimento(), ag.getStatus(), ag.getId()});

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
    public void deletar(Agenda ag) throws Exception {

        String sql = "DELETE FROM agenda WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", ag.getId());

            pst.setInt(1, ag.getId());
            pst.execute();

            logInfo("Excluido com sucesso no banco de dados para ID: {0}", ag.getId());

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
    public Agenda buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM agenda WHERE id=?";

        Agenda ag = new Agenda();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    ag.setId(rs.getInt("id"));

                    // Converte o DATETIME do banco para LocalDateTime
                    Timestamp timestamp = rs.getTimestamp("dataComparecimento");
                    if (timestamp != null) {
                        ag.setDataComparecimento(timestamp.toLocalDateTime());
                    } else {
                        ag.setDataComparecimento(null); // Ou defina um valor padrão se necessário
                    }

                    ag.setStatus(rs.getString("status"));
                    ag.setVendas(daoVendas.buscarPorId(rs.getInt("vendas_id")));

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

        return ag;
    }

    @Override
    public List<Agenda> listar() throws Exception {

        String sql = "SELECT * FROM agenda";

        List<Agenda> listaAg = new ArrayList<Agenda>();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Agenda ag = new Agenda();

                ag.setId(rs.getInt("id"));

                // Converte o DATETIME do banco para LocalDateTime
                Timestamp timestamp = rs.getTimestamp("dataComparecimento");
                if (timestamp != null) {
                    ag.setDataComparecimento(timestamp.toLocalDateTime());
                } else {
                    ag.setDataComparecimento(null); // Ou defina um valor padrão se necessário
                }

                ag.setStatus(rs.getString("status"));
                ag.setVendas(daoVendas.buscarPorId(rs.getInt("vendas_id")));

                listaAg.add(ag);
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

        return listaAg;
    }

    public List<Agenda> buscarPorStatus() throws Exception {

        String sql = "SELECT * FROM agenda WHERE status='Disponível'";

        List<Agenda> listaAgs = new ArrayList<Agenda>();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: ", sql);

            while (rs.next()) {

                Agenda ag = new Agenda();

                ag.setId(rs.getInt("id"));

                // Converte o DATETIME do banco para LocalDateTime
                Timestamp timestamp = rs.getTimestamp("dataComparecimento");
                if (timestamp != null) {
                    ag.setDataComparecimento(timestamp.toLocalDateTime());
                } else {
                    ag.setDataComparecimento(null); // Ou defina um valor padrão se necessário
                }

                ag.setStatus(rs.getString("status"));
                ag.setVendas(daoVendas.buscarPorId(rs.getInt("vendas_id")));

                listaAgs.add(ag);
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

        return listaAgs;
    }

    public void modificarStatus (Agenda ag) throws Exception {

        String sql = "UPDATE agenda SET status=?, vendas_id=? WHERE dataComparecimento=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("Status{0}, DataComparecimento: {1} ",
                    new Object[]{ ag.getStatus(), ag.getDataComparecimento()});

            
             pst.setString(1, ag.getStatus());
             
             pst.setInt(2, ag.getVendas().getId());
             
            // Converte LocalDateTime para Timestamp e insere no PreparedStatement
            if (ag.getDataComparecimento() != null) {

                pst.setTimestamp(3, Timestamp.valueOf(ag.getDataComparecimento()));

            } else {
                pst.setNull(3, java.sql.Types.TIMESTAMP); // Caso seja null
            }


            pst.executeUpdate();

            logInfo("Modificado com sucesso no banco de dados para DataComparecimento: {0}, Status{1}, ID: {2} ",
                    new Object[]{ag.getDataComparecimento(), ag.getStatus(), ag.getId()});

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
}
