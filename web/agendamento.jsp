<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="icon" type="image/png" sizes="16x16" href="imagens/favicon.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/agendamento.css" type="text/css">
        <link rel="stylesheet" href="css/reset.css" type="text/css">
        <link rel="stylesheet" href="css/scrollbar.css" type="text/css">
        <title>Taí Telecom - Fibra Óptica</title>
    </head>
    <body>
        <header>
            <div class="container">
                <!-- Inclus?o do menu de navega??o -->
                <%@include file="menu.jsp" %>
            </div>
        </header>

        <main>

            <div class="container conteudo-agenda">
                <h2 class="text-center mb-4">Agendamento de Visita Técnica</h2>

                <!-- Formulário -->
                <form id="agendamento-form">
                    <!-- Seletor de Tipo de Residência -->
                    <div class="mb-3">
                        <label for="tipo-residencia" class="form-label">Tipo de Residência</label>
                        <select class="form-select" id="tipo-residencia" required>
                            <option value="" disabled selected>Selecione o tipo de residência...</option>
                            <option value="casa">Casa</option>
                            <option value="condominio">Condomínio/Prédio</option>
                        </select>
                    </div>

                    <!-- Campos Comuns de Endereço (exibidos após selecionar o tipo) -->
                    <div class="endereco-completo">
                        <div class="mb-3">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" class="form-control" id="cep" placeholder="Digite o CEP" required>
                        </div>

                        <div class="mb-3">
                            <label for="rua" class="form-label">Endereço</label>
                            <input type="text" class="form-control" id="rua" placeholder="Rua" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="bairro" class="form-label">Bairro</label>
                            <input type="text" class="form-control" id="bairro" placeholder="Bairro" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="cidade" class="form-label">Cidade</label>
                            <input type="text" class="form-control" id="cidade" placeholder="Cidade" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="estado" class="form-label">Estado</label>
                            <input type="text" class="form-control" id="estado" placeholder="Estado" disabled>
                        </div>

                    </div>

                    <!-- Campos Específicos para Condomínio/Prédio -->
                    <div id="campo-apartamento">
                        <div class="mb-3">
                            <label for="bloco" class="form-label">Bloco</label>
                            <input type="text" class="form-control" id="bloco" placeholder="Bloco">
                        </div>
                        <div class="mb-3">
                            <label for="numero-apartamento" class="form-label">Número do Apartamento</label>
                            <input type="text" class="form-control" id="numero-apartamento" placeholder="Número do Apartamento" required>
                        </div>
                    </div>

                    <!-- Campos Específicos para Casa -->
                    <div id="campo-casa">
                        <div class="mb-3">
                            <label for="numero-casa" class="form-label">Número da Casa</label>
                            <input type="text" class="form-control" id="numero-casa" placeholder="Número da Casa" required>
                        </div>
                    </div>


                    <div class="endereco-completo"  id="campo-condominio">
                        <div class="mb-3">
                            <label for="condominio" class="form-label">Condomínio</label>
                            <input type="text" class="form-control" id="condominio" placeholder="Condomínio">
                        </div>
                        <div class="mb-3">
                            <label for="ponto-referencia" class="form-label">Ponto de Referência</label>
                            <input type="text" class="form-control" id="ponto-referencia" placeholder="Ponto de Referência">
                        </div>
                    </div>

                    <!-- Seletor de Data e Hora Disponíveis -->
                    <div class="mb-3 endereco-completo">
                        <label for="horario" class="form-label">Selecione o Dia e Hora</label>
                        <select class="form-select" id="horario" required>
                            <option value="">Escolha um horário disponível</option>
                            <option value="2024-09-19 08:00">19/09/2024 08:00</option>
                            <option value="2024-09-19 10:00">19/09/2024 10:00</option>
                            <option value="2024-09-19 14:00">19/09/2024 14:00</option>
                        </select>
                    </div>

                    <div class="d-flex justify-content-center">
                        <!-- Botão de Enviar -->
                        <button type="submit" class="btn botao my-3 endereco-completo">Agendar Visita</button>
                    </div>
                </form>
            </div>

            <script>
                // Função para exibir campos de endereço completo
                const tipoResidencia = document.getElementById('tipo-residencia');
                const camposEnderecoCompleto = document.querySelectorAll('.endereco-completo');
                const campoApartamento = document.getElementById('campo-apartamento');
                const campoCasa = document.getElementById('campo-casa');
                const campoCondominio = document.getElementById('campo-condominio');

                tipoResidencia.addEventListener('change', function () {
                    // Mostrar campos de endereço completo após seleção do tipo de residência
                    if (this.value === 'casa' || this.value === 'condominio') {
                        camposEnderecoCompleto.forEach(campo => campo.style.display = 'block');

                        if (this.value === 'condominio') {
                            campoApartamento.style.display = 'block';
                            campoCasa.style.display = 'none';
                        } else if (this.value === 'casa') {
                            campoCasa.style.display = 'block';
                            campoApartamento.style.display = 'none';
                        }

                        // Mostrar campos comuns "Condomínio" e "Ponto de Referência" após os campos específicos
                        campoCondominio.style.display = 'block';
                    } else {
                        // Ocultar todos os campos se nenhum tipo de residência estiver selecionado
                        camposEnderecoCompleto.forEach(campo => campo.style.display = 'none');
                        campoApartamento.style.display = 'none';
                        campoCasa.style.display = 'none';
                        campoCondominio.style.display = 'none';
                    }
                });

                // Submissão do formulário
                document.getElementById('agendamento-form').addEventListener('submit', function (e) {
                    e.preventDefault();
                    alert('Visita agendada com sucesso!');
                });

                // Preenchimento automático do CEP
                document.getElementById('cep').addEventListener('blur', function () {
                    let cep = this.value.replace(/\D/g, '');

                    document.getElementById('rua').disabled = true;
                    document.getElementById('bairro').disabled = true;
                    document.getElementById('cidade').disabled = true;
                    document.getElementById('estado').disabled = true;

                    if (cep.length === 8) {
                        fetch(`/buscarCep?cep=${cep}`)
                                .then(response => response.json())
                                .then(data => {
                                    if (!data.erro) {
                                        document.getElementById('rua').value = data.logradouro;
                                        document.getElementById('bairro').value = data.bairro;
                                        document.getElementById('cidade').value = data.localidade;
                                        document.getElementById('estado').value = data.uf;

                                        document.getElementById('rua').disabled = true;
                                        document.getElementById('bairro').disabled = true;
                                        document.getElementById('cidade').disabled = true;
                                        document.getElementById('estado').disabled = true;
                                    } else {
                                        alert('CEP não encontrado.');
                                    }
                                })
                                .catch(() => alert('Erro ao buscar o CEP.'));
                    } else {
                        alert('CEP inválido.');
                    }
                });
            </script>

            <%@include file="whatsapp.jsp"%>
        </main>

        <footer class=" color red text-white p-4">
            <div class="container text-center">
                <p>&copy; 2024 Taí Telecom. Todos os direitos reservados.</p>
            </div>
        </footer>

    </body>
</html>
