<%@page import="modelo.entidades.Perfil"%>
<%@page import="modelo.persistencia.PerfilDAO"%>
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
        <link rel="stylesheet" href="css/scrollbar.css" type="text/css">


        <title>Taí Telecom - Alterar Funcionário</title>


    </head>
    <body>

        <%@include file="menu_login.jsp" %>

        <div class="main-content">
            <h2 class="text-center mb-4">Alterar Funcionário</h2>

            <!-- Formulário de Cadastro de Funcionários -->
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4"> <!-- Usando 4 colunas para telas grandes e 6 colunas para telas médias -->
                    <form action="gerenciar_funcionario_u.do" method="post">
                        <input type="hidden" name="action" value="modificar">

                        <%                            int id = Integer.parseInt(request.getParameter("id"));
                            try {

                                FuncionariosDAO fDB = new FuncionariosDAO();
                                Funcionarios fun = fDB.buscarPorId(id);
                                if (fun.getId() > 0) {
                        %>

                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="id" class="form-label"><strong>ID: <%=fun.getId()%></strong> </label>
                            <input type="hidden"  id="id" name="id" value="<%=fun.getId()%>" required>
                        </div>

                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="nome" class="form-label">Nome Completo</label>
                            <input type="text" class="form-control" id="nome" name="nome" value="<%=fun.getUsuario().getNome()%>" required>
                        </div>

                        <div class="mb-3">
                            <!-- Velocidade -->
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" class="form-control" id="cpf" name="cpf" maxlength="14" value="<%=fun.getUsuario().getCpf()%>" required>

                            <script>
                                document.getElementById('cpf').addEventListener('input', function (e) {
                                    // Remove qualquer caractere que não seja número
                                    this.value = this.value.replace(/\D/g, '');

                                    // Formata o CPF
                                    let value = this.value;
                                    if (value.length > 9) {
                                        value = value.replace(/(\d{3})(\d)/, '$1.$2'); // Adiciona o primeiro ponto
                                        value = value.replace(/(\d{3})(\d)/, '$1.$2'); // Adiciona o segundo ponto
                                        value = value.replace(/(\d{3})(\d{2})$/, '$1-$2'); // Adiciona o traço
                                    } else if (value.length > 6) {
                                        value = value.replace(/(\d{3})(\d)/, '$1.$2'); // Adiciona o primeiro ponto
                                        value = value.replace(/(\d{3})(\d{2})$/, '$1-$2'); // Adiciona o traço
                                    } else if (value.length > 3) {
                                        value = value.replace(/(\d{3})(\d)/, '$1.$2'); // Adiciona o primeiro ponto
                                    }

                                    this.value = value;
                                });
                            </script>

                        </div>

                        <div class="mb-3">
                            <!-- Valor -->
                            <label for="telefone" class="form-label">Telefone</label>
                            <input type="text" class="form-control" id="telefone" name="telefone" maxlength="15" value="<%=fun.getUsuario().getTelefone()%>" required>

                            <script>
                                document.getElementById('telefone').addEventListener('input', function (e) {
                                    // Remove qualquer caractere que não seja número
                                    this.value = this.value.replace(/\D/g, '');

                                    // Formata o telefone
                                    let value = this.value;

                                    if (value.length > 10) {
                                        value = value.replace(/(\d{2})(\d{5})(\d{4})$/, '($1) $2-$3'); // Formato (XX) XXXXX-XXXX
                                    } else if (value.length > 6) {
                                        value = value.replace(/(\d{2})(\d{5})(\d{0,4})$/, '($1) $2-$3'); // Formato (XX) XXXXX
                                    } else if (value.length > 2) {
                                        value = value.replace(/(\d{2})(\d+)/, '($1) $2'); // Formato (XX)
                                    }

                                    this.value = value;
                                });
                            </script>

                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">E-mail</label>
                            <input type="text" class="form-control" id="email" name="email" value="<%=fun.getUsuario().getEmail()%>" required>
                        </div>



                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" class="form-control" id="senha" name="senha" value="<%=fun.getUsuario().getSenha()%>" required>
                        </div>

                        <%

                            if (usuario.getPerfil().getPerfil().equals("Admin")) {

                        %>
                        <div class="mb-3">
                            <label for="cargo" class="form-label">Cargo</label>
                            <select class="form-control" id="cargo" name="cargo" required>

                                <option value="<%=fun.getUsuario().getPerfil().getId()%>" disabled selected><%=fun.getUsuario().getPerfil().getPerfil()%></option>
                                <%

                                    PerfilDAO perDB = new PerfilDAO();
                                    List<Perfil> lista = perDB.listar();

                                    for (Perfil per : lista) {

                                        if (per.getId() != 4) {

                                %>
                                <option value="<%=per.getId()%>"><%=per.getPerfil()%></option>
                                <% }
                                    }
                                %>

                            </select>
                        </div>
                                
                        <%} else {%>

                        <div class="mb-3">
                            <label for="cargo" class="form-label">Cargo</label>
                            <select class="form-control" id="cargo" name="cargo" disabled required>
                                <option value="<%=fun.getUsuario().getPerfil().getId()%>" disabled selected><%=fun.getUsuario().getPerfil().getPerfil()%></option>
                            </select>
                        </div>

                        <%}%>
                        <div class="mb-3">
                            <label for="salario" class="form-label">Salário</label>
                            <input type="number" class="form-control" id="salario" name="salario" value="<%=fun.getSalario()%>" required step="0.01" min="0">
                        </div>

                        <div class="mb-3">
                            <label for="ativo" class="form-label">Usuário do Funcionário</label>
                            <select class="form-select" id="ativo" name="ativo" required>
                                <option value="<%=fun.getUsuario().getAtivo()%>" disabled selected>
                                    <c:choose>
                                        <c:when test="<%=fun.getUsuario().getAtivo()%>">Ativo</c:when>
                                        <c:otherwise>Inativo</c:otherwise>
                                    </c:choose>
                                </option>
                                <option value="true">Ativo</option>
                                <option value="false">Inativo</option>
                            </select>
                        </div>

                        <!-- Botão Enviar -->
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger">Alterar Funcionário</button>
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
