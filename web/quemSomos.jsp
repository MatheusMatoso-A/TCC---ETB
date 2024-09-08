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
        <link rel="stylesheet" href="css/scrollbar.css" type="text/css">
        <meta charset="UTF-8">

        <title>Taí Telecom - Fibra Óptica</title>
    </head>
    <body>
        <!-- Início do cabeçalho -->
        <header>
            <div class="container">
                <!-- Inclusão do menu de navegação -->
                <%@include file="menu.jsp" %>
            </div>
        </header>

        <!-- Início da Seção "Quem Somos" -->
        <section class="py-5 ">
            <div class="container">
                <h2 class="text-center mb-5 ">Quem Somos</h2>

                <p class="text-center mb-4">A Taí Telecom é uma empresa devidamente autorizada pela Anatel, com soluções em sistemas de internet para redes corporativas, residenciais e com especialização em infraestrutura em condomínios. Em nosso quadro contamos com profissionais capacitados, motivo pelo qual, contamos com a nossa capacidade de satisfazer as aspirações de nossos clientes, parceiros e colaboradores.</p>

                <div class="row">
                    <div class="col-md-6 vantagem-img">
                        <img src="imagens/mundo.png" alt="Nossa Equipe" class="img-fluid rounded" width="70%" height="70%">
                    </div>
                    <div class="col-md-6 ">
                        <h3>Nossa Missão</h3>
                        <p>Prover conectividade de alta qualidade com atendimento humanizado, assegurando a melhor experiência para nossos clientes.</p>
                        <h3>Nossa Visão</h3>
                        <p>Ser a principal escolha para soluções de internet, expandindo nossa presença e impactando positivamente a comunidade.</p>
                    </div>

                </div>
            </div>
        </section>
        <!-- Fim da Seção "Quem Somos" -->

        <!-- Início da Seção de Valores -->
        <section class="bg-light py-5">
            <div class="container">
                <h3 class="text-center mb-4 vantagem-img">Nossos Valores</h3>
                <div class="row text-center">
                    <div class="col-md-4 ">
                        <i class="bi bi-award" style="font-size: 2rem;"></i>
                        <h5 class="mt-3 vantagem-img">Excelência</h5>
                        <p>Comprometimento com a entrega de serviços de qualidade.</p>
                    </div>
                    <div class="col-md-4 ">
                        <i class="bi bi-people " style="font-size: 2rem;"></i>
                        <h5 class="mt-3 vantagem-img">Empatia</h5>
                        <p>Atendimento ao cliente com respeito e consideração.</p>
                    </div>
                    <div class="col-md-4 ">
                        <i class="bi bi-lightbulb " style="font-size: 2rem;"></i>
                        <h5 class="mt-3 vantagem-img">Inovação</h5>
                        <p>Investimento contínuo em tecnologia para melhorar nossos serviços.</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- Fim da Seção de Valores -->

         <%@include file="whatsapp.jsp"%>
        
        <!-- Início do Rodapé -->
        <footer class=" color red text-white p-4">
            <div class="container text-center">
                <p>&copy; 2024 Taí Telecom. Todos os direitos reservados.</p>
            </div>
        </footer>
        <!-- Fim do Rodapé -->



    </body>
</html>