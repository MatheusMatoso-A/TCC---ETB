<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">
        <link rel="stylesheet" href="css/inicio_login.css" type="text/css">
        <link rel="stylesheet" href="css/obrigatorio_login.css" type="text/css">


        <title>Tai Telecom - Acesso Login</title>

    </head>

    <body>

        <%@include file="menu_login.jsp" %>
        
        <div class="main-content text-center">
            <h1> Seja bem-vindo ao nosso sistema! Agradecemos por fazer parte da nossa empresa <img src="imagens/feliz.gif" width="50px" height="50px"> </h1> <br>
            <b>  <h2 style="color: red;">Tai Telecom informa:</h2>
                <p style="color: red;">Você está agora dentro do sistema. Explore as funcionalidades disponíveis para atender às suas necessidades!
                    Lembre-se de que estamos aqui para proporcionar a melhor experiência para você! </p> </b> <br>

            <p>
                Caso precise de atendimento para maior auxílio, entre em contato com o nosso suporte:

            </p>
            
            <div class="menu-body"> 

                <a href="https://wa.me/5561991844270" target="_blank">Clique aqui para iniciar uma conversa</a>    

            </div>

        </div>

    </body>

</html>