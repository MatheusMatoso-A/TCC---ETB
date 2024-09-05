<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/reset.css" type="text/css">
        <title>Taí Telecom - Fibra Óptica</title>
    </head>
    <body>
        <!-- In?cio do cabe?alho -->
        <header>
            <div class="container">
                <!-- Inclus?o do menu de navega??o -->
                <%@include file="menu.jsp" %>
            </div>
        </header>

        <!-- Fim do cabeçalho -->
        <div class="conteudo">
        <!-- Imagem de destaque -->
        <div class="container-fluid p-0">
            <img src="imagens/imagem-index.png" class="img-fluid w-100" alt="Imagem de destaque"width="100" height="100">


            <!-- Fim do cabe?alho -->


        </div>

        <!-- Seção de Planos de Internet -->
        <section class="my-5">
            <div class="container">
                <h2 class="text-center mb-4">Nossos Planos de Internet</h2>
                <div class="row">
                    <!-- Plano 1 -->
                    
                    <%@include file="basico.jsp" %>

                    <!-- Plano 2 -->
                    
                    <%@include file="intermediario.jsp" %>

                    <!-- Plano 3 -->
                 
                    <%@include file="avancado.jsp" %>
                    
                    <!-- Plano 4 --> 
                    
                    <%@include file="ultra.jsp" %>
                    
                </div>
            </div>
        </section>

        <!-- Incluindo o modal.jsp -->
        <jsp:include page="modal.jsp" />

        <!-- Seção de Vantagens -->
        <section class="bg-light py-5">
            <div class="container">
                <h2 class="text-center mb-4">Vantagens de Escolher a Taí Telecom</h2>
                <div class="row">
                    <div class="col-md-4 text-center">
                        <img src="imagens/velocidade.png" alt="Velocidade" class="mb-3 vantagem-img" width="50" height="50">
                        <h5>Velocidade e Estabilidade</h5>
                        <p>Internet rápida e estável com fibra óptica de última geração.</p>
                    </div>
                    <div class="col-md-4 text-center">
                        <img src="imagens/suporte.png" alt="Suporte" class="mb-3 vantagem-img" width="50" height="50">
                        <h5>Suporte 24/7</h5>
                        <p>Atendimento especializado disponível a qualquer hora.</p>
                    </div>
                    <div class="col-md-4 text-center">
                        <img src="imagens/beneficio.png" alt="Custo-benefício" class="mb-3 vantagem-img" width="50" height="50">
                        <h5>Melhor Custo-Benefício</h5>
                        <p>Planos acessíveis que cabem no seu bolso.<br> 
                            Planos especiais para negativados, para mais informações, clique em <span style="color: red" ><strong>Fale Conosco</strong></span> e entre em contato com a nossa central de atendimento.</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Incluindo o whatsapp -->
        <%@include file="whatsapp.jsp"%>



        <!-- Início do Rodapé -->
        <footer class=" color red text-white p-4">
            <div class="container text-center">
                <p>&copy; 2024 Taí Telecom. Todos os direitos reservados.</p>
            </div>
        </footer>
        <!-- Fim do Rodapé -->

    </div>
</body>
</html>
