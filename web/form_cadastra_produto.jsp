<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">


        <link rel="stylesheet" href="css/obrigatorio_login.css" type="text/css">
        <link rel="stylesheet" href="css/escolha_login.css" type="text/css">


        <title>Taí Telecom - Cadastrar Produtos</title>
    </head>
    <body>

        <%@include file="menu_login.jsp" %>

        <div class="main-content">
            <h2 class="text-center mb-4">Cadastrar Produto</h2>

            <!-- Formulário de Cadastro de Produto -->
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4"> <!-- Usando 4 colunas para telas grandes e 6 colunas para telas médias -->
                    <form action="cadastrar_produto.do" method="post">
                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="nome" class="form-label">Nome do Produto</label>
                            <input type="text" class="form-control" id="nome" name="nome"  required>
                        </div>

                        <div class="mb-3">
                            <!-- Velocidade -->
                            <label for="velocidade" class="form-label">Velocidade</label>
                            <input type="text" class="form-control" id="velocidade" name="velocidade"  required>
                        </div>

                        <div class="mb-3">
                            <!-- Valor -->
                            <label for="valor" class="form-label">Valor</label>
                            <input type="text" class="form-control" id="valor" name="valor"  required>
                        </div>


                        <!-- Botão Enviar -->
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger">Cadastrar Produto</button>
                        </div>


                    </form>

                </div>

            </div>

        </div>
    </body>
</html>
