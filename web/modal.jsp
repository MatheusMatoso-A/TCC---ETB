<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Carrega o conte�do do cadastro.jsp via AJAX -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>

<script>
    // Usando jQuery para capturar o ID do plano e carregar o formul�rio
    $(document).ready(function () {
        // Quando o modal for aberto
        $('#myModal').on('show.bs.modal', function (event) {
            // Pega o bot�o que acionou o modal
            var button = $(event.relatedTarget);
            // Pega o ID do plano do atributo data-id
            var planoId = button.data('id');

            // Exibe o ID no console do navegador para verificar
            console.log("Plano ID: ", planoId);

            // Carrega o conte�do do plano no modal via AJAX
            $.ajax({
                url: 'plano1.jsp', // P�gina JSP que cont�m o formul�rio
                type: 'GET',
                data: {id: planoId}, // Envia o ID do plano como par�metro
                success: function (response) {
                    // Preenche o corpo do modal com o conte�do retornado
                    $('.modal-body').html(response);
                },
                error: function () {
                    $('.modal-body').html('Erro ao carregar o conte�do.');
                }
            });
        });
    });
</script>

