package modelo.entidades;

import java.util.Date;
import lombok.*;

@Getter
@Setter

public class Vendas {
    
    private int id;
    private boolean foiPago;
    private Date dataVenda;
    private String dataVencimento;
    private Produtos produtos;
    private Funcionarios funcionario;
    private Clientes cliente;

    
    public Vendas(){
        
    }
    
    public Vendas(int id, boolean foiPago, Date dataVenda, String dataVencimento, Produtos produtos, Funcionarios funcionario, Clientes cliente) {
        this.id = id;
        this.foiPago = foiPago;
        this.dataVenda = dataVenda;
        this.dataVencimento = dataVencimento;
        this.produtos = produtos;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }
     @Override
    public String toString() {
        return String.valueOf(this.id); // Substitua "id" pela chave primária ou outro atributo relevante
    }
}
