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


        <title>Taí Telecom - Alterar Cliente</title>

    </head>
    <body>

        <%@include file="menu_login.jsp" %>

        <div class="main-content">
            <h2 class="text-center mb-4">Alterar Cliente</h2>

            <!-- Formulário de Cadastro de Clientes -->
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4"> <!-- Usando 4 colunas para telas grandes e 6 colunas para telas médias -->
                    <form action="cadastrar_cliente.do" method="post">
                        <input type="hidden" name="action" value="modificar">

                        <%                            int id = Integer.parseInt(request.getParameter("id"));
                            try {

                                ClientesDAO cDB = new ClientesDAO();
                                Clientes c = cDB.buscarPorId(id);
                                if (c.getId() > 0) {
                        %>

                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="id" class="form-label"><strong>ID: <%=c.getId()%></strong> </label>
                            <input type="hidden"  id="id" name="id" value="<%=c.getId()%>" required>
                        </div>

                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="nome" class="form-label">Nome Completo</label>
                            <input type="text" class="form-control" id="nome" name="nome" value="<%=c.getUsuario().getNome()%>" required>
                        </div>


                        <div class="mb-3">
                            <!-- Valor -->
                            <label for="telefone" class="form-label">Telefone</label>
                            <input type="text" class="form-control" id="telefone" name="telefone" maxlength="15" value="<%=c.getUsuario().getTelefone()%>" required>

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
                            <input type="text" class="form-control" id="email" name="email" value="<%=c.getUsuario().getEmail()%>" required>
                        </div>



                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" class="form-control" id="senha" name="senha" value="<%=c.getUsuario().getSenha()%>" required>
                        </div>

                        <div class="mb-3">
                            <label for="tipo-residencia" class="form-label">Tipo de Residência</label>
                            <select class="form-select" id="tipo-residencia" name="tipoResidencia" required>
                                <option value="<%=c.getTipoEndereco()%>" disabled selected><%=c.getTipoEndereco()%></option>
                                <option value="casa">Casa</option>
                                <option value="apartamento">Apartamento</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" name="cep" maxlength="9" class="form-control" id="cep" placeholder="Digite o CEP" value="<%=c.getCep()%>" required>

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
                            <input type="text" name="endereco" class="form-control" id="rua" placeholder="Rua" value="<%=c.getEndereco()%>" required >
                        </div>
                        <div class="mb-3">
                            <label for="numero" class="form-label">Número</label>
                            <input type="text" name="numero" class="form-control" id="numero" value="<%=c.getNumero()%>" placeholder="Número" required>
                        </div>

                        <!-- Campo Complemento com Exemplo -->
                        <div class="mb-3">
                            <label for="complemento" class="form-label">Complemento</label>
                            <input type="text" name="complemento" class="form-control" id="complemento" value="<%=c.getComplemento()%>"  placeholder="Exemplo: Número do apartamento, nome do condomínio, bloco">
                        </div>

                        <!-- Ponto de Referência -->
                        <div class="mb-3">
                            <label for="ponto-referencia" class="form-label">Ponto de Referência</label>
                            <input type="text" name="pontoReferencia" class="form-control" id="ponto-referencia" value="<%=c.getPontoReferencia()%>" placeholder="Ponto de Referência">
                        </div>



                        <%

                            if (usuario.getPerfil().getPerfil().equals("Admin")) {

                        %>

                        <div class="mb-3">
                            <label for="ativo" class="form-label">Usuário do cliente</label>
                            <select class="form-select" id="ativo" name="ativo" required>
                                <option value="<%=c.getUsuario().getAtivo()%>" disabled selected>
                                    <c:choose>
                                        <c:when test="<%=c.getUsuario().getAtivo()%>">Ativo</c:when>
                                        <c:otherwise>Inativo</c:otherwise>
                                    </c:choose>
                                </option>
                                <option value="true">Ativo</option>
                                <option value="false">Inativo</option>
                            </select>
                        </div>

                        <%} else {%>

                        <div class="mb-3">
                            <label for="ativo" class="form-label"><strong>Usuário:  <c:choose>
                                        <c:when test="<%=c.getUsuario().getAtivo()%>">Ativo</c:when>
                                        <c:otherwise>Inativo</c:otherwise>
                                    </c:choose>
                                </strong> </label>
                            <input type="hidden"  id="ativo" name="ativo" value="<%=c.getUsuario().getAtivo()%>" required>
                        </div>


                        <%}%>
                        <!-- Botão Enviar -->
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger">Alterar Cliente</button>
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

    </body>
</html>
