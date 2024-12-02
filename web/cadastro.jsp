<%@page import="modelo.entidades.PreCadastro"%>
<%@page import="modelo.persistencia.PreCadastroDAO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="modelo.entidades.Produtos"%>
<%@page import="modelo.persistencia.ProdutosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


        <link rel="stylesheet" href="css/reset.css" type="text/css">
        <link rel="stylesheet" href="css/scrollbar.css" type="text/css">
        <link rel="stylesheet" href="css/cadastro.css" type="text/css">
        <title>Taí Telecom - Cadastro</title>
    </head>
    <body>

        <header>
            <div class="container">
                <%@include file="menu.jsp" %>
                <%@include file="toast.jsp" %>
            </div>
        </header>

        <main>
            <div class="container-fluid py-5">
                <div class="row justify-content-center">
                    <form action="inserir_usuario_c.do" method="post" class="col-4 formulario-cadastro">
                        <h2 class="text-center title-form">Informe seus dados</h2>

                        <%
                            int planoId = Integer.parseInt(request.getParameter("planoId"));
                            int preCadastroId = Integer.parseInt(request.getParameter("preCadastroId"));

                            try {

                                PreCadastroDAO pcDAO = new PreCadastroDAO();

                                PreCadastro pc = pcDAO.buscarPorId(preCadastroId);

                                if (pc.getId() > 0) {

                        %>

                        <div class="form-floating mb-3">
                            <input type="text" class="form-control"  placeholder="Digite seu Nome Completo" id="nome" name="nome" value="<%= pc.getNome()%>" required>
                            <label for="nome">Nome Completo</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="email" placeholder="name@example.com" value="<%= pc.getEmail()%>" name="email" required>
                            <label for="floatingInput"> Infome seu e-mail</label>
                        </div>


                        <div class="form-floating mb-3">
                            <input type="text"  class="form-control" placeholder="Digite seu telefone" id="telefone" name="telefone" maxlength="15"  value=" <%= pc.getTelefone()%>" required>
                            <label for="telefone">Celular (WhatsApp)</label>


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

                        <div class="row g-2">
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="cpf" name="cpf" placeholder="Digete seu cpf" maxlength="14"  required>
                                    <label for="cpf">CPF</label>

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
                            </div>
                            <div class="col-md">
                                <div class="form-floating">
                                    <input type="date" class="form-select" id="data" name="dataNascimento" placeholder="escolha data de nascimento" required>
                                    <label for="dataNascimento">Data de Nascimento</label>
                                </div>
                            </div>
                        </div>


                        <div class="form-floating mb-3">
                            <h3 class="my-3 text-center">Selecione o dia do vencimento de sua fatura</h3>
                            <select class="form-select" id="dataVencimento" name="dataVencimento" onchange="updateSelectedDay()">
                                <option value="" disabled selected>Selecione um dia</option>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                            </select>
                        </div>

                        <div class="notice text-center">
                            A fatura será enviada todo mês para seu e-mail.
                        </div>


                        <h3 class="my-3 text-center">Escolha as credencias para acesso a área do cliente</h3>

                        <div class="form-floating mb-3">

                            <input type="text" class="form-control" placeholder="Digite seu Login" id="login" name="login"
                                   required>
                            <label for="login">Login</label>
                        </div>

                        <div class="form-floating mb-3 password-field">
                            <input type="password" class="form-control" id="senha" name="senha" placeholder="Sua senha" required>
                            <label for="senha">Senha</label>
                            <i class="bi bi-eye eye-icon" id="togglePassword"></i>

                        </div>

                        <div class="form-floating mb-3 password-field">
                            <input type="password" class="form-control" id="confirmSenha" placeholder="Confirme sua senha"
                                   required>
                            <label for="confirmSenha">Confirmar Senha</label>
                            <i class="bi bi-eye eye-icon" id="toggleConfirmPassword"></i>
                        </div>


                        <script>
                            // Função para alternar a visibilidade da senha
                            function togglePasswordVisibility(passwordFieldId, iconId) {
                                const passwordField = document.getElementById(passwordFieldId);
                                const toggleIcon = document.getElementById(iconId);

                                toggleIcon.addEventListener('click', function () {
                                    const type = passwordField.type === 'password' ? 'text' : 'password';
                                    passwordField.type = type;
                                    toggleIcon.classList.toggle('bi-eye');
                                    toggleIcon.classList.toggle('bi-eye-slash');
                                });
                            }
                            ;

                            // Inicializa a visibilidade da senha para ambos os campos
                            togglePasswordVisibility('senha', 'togglePassword');
                            togglePasswordVisibility('confirmSenha', 'toggleConfirmPassword');


                        </script>

                        <div class="notice text-center">
                            Só poderá fazer login e ter acesso a área do cliente após o pagamento.
                        </div>

                        <input type="hidden" id="preCadastroId" name="preCadastroId" value="<%=pc.getId()%>"/>

                        <input type="hidden" id="perfil" name="perfil" value="4"/>
                        
                        <input type="hidden" id="planoId" name="planoId" value="<%=planoId%>"/>

                        <div class="text-center">
                            <button type="submit" class="btn botao my-3" onclick="submitForm()">
                                Enviar
                            </button> 

                            <script>


                                // Validação do formulário
                                document.getElementById('paymentForm').addEventListener('submit', function (event) {
                                    const senha = document.getElementById('senha').value;
                                    const confirmSenha = document.getElementById('confirmSenha').value;
                                    if (senha !== confirmSenha) {
                                        alert('As senhas não correspondem.');
                                        event.preventDefault(); // Impede o envio do formulário
                                    }
                                });
                            </script>

                        </div>
                    </form>

                    <% }

                        } catch (Exception e) {
                            out.print(e);
                            
                        }%>



                    <div class="col-5 my-5">
                        <%                            try {

                                ProdutosDAO pDAO = new ProdutosDAO();

                                Produtos p = pDAO.buscarPorId(planoId);
                                if (p.getId() > 0) {
                                    // Formatação do valor
                                    DecimalFormat df = new DecimalFormat("#,##0.00");
                                    String valorFormatado = df.format(p.getValor());

                        %>

                        <div class="card text-dark border-dark my-4 ">
                            <div class="card-header text-center bg-danger "><h4> <strong> Plano <%=p.getNome()%> </strong></h4></div>
                            <div class="card-body text-center">
                                <h5 class="card-title"><%=p.getVelocidade()%> de Velocidade </h5>
                                <p class="card-text text-center">Conexão ultra rápida para todos os usos</p>
                                <p class="card-text text-center">Download ilimitado</p>
                                <p class="card-text text-center">Suporte 24/7</p>
                                <p class="card-text text-center"><strong>R$ <%=valorFormatado%>/mês</strong></p>
                            </div>
                        </div>

                        <%                                    }

                            } catch (Exception e) {
                                out.print(e);
                            }

                        %>

                    </div>

                </div>
            </div>
            <!-- Incluindo o whatsapp -->
            <%@include file="whatsapp.jsp"%>
        </main>

        <footer class=" color red text-white p-4">
            <div class="container text-center">
                <p>&copy; 2024 Taí Telecom. Todos os direitos reservados.</p>
            </div>
        </footer>

    </body>
</html>
