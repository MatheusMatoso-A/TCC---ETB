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
        <link rel="stylesheet" href="css/scrollbar.css" type="text/css">


        <title>Taí Telecom - Cadastrar Funcionário</title>

        <script>
            // Contador de matrícula para gerar números sequenciais (como 001, 002, 003, ...)
            let matriculaCounter = 1;

            // Função para gerar matrícula e preencher o campo automaticamente
            function gerarMatricula() {
                // Gera matrícula no formato: 001, 002, 003, ...
                const matricula = String(matriculaCounter).padStart(3, '0'); // Garante 3 dígitos
                document.getElementById("matricula").value = matricula;

                // Incrementa o contador para o próximo funcionário
                matriculaCounter++;
            }

            // Chama a função assim que a página carregar para gerar a matrícula inicial
            window.onload = gerarMatricula;
        </script>

    </head>
    <body>

        <%@include file="menu_login.jsp" %>

        <div class="main-content">
            <h2 class="text-center mb-4">Cadastrar Funcionário</h2>

            <!-- Formulário de Cadastro de Funcionários -->
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4"> <!-- Usando 4 colunas para telas grandes e 6 colunas para telas médias -->
                    <form action="cadastrar_funcionario.do" method="post">
                        <div class="mb-3">
                            <!-- Nome do Produto -->
                            <label for="nome" class="form-label">Nome Completo</label>
                            <input type="text" class="form-control" id="nome" name="nome"  required>
                        </div>

                        <div class="mb-3">
                            <!-- Velocidade -->
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" class="form-control" id="cpf" name="cpf" maxlength="14"  required>

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
                            <input type="tel" class="form-control" id="telefone" name="telefone" maxlength="15" required>

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
                            <input type="text" class="form-control" id="email" name="email"  required>
                        </div>

                        <div class="mb-3">
                            <label for="dataNascimento" class="form-label">Data de nascimento</label>
                            <input type="date" class="form-control" id="dataNascimento" name="dataNascimento"  required>
                        </div>

                        <div class="mb-3">
                            <label for="login" class="form-label">Login</label>
                            <input type="text" class="form-control" id="login" name="login"  required>
                        </div>

                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" class="form-control" id="senha" name="senha"  required>
                        </div>


                        <div class="mb-3">
                            <label for="cargo" class="form-label">Cargo</label>
                            <select class="form-control" id="cargo" name="cargo" required>
                                <option value="" disabled selected>Selecione o cargo</option>
                                <option value="admin">Admin</option>
                                <option value="vendedor">Vendedor</option>
                                <option value="tecnico">Técnico</option>
                            </select>
                        </div>


                        <div class="mb-3">
                            <label for="salario" class="form-label">Salário</label>
                            <input type="number" class="form-control" id="salario" name="salario" required step="0.01" min="0">
                        </div>

                        <div class="mb-3">
                            <label for="matricula" class="form-label">Matrícula</label>
                            <input type="text" class="form-control" id="matricula" name="matricula" readonly required>
                        </div>


                        <!-- Botão Enviar -->
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger">Cadastrar Funcionário</button>
                        </div>


                    </form>

                </div>

            </div>

        </div>
    </body>
</html>
