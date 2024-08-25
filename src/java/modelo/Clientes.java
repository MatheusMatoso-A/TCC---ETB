package modelo;

import java.util.Date;
import lombok.*;

@Getter
@Setter
public class Clientes {

    private int id;
    private String nome;
    private String telefone;
    private String cep;
    private String cpf;
    private String email;
    private String rg;
    private Date dataNascimento;
    private Agenda agenda;
    private PreCadastro preCadastro;

}
