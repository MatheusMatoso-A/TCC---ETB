<%-- 
    Document   : plano1
    Created on : 20/08/2024, 10:33:35
    Author     : lilia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/reset.css" type="text/css">
        <style>
            main{
                margin: 0 auto;
                width: 50%;
                display: flex;

                background-color: #ededed;


            }  



        </style>
        <title>Taí Telecom - Fibra Óptica</title>
    </head>
    <body>
        <!-- Início do cabeçalho -->
        <header>
            <div class="container">
                <!-- Inclusão do banner -->
                <%@include file="banner.jsp" %>
                <!-- Inclusão do menu de navegação -->
                <%@include file="menu.jsp" %>
            </div>
        </header>
        <!-- Fim do cabeçalho -->
        <main>
            <div class="container my-5">
                <h2 class="text-center mb-4">Preencha seus dados</h2>
                <form action="processarAssinatura.jsp" method="post">
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
                    <button type="submit" class="btn btn-primary">Continuar</button>
                </form>
            </div>
        </main>

        <!-- Scripts do Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
