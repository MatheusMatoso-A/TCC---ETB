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
        <title>Taí Telecom - Fibra Óptica</title>
    </head>
    <body>

        <header>
            <div class="container">
                <%@include file="menu.jsp" %>
            </div>
        </header>

        <main>
            <div class="container-fluid py-5">
                <div class="row">
                    <form action="" method="post" class="col-4 formulario-cadastro">
                        <h2 class="text-center title-form">Informe seus dados</h2>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control"  placeholder="Digite seu Nome Completo" id="nome" name="nome" required>
                            <label for="nome">Nome Completo</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                            <label for="floatingInput"> Infome seu e-mail</label>
                        </div>


                        <div class="form-floating mb-3">
                            <input type="tel"  class="form-control" placeholder="Digite seu telefone" id="telefone" name="telefone" maxlength="15"  required>
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
                        <div class="text-center">
                            <button type="submit" class="btn btn-red vantagem-img my-5">
                                Enviar
                            </button> 
                        </div>
                    </form>
                    
                    <div class="col-5">
                        
                        
                        
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
