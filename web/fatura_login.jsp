<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">


        <link rel="stylesheet" href="css/obrigatorio_login.css" type="text/css">
        <link rel="stylesheet" href="css/fatura.css" type="text/css">


        <title>Taí Telecom - Clientes</title>
    </head>
    <body>

        <%@include file="menu_login.jsp" %>

        <div class="main-content text-center mt-5">
            <h2 class="text-center mb-4">Histórico de Faturas</h2>

            <!-- Fatura Atual -->
            <div class="card card-custom">
                <div class="card-header card-dark">
                    <h5 class="card-title mb-0">Fatura Atual</h5>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                        <p><strong>Valor:</strong> R$ 250,00</p>
                        <p class="status-paga p-2 rounded">Status: Paga</p>
                    </div>
                    <p><strong>Data de Vencimento:</strong> 15/11/2024</p>
                    <p><strong>Forma de Pagamento:</strong> Cartão de Crédito</p>
                </div>
            </div>

            <!-- Histórico de Faturas -->
            <h4 class="mb-3">Histórico de Faturas</h4>
            <div class="list-group">
                <div class="list-group-item d-flex justify-content-between align-items-center status-pendente">
                    <span>Fatura 001</span>
                    <span>R$ 200,00</span>
                    <span>15/10/2024</span>
                    <span class="badge badge-light">Pendente</span>
                </div>
                <div class="list-group-item d-flex justify-content-between align-items-center status-vencida">
                    <span>Fatura 002</span>
                    <span>R$ 150,00</span>
                    <span>05/10/2024</span>
                    <span class="badge badge-light">Vencida</span>
                </div>
                <div class="list-group-item d-flex justify-content-between align-items-center status-paga">
                    <span>Fatura 003</span>
                    <span>R$ 300,00</span>
                    <span>20/09/2024</span>
                    <span class="badge badge-light">Paga</span>
                </div>
            </div>
        </div>


    </body>
</html>
