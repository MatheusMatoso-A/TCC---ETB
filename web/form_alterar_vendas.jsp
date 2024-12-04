<%@page import="modelo.persistencia.VendasDAO"%>
<%@page import="modelo.entidades.Vendas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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


        <title>Taí Telecom - Alterar Vendas</title>
    </head>
    <body>

        <%@include file="menu_login.jsp" %>

        <div class="main-content">
            <h2 class="text-center mb-4">Alterar Vendas</h2>

            <!-- Formulário de Cadastro de Área de cobertura -->
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4"> <!-- Usando 4 colunas para telas grandes e 6 colunas para telas médias -->
                    <form action="gerenciar_vendas.do" method="post">
                        <input type="hidden" name="action" value="modificar">

                        <%                            int id = Integer.parseInt(request.getParameter("id"));
                            try {
                                VendasDAO vDB = new VendasDAO();
                                Vendas v = vDB.buscarPorId(id);
                                if (v.getId() > 0) {
                        %>


                        <div class="mb-3">

                            <label for="id" class="form-label"><strong>ID: <%=v.getId()%></strong> </label>
                            <input type="hidden"  id="id" name="id" value="<%=v.getId()%>" required>
                        </div>

                        <div class="mb-3">
                            <label for="foiPago" class="form-label">Fatura Paga</label>
                            <select class="form-select" id="foiPago" name="foiPago" required>
                                <option value="<%=v.isFoiPago() %>" disabled selected>
                                    <c:choose>
                                        <c:when test="<%=v.isFoiPago()%>">Sim</c:when>
                                        <c:otherwise>Não</c:otherwise>
                                    </c:choose>
                                </option>
                                <option value="true">Sim</option>
                                <option value="false">Não</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="dataVencimento" class="form-label">Selecione o dia do vencimento da Fatura</label>
                            <select class="form-select" id="dataVencimento" name="dataVencimento" required>
                                <option value="<%=v.getDataVencimento()%>" disabled selected><%=v.getDataVencimento()%></option>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            
                            <jsp:useBean class="modelo.persistencia.ProdutosDAO" id="pDB"/>
                            
                            <label for="produto" class="form-label">Selecione o Plano</label>
                            <select class="form-select" id="produto" name="produto" required>
                                <option value="<%=v.getProdutos().getId() %>" disabled selected><%=v.getProdutos().getNome() %></option>
                            
                                <c:forEach var="p" items="${pDB.listarAtivos()}">
                                    
                                <option value="${p.id}">${p.nome}</option>
                                
                                </c:forEach>
                                
                            </select>
                                
                        </div>


                        <!-- Botão Enviar -->
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger">Alterar Venda</button>
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
