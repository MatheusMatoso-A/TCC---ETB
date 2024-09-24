package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.AreaCobertura;

public class AreaCoberturaDAO extends DataBaseDAO implements InterfaceDAO<Integer, AreaCobertura> {

    public AreaCoberturaDAO() throws Exception {

    }

    @Override
    public void salvar(AreaCobertura ac) throws Exception {

        String sql = "INSERT INTO areaCobertura ( cep, cidade, estado) VALUES (?, ?, ?)";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, ac.getCep());
        pst.setString(2, ac.getCidade());
        pst.setString(3, ac.getEstado());

        pst.execute();

        desconectar();

    }

    @Override
    public void modificar(AreaCobertura ac) throws Exception {
        String sql = "UPDATE areacobertura SET cep=?, cidade=?, estado=? WHERE id=?";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, ac.getCep());
        pst.setString(2, ac.getCidade());
        pst.setString(3, ac.getEstado());

        pst.setInt(4, ac.getId());

        pst.execute();

        desconectar();

    }

    @Override
    public void deletar(AreaCobertura ac) throws Exception {

        String sql = "DELETE FROM areacobertura WHERE id=?";

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, ac.getId());

        pst.execute();

        desconectar();

    }

    @Override
    public AreaCobertura buscarPorId(Integer id) throws Exception {

        String sql = "SELECT * FROM areacobertura WHERE id=?";

        AreaCobertura ac = new AreaCobertura();

        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {

            ac.setId(rs.getInt("id"));
            ac.setCep(rs.getString("cep"));
            ac.setCidade(rs.getString("cidade"));
            ac.setEstado(rs.getString("estado"));

        }

        desconectar();
        return ac;

    }

    @Override
    public List<AreaCobertura> listar() throws Exception {

        String sql = "SELECT * FROM areacobertura";

        List<AreaCobertura> listaAc = new ArrayList<AreaCobertura>();
        conectar();

        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            AreaCobertura ac = new AreaCobertura();

            ac.setId(rs.getInt("id"));
            ac.setCep(rs.getString("cep"));
            ac.setCidade(rs.getString("cidade"));
            ac.setEstado(rs.getString("estado"));

            listaAc.add(ac);
        }
        desconectar();
        return listaAc;

    }

    public AreaCobertura buscarPorCep(String cep) throws Exception {
        // Consulta otimizada: buscar apenas o campo id
        String sql = "SELECT id FROM areacobertura WHERE cep = ?";

        // Criamos o objeto AreaCobertura, que será retornado se o CEP for encontrado
        AreaCobertura ac = null;

        // Conectar ao banco de dados
        conectar();

        // Preparar o statement para a consulta
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, cep); // Substitui o ? pelo CEP fornecido

        
        // Executa a consulta
        ResultSet rs = pst.executeQuery();
        
         // Verifica se a consulta retornou algum registro
        if (rs.next()) {

             // Apenas cria o objeto com o id
            ac = new AreaCobertura();
            ac.setId(rs.getInt("id"));

            // Não é necessário preencher os outros campos, como cidade e estado
        }

        // Fechar a conexão
        desconectar();

        // Retorna o objeto AreaCobertura contendo apenas o id, ou null se o CEP não foi encontrado
        return ac;

    }

}
