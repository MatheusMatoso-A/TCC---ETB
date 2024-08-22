<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="css/reset.css" type="text/css">

        <title>Taí Telecom - Fibra Óptica</title>
    </head>
    <body class="body">
        <!-- Início do cabeçalho -->
        <header class="w-100">
            <div class="container">
                <!-- Inclusão do menu de navegação -->
                <%@include file="menu.jsp" %>
            </div>
        </header>
        <!-- Fim do cabeçalho -->

        <!-- Início do Conteúdo Principal -->
        <main>
            <!-- Imagem acima do formulário -->
            <div>
                <img src="imagens/logo-tai.png" alt="Banner" title="Banner">



                <form action="processarAssinatura.jsp" method="post">
                    <!-- Formulário de dados -->
                    <h2 class="text-center mb-4">Preencha seus dados</h2>
                    <!-- Campo Nome Completo -->
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome Completo</label>
                        <input type="text" class="form-control" id="nome" name="nome" required>
                    </div>
                    <!-- Campo CEP e Número (lado a lado) -->
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" class="form-control" id="cep" name="cep" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="numero" class="form-label">Número</label>
                            <input type="text" class="form-control" id="numero" name="numero" required>
                        </div>
                    </div>
                    <!-- Campo Telefone -->
                    <div class="mb-3">
                        <label for="telefone" class="form-label">Telefone</label>
                        <input type="tel" class="form-control" id="telefone" name="telefone" required>
                    </div>


                    <!-- Botão Continuar -->
                    <button type="submit" class="btn btn-red w-100 d-block">Continuar</button>
            </div>
        </form>
    </main>

    <footer class="text-white p-4 mt-4">
        <div class="container text-center">
            <p>&copy; 2024 Taí Telecom. Todos os direitos reservados.</p>
        </div>
    </footer>
</body>
</html>
