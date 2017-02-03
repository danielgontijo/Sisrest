package util;

import java.util.List;

/**
 *
 * @author tatuapu
 */
public interface DAO {
    
    void atualizar(Object ob) throws Exception;
    
    void excluir(Object ob) throws Exception;
    
    List listaTodos() throws Exception;
    
    List procura(Object ob) throws Exception;
    
    void salvar(Object ob) throws Exception;
    
}
