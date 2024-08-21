<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/reset.css" type="text/css">
        <title>Ta� Telecom - Fibra �ptica</title>
    </head>
    <body>
        <!-- In�cio do cabe�alho -->
        <header>
            <div class="container">
                <!-- Inclus�o do banner -->
                <%@include file="banner.jsp" %>
                <!-- Inclus�o do menu de navega��o -->
                <%@include file="menu.jsp" %>
            </div>
        </header>
        <!-- Fim do cabe�alho -->

        <!-- Imagem de destaque -->
        <div class="container-fluid p-0">
            <img src="imagens/imagem-index.png" class="img-fluid w-100" alt="Imagem de destaque"width="100" height="100">
        </div>

        <!-- Se��o de Planos de Internet -->
        <section class="my-5">
            <div class="container">
                <h2 class="text-center mb-4">Nossos Planos de Internet</h2>
                <div class="row">
                    <!-- Plano 1 -->
                    <div class="col-md-4 mb-4">
                        <div class="card h-100">
                            <div class="card-body text-center">
                                <h5 class="card-title">Plano B�sico</h5>
                                <p class="card-text">100 Mbps de velocidade</p>
                                <p class="card-text">Download ilimitado</p>
                                <p class="card-text">Suporte 24/7</p>
                                <p class="card-text"><strong>R$ 99,90/m�s</strong></p>
                                <a href="plano1.jsp" class="btn btn-red vantagem-img">Assinar</a>
                            </div>
                        </div>
                    </div>

                    <!-- Plano 2 -->
                    <div class="col-md-4 mb-4">
                        <div class="card h-100">
                            <div class="card-body text-center">
                                <h5 class="card-title">Plano Intermedi�rio</h5>
                                <p class="card-text">300 Mbps de velocidade</p>
                                <p class="card-text">Download ilimitado</p>
                                <p class="card-text">Suporte 24/7</p>
                                <p class="card-text"><strong>R$ 149,90/m�s</strong></p>
                                <a href="plano2.jsp" class="btn btn-red vantagem-img">Assinar</a>
                            </div>
                        </div>
                    </div>

                    <!-- Plano 3 -->
                    <div class="col-md-4 mb-4 ">
                        <div class="card h-100">
                            <div class="card-body text-center">
                                <h5 class="card-title">Plano Avan�ado</h5>
                                <p class="card-text">500 Mbps de velocidade</p>
                                <p class="card-text">Download ilimitado</p>
                                <p class="card-text">Suporte 24/7</p>
                                <p class="card-text"><strong>R$ 199,90/m�s</strong></p>
                                <a href="plano3.jsp" class="btn btn-red vantagem-img">Assinar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <!-- Se��o de Vantagens -->
        <section class="bg-light py-5">
            <div class="container">
                <h2 class="text-center mb-4">Vantagens de Escolher a Ta� Telecom</h2>
                <div class="row">
                    <div class="col-md-4 text-center">
                        <img src="imagens/velocidade.png" alt="Velocidade" class="mb-3 vantagem-img" width="50" height="50">
                        <h5>Velocidade e Estabilidade</h5>
                        <p>Internet r�pida e est�vel com fibra �ptica de �ltima gera��o.</p>
                    </div>
                    <div class="col-md-4 text-center">
                        <img src="imagens/suporte.png" alt="Suporte" class="mb-3 vantagem-img" width="50" height="50">
                        <h5>Suporte 24/7</h5>
                        <p>Atendimento especializado dispon�vel a qualquer hora.</p>
                    </div>
                    <div class="col-md-4 text-center">
                        <img src="imagens/beneficio.png" alt="Custo-benef�cio" class="mb-3 vantagem-img" width="50" height="50">
                        <h5>Melhor Custo-Benef�cio</h5>
                        <p>Planos acess�veis que cabem no seu bolso.</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- In�cio do Rodap� -->
        <footer class=" color red text-white p-4">
            <div class="container text-center">
                <p>&copy; 2024 Ta� Telecom. Todos os direitos reservados.</p>
            </div>
        </footer>
        <!-- Fim do Rodap� -->


    </body>
</html>