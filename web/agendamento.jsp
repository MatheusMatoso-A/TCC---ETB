<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="modelo.entidades.Agenda"%>
<%@page import="java.util.List"%>
<%@page import="modelo.persistencia.AgendaDAO"%>
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
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/endereco.js"></script>
        <link rel="stylesheet" href="css/agendamento.css" type="text/css">
        <link rel="stylesheet" href="css/reset.css" type="text/css">
        <link rel="stylesheet" href="css/scrollbar.css" type="text/css">
        <title>Taí Telecom - Agendar visita</title>
    </head>
    <body>
        <header>
            <div class="container">
                <!-- Inclus?o do menu de navega??o -->
                <%@include file="menu.jsp" %>
            </div>
        </header>

        <main>

            <div class="container conteudo-agenda">
                <h2 class="text-center mb-4">Agendamento de Visita Técnica</h2>

                <!-- Formulário -->
                <form id="agendamento-form" method="post" action="processaDados.jsp">
                    <!-- Seletor de Tipo de Residência -->
                    <div class="mb-3">
                        <label for="tipo-residencia" class="form-label">Tipo de Residência</label>
                        <select class="form-select" id="tipo-residencia" name="tipoResidencia" required>
                            <option value="" disabled selected>Selecione o tipo de residência...</option>
                            <option value="casa">Casa</option>
                            <option value="apartamento">Apartamento</option>
                        </select>
                    </div>

                    <!-- Campos Comuns de Endereço (exibidos após selecionar o tipo) -->
                    <div id="campo-comum" style="display: none;">
                        <div class="mb-3">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" name="cep" maxlength="9" class="form-control" id="cep" placeholder="Digite o CEP" onblur="buscarEndereco()" required>

                            <script>
                                document.getElementById('cep').addEventListener('input', function (e) {
                                    // Remove qualquer caractere que não seja número
                                    this.value = this.value.replace(/\D/g, '');

                                    // Formata o CEP
                                    let value = this.value;

                                    if (value.length > 5) {
                                        value = value.replace(/(\d{5})(\d{0,3})/, '$1-$2'); // Formato XXXXX-XXX
                                    }

                                    this.value = value;
                                });
                            </script>
                        </div>

                        <div class="mb-3">
                            <label for="rua" class="form-label">Endereço</label>
                            <input type="text" name="endereco" class="form-control" id="rua" placeholder="Rua" required disabled>
                        </div>
                        <div class="mb-3">
                            <label for="numero" class="form-label">Número</label>
                            <input type="text" name="numero" class="form-control" id="numero" placeholder="Número" required>
                        </div>

                        <div class="mb-3">
                            <label for="bairro" class="form-label">Bairro</label>
                            <input type="text" name="bairro" class="form-control" id="bairro" placeholder="Bairro" required disabled>
                        </div>
                        <div class="mb-3">
                            <label for="cidade" class="form-label">Cidade</label>
                            <input type="text" name="cidade" class="form-control" id="cidade" placeholder="Cidade" required disabled>
                        </div>
                        <div class="mb-3">
                            <label for="estado" class="form-label">Estado</label>
                            <input type="text" name="estado" class="form-control" id="estado" placeholder="Estado" required disabled>
                        </div>

                        <!-- Campo Complemento com Exemplo -->
                        <div class="mb-3">
                            <label for="complemento" class="form-label">Complemento</label>
                            <input type="text" name="complemento" class="form-control" id="complemento" placeholder="Exemplo: Número do apartamento, nome do condomínio, bloco">
                        </div>

                        <!-- Ponto de Referência -->
                        <div class="mb-3">
                            <label for="ponto-referencia" class="form-label">Ponto de Referência</label>
                            <input type="text" name="pontoReferencia" class="form-control" id="ponto-referencia" placeholder="Ponto de Referência">
                        </div>

                        <!-- Seletor de Data e Hora Disponíveis -->
                        <div class="mb-3">


                            <label for="horario" class="form-label">Selecione o Dia e Hora</label>
                            <select class="form-select" name="horario" id="horario" required>
                                <option value="">Escolha um horário disponível</option>

                                <%
                                    try {
                                        AgendaDAO agDAO = new AgendaDAO();
                                        List<Agenda> lista = agDAO.buscarPorStatus();

                                        for (Agenda ag : lista) {
                                %>


                                <option value="<%=ag.getDataComparecimento() %>">

                                    <%
                                        // Convertendo e formatando a data
                                        LocalDateTime dataComparecimento = ag.getDataComparecimento();
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                                        String dataFormatada = dataComparecimento.format(formatter);
                                    %>
                                    <%= dataFormatada%>


                                </option>
                                <%                                        }
                                    } catch (Exception e) {
                                        out.print(e);
                                    }

                                %>

                            </select>
                        </div>
                    </div>

                    <div class="d-flex justify-content-center">
                        <!-- Botão de Enviar -->
                        <button type="submit" class="btn botao my-3">Agendar Visita</button>
                    </div>
                </form>


                <script>
                    const tipoResidencia = document.getElementById('tipo-residencia');
                    const campoComum = document.getElementById('campo-comum');

                    tipoResidencia.addEventListener('change', function () {
                        // Mostrar os campos comuns após a seleção do tipo de residência
                        if (this.value === 'casa' || this.value === 'apartamento') {
                            campoComum.style.display = 'block'; // Exibir os campos comuns (CEP, endereço, etc.)
                        } else {
                            // Ocultar os campos se nenhum tipo de residência estiver selecionado
                            campoComum.style.display = 'none';
                        }
                    });

                    // Submissão do formulário
                    document.getElementById('agendamento-form').addEventListener('submit', function (e) {
                        e.preventDefault();
                        alert('Visita agendada com sucesso!');
                    });
                </script>

                <%@include file="whatsapp.jsp"%>
        </main>

        <footer class=" color red text-white p-4">
            <div class="container text-center">
                <p>&copy; 2024 Taí Telecom. Todos os direitos reservados.</p>
            </div>
        </footer>

    </body>
</html>