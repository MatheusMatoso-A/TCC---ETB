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


        <title>Taí Telecom - Produtos</title>
    </head>
    <body>

        <%@include file="menu_login.jsp" %>

        <div class="container text-center mt-5">
            <div class="row g-2"> <!-- Define o `gutter` como 2 (espaço reduzido entre colunas) -->
                <div class="col-md-5 offset-md-2"> <!-- Aumenta o alinhamento à esquerda -->
                    <a href="form_cadastra_produto.jsp" class="btn btn-transparent">
                        <img src="imagens/cadastrar_produto.png" alt="Cadastrar Produto"> <!-- Imagem para Cadastrar -->
                        Cadastrar Produto
                    </a>
                </div>
                <div class="col-md-5">
                    <a href="listar_produtos.jsp" class="btn btn-transparent">
                        <img src="imagens/gerenciar_produtos.png" alt="Visualizar Funcionarios"> <!-- Imagem para Visualizar -->
                        Gerenciar Produtos
                    </a>
                </div>
            </div>
        </div>

    </body>
</html>
