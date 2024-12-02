
<script language="javascript" >
    function validaForm() {
        formulario = document.form_usuario;
        if (formulario.nome.value == "") {
            alert("O Campo nome deve ser preenchido!!");
            formulario.nome.focus();
            return false;
        }
        if (formulario.telefone.value == "") {
            alert("O Campo telefone deve ser preenchido!!");
            formulario.telefone.focus();
            return false;
        }
        if (formulario.email.value == "") {
            alert("O Campo email deve ser preenchido!!");
            formulario.email.focus();
            return false;
        }
        if (formulario.cep.value == "") {
            alert("O Campo CEP deve ser preenchido!!");
            formulario.cep.focus();
            return false;
        }
        if (formulario.cidade.value == "") {
            alert("O Campo Cidade deve ser preenchido!!");
            formulario.cidade.focus();
            return false;
        }
        if (formulario.planoId.value == "") {
            alert("Selecione um plano!!");
            formulario.planoId.focus();
            return false;
        }

        return true;
    }
</script>

<style> 

.form-select:focus {
    border-color: #ff0000; /* Cor de borda ao focar */
    box-shadow: 0 0 0 0.25rem rgba(255, 0, 0, 0.25); /* Sombra vermelha quando o select é focado */
}
.form-control:focus{
    border-color: #ff0000; /* Cor de borda ao focar */
    box-shadow: 0 0 0 0.25rem rgba(255, 0, 0, 0.25); /* Sombra vermelha quando o select é focado */
}
</style>

<div class="container">
    <form action="gerenciar_pre_cadastro.do" method="post" style="text-align: center" onsubmit="return validaForm();"> 
        <img class="img-responsive"src="imagens/logo-tai.png" alt="Banner" title="Banner" >
        <!-- Formulário de dados -->
        <h2 class="text-center mb-4">Preencha os dados para continuar</h2>
        <!-- Campo Nome Completo -->
        <div class="mb-3">
            <label for="nome" class="form-label">Nome Completo</label>
            <input type="text" class="form-control" id="nome" name="nome" required>
        </div>


        <!-- Campo Telefone -->
        <div class="mb-3">
            <label for="telefone" class="form-label">Celular (WhatsApp)</label>
            <input type="text"  class="form-control" id="telefone" name="telefone" maxlength="15" required>

            <script>
                document.getElementById('telefone').addEventListener('input', function (e) {
                    // Remove qualquer caractere que não seja número
                    this.value = this.value.replace(/\D/g, '');

                    // Formata o telefone
                    let value = this.value;

                    if (value.length > 10) {
                        value = value.replace(/(\d{2})(\d{5})(\d{4})$/, '($1) $2-$3'); // Formato (XX) XXXXX-XXXX
                    } else if (value.length > 6) {
                        value = value.replace(/(\d{2})(\d{5})(\d{0,4})$/, '($1) $2-$3'); // Formato (XX) XXXXX
                    } else if (value.length > 2) {
                        value = value.replace(/(\d{2})(\d+)/, '($1) $2'); // Formato (XX)
                    }

                    this.value = value;
                });
            </script>


        </div>

        <!-- Campo Email -->
        <div class="mb-3">
            <label for="email" class="form-label">E-mail</label>
            <input type="text" class="form-control" id="email" name="email" required>
        </div>

        <!-- Campo CEP e Número (lado a lado) -->
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="cep" class="form-label">CEP</label>
                <input type="text" maxlength="8" class="form-control" id="cep" name="cep" title="Digite um CEP válido no formato XXXXXXXX" required>
            </div>



            <!-- Campo Cidade -->
            <div class="col-md-6 mb-3">
                <label for="cidade" class="form-label">Cidade</label>
                <input type="text" class="form-control" id="cidade" name="cidade" required>
            </div>


        </div>


        <input type="hidden" id="planoId" name="planoId" value="<%= request.getParameter("id")%>"/>
        <!-- Botão Continuar -->
        <button type="submit" class="btn btn-red w-100 d-block">Continuar</button>
    </form>
</div>

