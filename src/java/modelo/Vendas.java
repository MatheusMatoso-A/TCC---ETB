package modelo;

import java.util.Date;
import lombok.*;

@Getter
@Setter

public class Vendas {
    
    private int id;
    private String tipoPag;
    private boolean foiPago;
    private Date dataVenda;
    private Date dataVencimento;
    private Produtos produtos;
    private Funcionarios funcionario;
    private Clientes cliente;
    
    

}
