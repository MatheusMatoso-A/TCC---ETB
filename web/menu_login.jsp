<%@page import="modelo.entidades.Funcionarios"%>
<%@page import="modelo.persistencia.FuncionariosDAO"%>
<%@page import="modelo.entidades.Clientes"%>
<%@page import="modelo.persistencia.ClientesDAO"%>
<%@page import="modelo.entidades.PerfilMenu"%>
<%@page import="java.util.List"%>
<%@page import="modelo.persistencia.PerfilMenuDAO"%>
<%@page import="modelo.entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    // Verifica se o usuário está logado
    if (session.getAttribute("usuario") == null) {
        out.print("<script language='javascript'>");
        out.print("location.href='login.jsp';");
        out.print("</script>");
    }
%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/menu_login.css" type="text/css">


<div class="sidebar p-3">
    <h1 class="text-center"> <img src="imagens/tai.png" alt="Logo"></h1>
    <nav class="nav flex-column">

        <%
            Usuario usuario = new Usuario();
            usuario = (Usuario) session.getAttribute("usuario");
            int i = usuario.getId();

            if (usuario == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            PerfilMenuDAO pmDAO = new PerfilMenuDAO();
            List<PerfilMenu> listaPM = null;

            try {
                listaPM = pmDAO.perfilMenuVinculado(usuario.getPerfil().getId());
            } catch (Exception e) {

                e.printStackTrace();
                response.sendRedirect("login.jsp");

            }

            for (PerfilMenu pm : listaPM) {

        %>

        <a class="nav-link" href="<%=pm.getMenu().getLink()%>"><i class="<%=pm.getMenu().getIcone()%>"></i> <%=pm.getMenu().getMenu()%> </a>


        <%
            }
        %>
    </nav>
</div>


<div class="header">
    <h1></h1>
    <div class="header-content">
        <div class="user-menu dropdown">


            <button class="btn btn-danger dropdown-toggle" type="button" id="userDropdown" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <i class="bi bi-people"></i>
                <%=usuario.getNome()%>
            </button>
            <ul class="dropdown-menu dropdown-menu-lg-end" aria-labelledby="userDropdown">

                <%
                    // Determina o link de acordo com o perfil
                    String linkAlterarCadastro = "#"; // Default (caso algo dê errado)
                    switch (usuario.getPerfil().getId()) { // Substitua por outro atributo se necessário
                        case 4: // Exemplo: Perfil de cliente
                            if (usuario.getPerfil().getId() < 1) {

                                response.sendRedirect("login.jsp");

                            } else {

                                ClientesDAO cDB = new ClientesDAO();
                                Clientes c = cDB.buscarPorUsuario(i);

                                linkAlterarCadastro = "form_altera_cliente.jsp?id=" + c.getId();
                            }
                            break;
                        default:
                            if (usuario.getPerfil().getId() < 1) {

                                response.sendRedirect("login.jsp");

                            } else {

                                FuncionariosDAO fDB = new FuncionariosDAO();
                                Funcionarios f = fDB.buscarPorUsuarioId(i);

                                linkAlterarCadastro = "form_altera_fun.jsp?id=" + f.getId();
                            }
                            break;
                    }
                %>
                <li><a class="dropdown-item" href="<%= linkAlterarCadastro%>">Alterar Cadastro</a></li>
                <li><a class="dropdown-item" href="sair.do">Sair</a></li>
            </ul>

            <%

            %>


        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
crossorigin="anonymous"></script>
