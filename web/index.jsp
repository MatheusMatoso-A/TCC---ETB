<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/reset.css" type="text/css">
     
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
    <div class ="titulo"  >
            <h1 class="text-center mb-4">Nossos Planos de Internet</h1>
        </div>

        <!-- Início do conteúdo principal -->
        <main class="container my-5">
            <!-- Título da seção -->
            <!-- Criação de uma linha (row) para os planos -->
            <div class="row">
                <!-- Plano 1 -->
                <div class="box">
                    <div class="card">
                        <div class="card-body text-center">
                            <h5 class="card-title">Plano Básico</h5>
                            <p class="card-text">100 Mbps de velocidade</p>
                            <p class="card-text">Download ilimitado</p>
                            <p class="card-text">Suporte 24/7</p>
                            <p class="card-text"><strong>R$ 99,90/mês</strong></p>
                            <a href="plano1.jsp" class="btn btn-primary" >Assinar</a>
                        </div>
                    </div>
                </div>

                <!-- Plano 2 -->
                <div class="box">
                    <div class="card">
                        <div class="card-body text-center">
                            <h5 class="card-title">Plano Intermediário</h5>
                            <p class="card-text">300 Mbps de velocidade</p>
                            <p class="card-text">Download ilimitado</p>
                            <p class="card-text">Suporte 24/7</p>
                            <p class="card-text"><strong>R$ 149,90/mês</strong></p>
                            <a href="plano1.jsp" class="btn btn-primary">Assinar</a>
                        </div>
                    </div>
                </div>

                <!-- Plano 3 -->
                <div class="box">
                    <div class="card">
                        <div class="card-body text-center">
                            <h5 class="card-title">Plano Avançado</h5>
                            <p class="card-text">500 Mbps de velocidade</p>
                            <p class="card-text">Download ilimitado</p>
                            <p class="card-text">Suporte 24/7</p>
                            <p class="card-text"><strong>R$ 199,90/mês</strong></p>
                            <a href="plano1.jsp" class="btn btn-primary">Assinar</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Fim da linha dos planos -->
        </main>       
    </body>
</html>

  
            
        
      
 