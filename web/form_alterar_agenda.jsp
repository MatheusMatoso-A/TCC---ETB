<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="modelo.entidades.Agenda"%>
<%@page import="modelo.persistencia.AgendaDAO"%>
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

        <!-- Flatpickr CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
        <!-- Flatpickr Locale PT-BR -->
        <script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/pt.js"></script>

        <title>Taí Telecom - Alterar Agendamento</title>
    </head>
    <body>

        <%@include file="menu_login.jsp" %>
        <%@include file="toast_warning.jsp" %>


        <div class="main-content">
            <h2 class="text-center mb-4">Alterar Agendamento</h2>

            <!-- Formulário de Cadastro de Agendamento -->
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4"> <!-- Usando 4 colunas para telas grandes e 6 colunas para telas médias -->
                    <form action="gerenciar_agendamento.do" method="post">
                        <input type="hidden" name="action" value="modificar">
                        <%                            int id = Integer.parseInt(request.getParameter("id"));
                            try {
                                AgendaDAO pDB = new AgendaDAO();
                                Agenda ag = pDB.buscarPorId(id);
                                if (ag.getId() > 0) {
                        %>

                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="id" class="form-label"><strong>ID: <%=ag.getId()%></strong> </label>
                            <input type="hidden"  id="id" name="id" value="<%=ag.getId()%>" required>
                        </div>

                        <%
                            // Convertendo e formatando a data
                            LocalDateTime dataComparecimento = ag.getDataComparecimento();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                            String dataFormatada = dataComparecimento.format(formatter);
                        %>

                        <div class="mb-3">
                            <label for="dataHora" class="form-label">Data e Hora</label>
                            <input type="text" class="form-control" id="dataHora" name="dataHora" placeholder="Selecione data e hora" value=" <%= dataFormatada%>" required>                                   
                        </div>

                        <div class="mb-3">
                            <label for="status" class="form-label">Status da Agenda</label>
                            <select class="form-select" id="status" name="status" required>
                                <option value="<%=ag.getStatus()%>" disabled selected><%=ag.getStatus()%></option>
                                <option value="Disponível">Disponível</option>
                                <option value="Reservado">Reservado</option>
                                <option value="Atendendo">Atendendo</option>
                                <option value="Finalizado">Finalizado</option>
                            </select>
                        </div>

                        <!-- Botão Enviar -->
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger">Alterar Agendamento</button>
                        </div>

                        <%

                                }
                            } catch (Exception e) {

                                e.printStackTrace();
                            }

                        %>


                    </form>

                </div>

            </div>

            <script>
                flatpickr("#dataHora", {
                    enableTime: true, // Habilita o seletor de hora
                    dateFormat: "d-m-Y H:i", // Formato de data e hora
                    minDate: "today", // A data mínima é o dia de hoje
                    locale: "pt", // Define o idioma para português brasileiro
                });
            </script>
        </div>
    </body>
</html>
