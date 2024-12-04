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
        <link rel="stylesheet" href="css/inicio_login.css" type="text/css">
        <link rel="stylesheet" href="css/obrigatorio_login.css" type="text/css">
        <link rel="stylesheet" href="css/scrollbar.css" type="text/css">


        <!-- Script jQuery (necessário para o DataTables) -->
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>



        <!-- Link para o DataTables CSS -->
        <link href="https://cdn.datatables.net/2.1.8/css/dataTables.bootstrap5.min.css" rel="stylesheet">

        <!-- Script para o DataTables JS -->
        <script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/2.1.8/js/dataTables.bootstrap5.min.js"></script>

        <link rel="stylesheet" href="./css/listar.css" type="text/css">


        <title>Taí Telecom - Gerenciar clientes</title>
    </head>
    <body>

        <%@include file="menu_login.jsp" %>
        <%@include file="toast.jsp" %>
        <%@include file="toast_danger.jsp" %>
        <%@include file="toast_warning.jsp" %>


        <div class="main-content">
            <div class="table-responsive">
                <table id="tabela" class="table table-hover border-top border-danger caption-top">
                    <caption>Lista de clientes</caption>
                    <thead class="table-danger border-top border-bottom border-danger">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Telefone</th>
                            <th>E-mail</th>
                            <th>Login</th>
                            <th>Tipo de Endereço</th>
                            <th>CEP</th>
                            <th>Endereço</th>
                            <th>Número</th>
                            <th>Complemento</th>
                            <th>Ponto de referência</th>
                            <th>Ativo</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </thead>

                    <tbody>

                        <jsp:useBean class="modelo.persistencia.ClientesDAO" id="cDB"/>
                        <c:forEach var="c" items="${cDB.listar()}">
                            <tr>

                                <th>${c.id}</th>
                                <td>${c.usuario.nome}</td>
                                <td>${c.usuario.cpf}</td>
                                <td>${c.usuario.telefone}</td>
                                <td>${c.usuario.email}</td>
                                <td>${c.usuario.login}</td>
                                <td>${c.tipoEndereco}</td>
                                <td>${c.cep}</td>
                                <td>${c.endereco}</td>
                                <td>${c.numero}</td>
                                <td>${c.complemento}</td>
                                <td>${c.pontoReferencia}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${c.usuario.ativo}">Sim</c:when>
                                        <c:otherwise>Não</c:otherwise>
                                    </c:choose>

                                </td>
                                <td> <a href="form_altera_cliente.jsp?id=${c.id}"> <img class="imagem-tabela" src="./imagens/editar.png" alt="Alterar"> </a> </td>
                                <td> <a href="gerenciar_cliente_u.do?action=excluir&id=${c.id}"> <img class="imagem-tabela" src="./imagens/excluir.png" alt="Excluir"> </a> </td>

                            </tr>
                        </c:forEach>
                    </tbody>

                    <tfoot class="table-danger border-top border-bottom border-danger">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Telefone</th>
                            <th>E-mail</th>
                            <th>Login</th>
                            <th>Tipo de Endereço</th>
                            <th>CEP</th>
                            <th>Endereço</th>
                            <th>Número</th>
                            <th>Complemento</th>
                            <th>Ponto de referência</th>
                            <th>Ativo</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </tfoot>

                </table>

            </div>
        </div>
        <!-- Script para inicializar o DataTables -->
        <script>


            var table = new DataTable('#tabela', {
                language: {
                    url: 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/pt-BR.json',
                    "paginate": {
                        "next": ">",
                        "previous": "<",
                        "first": "<<",
                        "last": ">>"
                    },
                },
                ordering: false, // Desativa a ordenação das colunas
            });




        </script>


    </body>

</html>
