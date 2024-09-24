<div class="container">
    <form action="processarAssinatura.jsp" method="post" style="text-align: center"> 
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
            <input type="tel"  class="form-control" id="telefone" name="telefone" maxlength="14" required>

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



        <!-- Campo CEP e Número (lado a lado) -->
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="cep" class="form-label">CEP</label>
                <input type="text" maxlength="9" class="form-control" id="cep" name="cep" title="Digite um CEP válido no formato XXXXX-XXX" required>


                <script>
                    document.getElementById('cep').addEventListener('input', function (e) {
                        // Remove qualquer caractere que não seja número
                        this.value = this.value.replace(/\D/g, '');

                        // Formata o CEP
                        let value = this.value;

                        if (value.length > 5) {
                            value = value.replace(/(\d{5})(\d{0,3})/, '$1-$2'); // Formato XXXXX-XXX
                        }

                        this.value = value;
                    });
                </script>

            </div>



            <div class="col-md-6 mb-3">
                <label for="numero" class="form-label">Número</label>
                <input type="text" maxlength="2" class="form-control" id="numero" name="numero" required>

                <script>
                    document.getElementById('numero').addEventListener('input', function (e) {
                        // Remove qualquer caractere que não seja número
                        this.value = this.value.replace(/\D/g, '');

                    });
                </script>



            </div>
        </div>


        <!-- Botão Continuar -->
        <a href="cadastro.jsp" class="botao"> <button type="submit" class="btn btn-red w-100 d-block">Continuar</button></a>
    </form>
</div>

