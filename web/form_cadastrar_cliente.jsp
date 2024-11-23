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

    </head>
    <body>

        <%@include file="menu_login.jsp" %>

        <div class="main-content">
            <h2 class="text-center mb-4">Cadastrar Cliente</h2>

            <!-- Formulário de Cadastro de Clientes -->
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4"> <!-- Usando 4 colunas para telas grandes e 6 colunas para telas médias -->
                    <form action="cadastrar_cliente.do" method="post">
                        <h3 class="text-center mb-4 text-danger"><strong> Dados do Cliente </strong></h3>
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

                        <h3 class="text-center mb-4 text-danger"><strong> Endereço do Cliente e Agendamento de Visita </strong></h3>

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
                                    <option value="2024-09-19 08:00">19/09/2024 08:00</option>
                                    <option value="2024-09-19 10:00">19/09/2024 10:00</option>
                                    <option value="2024-09-19 14:00">19/09/2024 14:00</option>
                                </select>
                            </div>
                        </div>


                        <!-- Botão Enviar -->
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger">Cadastrar Cliente</button>
                        </div>


                    </form>

                </div>

            </div>


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

        </div>

    </body>
</html>
