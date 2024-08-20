<%-- %
    Document   : login
    Created on : 20/08/2024, 11:00:19
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
                width: 30%;
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
                <h2 class="text-center mb-4">Login</h2>
                <form action="processarLogin.jsp" method="post">
                    <!-- Campo Login -->
                    <div class="mb-3">
                        <label for="login" class="form-label">Login:</label>
                        <input type="text" class="form-control" id="login" name="login" required>
                    </div>
                    <!-- Campo Senha -->
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha:</label>
                        <input type="password" class="form-control" id="senha" name="senha" required>
                    </div>
                    <!-- Link Esqueci a Senha -->
                    <div class="mb-3 text-end">
                        <a href="#" class="link-primary">Esqueci minha senha</a>
                    </div>
                    <!-- Botão Entrar -->
                    <button type="submit" class="btn btn-primary w-100">Entrar</button>
                </form>
            </div> 

        </main>

    </body>
</html>
