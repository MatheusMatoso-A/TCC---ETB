
package modelo.persistencia;

import java.util.List;


public interface InterfaceDAO<K, T> {
    
    public void salvar(T entidade) throws Exception;

    public void modificar(T entidade)throws Exception;

    public void deletar(T entidade)throws Exception;

    public T buscarPorId(K id)throws Exception;

    public List<T> listar() throws Exception;
    
}
