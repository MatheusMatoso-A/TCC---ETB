<%@page import="modelo.entidades.Produtos"%>
<%@page import="modelo.persistencia.ProdutosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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


        <title>Taí Telecom - Alterar Produto</title>
    </head>
    <body>

        <%@include file="menu_login.jsp" %>
        <%@include file="toast_warning.jsp" %>

        <div class="main-content">
            <h2 class="text-center mb-4">Alterar Produto</h2>

            <!-- Formulário de Alterar de Produto -->
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4"> <!-- Usando 4 colunas para telas grandes e 6 colunas para telas médias -->
                    <form action="gerenciar_produtos.do" method="post">
                        <input type="hidden" name="action" value="modificar">

                        <%                            int id = Integer.parseInt(request.getParameter("id"));
                            try {
                                ProdutosDAO pDB = new ProdutosDAO();
                                Produtos p = pDB.buscarPorId(id);
                                if (p.getId() > 0) {
                        %>

                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="id" class="form-label"><strong>ID: <%=p.getId()%></strong> </label>
                            <input type="hidden"  id="id" name="id" value="<%=p.getId()%>" required>
                        </div>

                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="nome" class="form-label">Nome do Produto</label>
                            <input type="text" class="form-control" id="nome" name="nome" value="<%=p.getNome()%>"  required>
                        </div>

                        <div class="mb-3">
                            <!-- Velocidade -->
                            <label for="velocidade" class="form-label">Velocidade</label>
                            <input type="text" class="form-control" id="velocidade" name="velocidade"  value="<%=p.getVelocidade()%>" required>
                        </div>

                        <div class="mb-3">
                            <!-- Valor -->
                            <label for="valor" class="form-label">Valor</label>
                            <input type="text" class="form-control" id="valor" name="valor" value=" <fmt:formatNumber value="<%=p.getValor()%>" pattern="#,##0.00" /> "  required>
                        </div>

                        <div class="mb-3">
                            <label for="ativo" class="form-label">Situação do Plano</label>
                            <select class="form-select" id="ativo" name="ativo" required>
                                <option value="<%=p.isAtivo()%>" disabled selected>
                                    <c:choose>
                                        <c:when test="<%=p.isAtivo()%>">Ativo</c:when>
                                        <c:otherwise>Inativo</c:otherwise>
                                    </c:choose>
                                </option>
                                <option value="true">Ativo</option>
                                <option value="false">Inativo</option>
                            </select>
                        </div>

                        <!-- Botão Enviar -->
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger">Alterar Produto</button>
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
