package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import modelo.entidades.AreaCobertura;
import modelo.entidades.PreCadastro;

public class PreCadastroDAO extends DataBaseDAO implements InterfaceLoggable, InterfaceDAO<Integer, PreCadastro> {

    AreaCoberturaDAO daoAreacobertura = new AreaCoberturaDAO();
    private static final Logger LOGGER = Logger.getLogger(PreCadastroDAO.class.getName());

    public PreCadastroDAO() throws Exception {

    }

    @Override
    public Logger getLogger() {

        return LOGGER;
    }

    @Override
    public void salvar(PreCadastro pc) throws Exception {

        // Buscar a área de cobertura pelo CEP
        AreaCobertura ac = daoAreacobertura.buscarPorCep(pc.getCep());

        // Verificar se a área de cobertura existe
        if (ac != null) {

            pc.setAreaCobertura(ac);    // Define a área de cobertura

        }

        String sql = "INSERT INTO precadastro ( nome, cep, telefone, cidade, email, areacobertura_id ) VALUES (?, ?, ?, ?, ?, ?)";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("Nome: {0}, Cep: {1}, Telefone: {2}, Cidade: {3}, Email: {4}, "
                    + "Areacobertura_ID: {5} ", new Object[]{pc.getNome(), pc.getCep(),
                        pc.getTelefone(), pc.getCidade(), pc.getEmail(), pc.getAreaCobertura().getId()});

            pst.setString(1, pc.getNome());
            pst.setString(2, pc.getCep());
            pst.setString(3, pc.getTelefone());
            pst.setString(4, pc.getCidade());
            pst.setString(5, pc.getEmail());

            // Definir o areacobertura_id como nulo se a área de cobertura não for encontrada
            if (pc.getAreaCobertura() != null) {

                pst.setInt(6, pc.getAreaCobertura().getId());
            } else {

                pst.setNull(6, java.sql.Types.INTEGER); // Define o areacobertura_id como nulo

            }

            pst.execute();

            logInfo("Inserido com sucesso no banco de dados para Nome: {0}, Cep: {1}, Telefone: {2}, Cidade: {3}, Email: {4}, "
                    + "Areacobertura_ID: {5} ", new Object[]{pc.getNome(), pc.getCep(),
                        pc.getTelefone(), pc.getCidade(), pc.getEmail(), pc.getAreaCobertura().getId()});

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
    public void modificar(PreCadastro pc) throws Exception {

        String sql = "UPDATE precadastro SET nome=?, cep=?, telefone=?, cidade=?, email=?, areacobertura_id=? WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0} ", sql);
            logFine("Nome: {0}, Cep: {1}, Telefone: {2}, Cidade: {3}, Email: {4}, "
                    + "Areacobertura_ID: {5}, ID: {6} ", new Object[]{pc.getNome(), pc.getCep(),
                        pc.getTelefone(), pc.getCidade(), pc.getEmail(), pc.getAreaCobertura().getId(), pc.getId()});

            pst.setString(1, pc.getNome());
            pst.setString(2, pc.getCep());
            pst.setString(3, pc.getTelefone());
            pst.setString(4, pc.getCidade());
            pst.setString(5, pc.getEmail());
            pst.setInt(6, pc.getAreaCobertura().getId());

            pst.setInt(7, pc.getId());

            pst.execute();
            logInfo("Modificado com sucesso no banco de dados para Nome: {0}, Cep: {1}, Telefone: {2}, Cidade: {3}, Email: {4}, "
                    + "Areacobertura_ID: {5}, ID: {6} ", new Object[]{pc.getNome(), pc.getCep(),
                        pc.getTelefone(), pc.getCidade(), pc.getEmail(), pc.getAreaCobertura().getId(), pc.getId()});

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
    public void deletar(PreCadastro pc) throws Exception {

        String sql = "DELETE FROM precadastro WHERE id=?";

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", pc.getId());

            pst.setInt(1, pc.getId());

            pst.execute();

            logInfo("Excluido com sucesso no banco de dados para ID: {0}", pc.getId());

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
    public PreCadastro buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM precadastro WHERE id=?";

        PreCadastro pc = new PreCadastro();

        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            logInfo("Executando SQL: {0}", sql);
            logFine("ID: {0}", id);

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    pc.setId(rs.getInt("id"));
                    pc.setNome(rs.getString("nome"));
                    pc.setCep(rs.getString("cep"));
                    pc.setTelefone(rs.getString("telefone"));
                    pc.setCidade(rs.getString("cidade"));
                    pc.setEmail(rs.getString("email"));
                    pc.setAreaCobertura(daoAreacobertura.buscarPorId(rs.getInt("areacobertura_id")));

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

        desconectar();
        return pc;

    }

    @Override
    public List<PreCadastro> listar() throws Exception {

        String sql = "SELECT * FROM precadastro";

        List<PreCadastro> listaPc = new ArrayList<PreCadastro>();
        conectar();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

             logInfo("Executando SQL: ", sql);
            
            while (rs.next()) {

                PreCadastro pc = new PreCadastro();

                pc.setId(rs.getInt("id"));
                pc.setNome(rs.getString("nome"));
                pc.setCep(rs.getString("cep"));
                pc.setTelefone(rs.getString("telefone"));
                pc.setCidade(rs.getString("cidade"));
                pc.setEmail(rs.getString("email"));
                pc.setAreaCobertura(daoAreacobertura.buscarPorId(rs.getInt("areacobertura_id")));

                listaPc.add(pc);
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

        return listaPc;

    }

}
