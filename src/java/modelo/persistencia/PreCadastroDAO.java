package modelo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.PreCadastro;

public class PreCadastroDAO extends DataBaseDAO implements InterfaceDAO<Integer, PreCadastro> {
    
    AreaCoberturaDAO daoAreacobertura = new AreaCoberturaDAO();
    
    public PreCadastroDAO() throws Exception {
        
    }
    
    @Override
    public void salvar(PreCadastro pc) throws Exception {
        
        String sql = "INSERT INTO precadastro ( nome, cep, telefone, cidade, email, areacobertura_id ) VALUES (?, ?, ?, ?, ?, ?)";
        
        conectar();
        
        PreparedStatement pst = conn.prepareStatement(sql);
        
        pst.setString(1, pc.getNome());
        pst.setString(2, pc.getCep());
        pst.setString(3, pc.getTelefone());
        pst.setString(4, pc.getCidade());
        pst.setString(5, pc.getEmail());
        pst.setInt(6, pc.getAreaCobertura().getId());
        
        pst.execute();
        
        desconectar();
    }
    
    @Override
    public void modificar(PreCadastro pc) throws Exception {
        
        String sql = "UPDATE precadastro SET nome=?, cep=?, telefone=?, cidade=?, email=?, areacobertura_id=? WHERE id=?";
        
        conectar();
        
        PreparedStatement pst = conn.prepareStatement(sql);
        
        pst.setString(1, pc.getNome());
        pst.setString(2, pc.getCep());
        pst.setString(3, pc.getTelefone());
        pst.setString(4, pc.getCidade());
        pst.setString(5, pc.getEmail());
        pst.setInt(6, pc.getAreaCobertura().getId());
        
        pst.setInt(7, pc.getId());
        
        pst.execute();
        
        desconectar();
        
    }
    
    @Override
    public void deletar(PreCadastro pc) throws Exception {
        
        String sql = "DELETE FROM precadastro WHERE id=?";
        
        conectar();
        
        PreparedStatement pst = conn.prepareStatement(sql);
        
        pst.setInt(1, pc.getId());
        
        pst.execute();
        
        desconectar();
        
    }
    
    @Override
    public PreCadastro buscarPorId(Integer id) throws Exception {
        
        String sql = "SELECT * FROM precadastro WHERE id=?";
        
        PreCadastro pc = new PreCadastro();
        
        conectar();
        
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            
            pc.setId(rs.getInt("id"));
            pc.setNome(rs.getString("nome"));
            pc.setCep(rs.getString("cep"));
            pc.setTelefone(rs.getString("telefone"));
            pc.setCidade(rs.getString("cidade"));
            pc.setEmail(rs.getString("email"));
            pc.setAreaCobertura(daoAreacobertura.buscarPorId(rs.getInt("areacobertura_id")));
            
        }
        
        desconectar();
        return pc;
        
    }
    
    @Override
    public List<PreCadastro> listar() throws Exception {
        
        String sql = "SELECT * FROM precadastro";
        
        List<PreCadastro> listaPc = new ArrayList<PreCadastro>();
        conectar();
        
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            
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
        
        desconectar();
        return listaPc;
        
    }
    
}
