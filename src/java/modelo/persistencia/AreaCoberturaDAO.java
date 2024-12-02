package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.AreaCobertura;

public class AreaCoberturaDAO extends DataBaseDAO implements InterfaceDAO<Integer, AreaCobertura>, InterfaceLoggable {

    private static final Logger LOGGER = Logger.getLogger(AreaCoberturaDAO.class.getName());

    public AreaCoberturaDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;

    }

    @Override
    public void salvar(AreaCobertura ac) throws Exception {

        String sql = "INSERT INTO areaCobertura ( cep, cidade, estado) VALUES (?, ?, ?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0} ", sql);
            logFine("CEP: {0}, Cidade: {1}, Estado: {2}", new Object[]{ac.getCep(), ac.getCidade(), ac.getEstado()});

            pst.setString(1, ac.getCep());
            pst.setString(2, ac.getCidade());
            pst.setString(3, ac.getEstado());

            pst.execute();

            logInfo("Inserido com sucesso no banco de dados para CEP: {0}, Cidade: {1}, Estado: {2}", new Object[]{ac.getCep(), ac.getCidade(), ac.getEstado()});
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
    public void modificar(AreaCobertura ac) throws Exception {
        String sql = "UPDATE areacobertura SET cep=?, cidade=?, estado=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0} ", sql);
            logFine("CEP: {0}, Cidade: {1}, Estado: {2}, ID: {3}", new Object[]{ac.getCep(), ac.getCidade(), ac.getEstado(), ac.getId()});

            pst.setString(1, ac.getCep());
            pst.setString(2, ac.getCidade());
            pst.setString(3, ac.getEstado());

            pst.setInt(4, ac.getId());

            pst.execute();

            logInfo("Modificado com sucesso no banco de dados para CEP: {0}, Cidade: {1}, Estado: {2}, ID: {3}  ", new Object[]{ac.getCep(), ac.getCidade(), ac.getEstado(), ac.getId()});

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
    public void deletar(AreaCobertura ac) throws Exception {

        String sql = "DELETE FROM areacobertura WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0} ", sql);
            logFine("ID: {0}", ac.getId());

            pst.setInt(1, ac.getId());

            pst.execute();

            logInfo("Excluido com sucesso no banco de dados para ID: {0}", ac.getId());

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
    public AreaCobertura buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM areacobertura WHERE id=?";

        AreaCobertura ac = new AreaCobertura();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0} ", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    ac.setId(rs.getInt("id"));
                    ac.setCep(rs.getString("cep"));
                    ac.setCidade(rs.getString("cidade"));
                    ac.setEstado(rs.getString("estado"));

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

        return ac;

    }

    @Override
    public List<AreaCobertura> listar() throws Exception {

        String sql = "SELECT * FROM areacobertura";

        List<AreaCobertura> listaAc = new ArrayList<AreaCobertura>();
        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            logInfo("Executando SQL: {0} ", sql);

            while (rs.next()) {

                AreaCobertura ac = new AreaCobertura();

                ac.setId(rs.getInt("id"));
                ac.setCep(rs.getString("cep"));
                ac.setCidade(rs.getString("cidade"));
                ac.setEstado(rs.getString("estado"));

                listaAc.add(ac);
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

        return listaAc;

    }

    public AreaCobertura buscarPorCep(String cep) throws Exception {
        // Consulta otimizada: buscar apenas o campo id
        String sql = "SELECT id FROM areacobertura WHERE cep = ?";

        // Criamos o objeto AreaCobertura, que será retornado se o CEP for encontrado
        AreaCobertura ac = null;

        // Conectar ao banco de dados
        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

              logInfo("Executando SQL: {0} ", sql);
            
            // Preparar o statement para a consulta
            pst.setString(1, cep); // Substitui o ? pelo CEP fornecido

            // Executa a consulta
            try (ResultSet rs = pst.executeQuery()) {

                // Verifica se a consulta retornou algum registro
                if (rs.next()) {

                    // Apenas cria o objeto com o id
                    ac = new AreaCobertura();
                    ac.setId(rs.getInt("id"));

                    // Não é necessário preencher os outros campos, como cidade e estado
                }

            }
            
            logInfo("Pesquisa por CEP bem-sucedida no banco de dados para o CEP: {0}", cep);
            
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

        // Retorna o objeto AreaCobertura contendo apenas o id, ou null se o CEP não foi encontrado
        return ac;

    }

}
