<%@page import="modelo.entidades.AreaCobertura"%>
<%@page import="modelo.persistencia.AreaCoberturaDAO"%>
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


        <title>Taí Telecom - Alterar Área de Cobertura</title>
    </head>
    <body>

        <%@include file="menu_login.jsp" %>

        <div class="main-content">
            <h2 class="text-center mb-4">Alterar Área de Cobertura</h2>

            <!-- Formulário de Cadastro de Área de cobertura -->
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4"> <!-- Usando 4 colunas para telas grandes e 6 colunas para telas médias -->
                    <form action="gerenciar_area.do" method="post">
                        <input type="hidden" name="action" value="modificar">

                        <%                            int id = Integer.parseInt(request.getParameter("id"));
                            try {
                                AreaCoberturaDAO acDB = new AreaCoberturaDAO();
                                AreaCobertura ac = acDB.buscarPorId(id);
                                if (ac.getId() > 0) {
                        %>


                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="id" class="form-label"><strong>ID: <%=ac.getId()%></strong> </label>
                            <input type="hidden"  id="id" name="id" value="<%=ac.getId()%>" required>
                        </div>

                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" class="form-control" id="cep" name="cep"  maxlength="8" value="<%=ac.getCep() %>" required>
                        </div>

                        <div class="mb-3">
                            <!-- Velocidade -->
                            <label for="cidade" class="form-label">Cidade</label>
                            <input type="text" class="form-control" id="cidade" name="cidade" value="<%=ac.getCidade() %>"  required>
                        </div>

                        <div class="mb-3">
                            <!-- Valor -->
                            <label for="estado" class="form-label">Estado</label>
                            <input type="text" class="form-control" id="estado" name="estado" maxlength="2" value="<%=ac.getEstado() %>" required>
                        </div>


                        <!-- Botão Enviar -->
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger">Alterar Área</button>
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

        </div>
    </body>
</html>
