<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/reset.css" type="text/css">
        <link rel="stylesheet" href="css/scrollbar.css" type="text/css">
        <link rel="stylesheet" href="css/informativo.css" type="text/css">
        <title>Taí Telecom - Informativo Importante</title>
    </head>
    <body>
        
        <header>
            <div class="container">
                <!-- Inclus?o do menu de navega??o -->
                <%@include file="menu.jsp" %>
            </div>
        </header>

        
            
            <div class=" container infomacoes">
                <div class="topo">
                    <h1>Área de Cobertura</h1>
                </div>
                <div class="message">
                    <p>Atualmente, nosso plano ainda não está disponível na sua área.</p>
                    <p>Estamos analisando a possibilidade de expandir a cobertura para região de sua residência.</p>
                </div>
                <div class="gif-container">
                    <img src="imagens/aviso.gif" alt="Estudo em andamento">
                </div>
            </div>



            <%@include file="whatsapp.jsp"%>




    <!-- Início do Rodapé -->
    <footer class="arrumando-footer">
        <div class="container text-center">
            <p>&copy; 2024 Taí Telecom. Todos os direitos reservados.</p>
        </div>
    </footer>
    <!-- Fim do Rodapé -->

</body>
</html>
