<div class="container">
    <form action="processarAssinatura.jsp" method="post" style="text-align: center"> 
        <img class="img-responsive"src="imagens/logo-tai.png" alt="Banner" title="Banner" >
        <!-- Formulário de dados -->
        <h2 class="text-center mb-4">Preencha seus dados</h2>
        <!-- Campo Nome Completo -->
        <div class="mb-3">
            <label for="nome" class="form-label">Nome Completo</label>
            <input type="text" class="form-control" id="nome" name="nome" required>
        </div>
        
        
                <!-- Campo Telefone -->
        <div class="mb-3">
            <label for="telefone" class="form-label">Celular (WhatsApp)</label>
            <input type="number"  class="form-control" id="telefone" name="telefone"  required>
        </div>

        
        
        <!-- Campo CEP e Número (lado a lado) -->
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="cep" class="form-label">CEP</label>
                <input type="text" pattern="\d{5}-?\d{3}" maxlength="9" class="form-control" id="cep" name="cep" title="Digite um CEP válido no formato XXXXX-XXX" required>
            </div>
            <div class="col-md-6 mb-3">
                <label for="numero" class="form-label">Número</label>
                <input type="number" class="form-control" id="numero" name="numero" required>
            </div>
        </div>


        <!-- Botão Continuar -->
        <a href="cadastro.jsp" class="botao"> <button type="submit" class="btn btn-red w-100 d-block">Continuar</button></a>
    </form>
</div>

