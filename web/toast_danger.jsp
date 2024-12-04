<style>
    /* Anima��o personalizada para o toast */
    .toast {
        animation: slideInFromRight 1s ease-out, fadeOut 1s 4s forwards;
    }

    /* Anima��o de entrada (deslizando da direita) */
    @keyframes slideInFromRight {
        0% {
            transform: translateX(100%);
            opacity: 0;
        }
        100% {
            transform: translateX(0);
            opacity: 1;
        }
    }

    /* Anima��o de sa�da (desaparecendo) */
    @keyframes fadeOut {
        0% {
            opacity: 1;
        }
        100% {
            opacity: 0;
            transform: translateX(100%);
        }
    }
</style>
<%
    // Verifica se existe uma mensagem na sess�o
    String mensagemDangerToast = (String) session.getAttribute("mensagemDangerToast");
    if (mensagemDangerToast != null) {
        // Se houver uma mensagem, exibe o toast e remove a mensagem da sess�o
        session.removeAttribute("mensagemDangerToast");
%>

<div class="toast position-fixed top-0 end-0 m-3" id="toast-danger" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header bg-danger text-white">
        <strong class="me-auto">Notifica��o</strong>
        <small>Agora</small>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body bg-danger text-white">
        <%= mensagemDangerToast%>  <!-- Exibe a mensagem passada pelo servlet -->
    </div>
</div>
</div>

<% }%>


<script>
    document.addEventListener('DOMContentLoaded', function () {
        var toastElement = document.getElementById('toast-danger');
        var toast = new bootstrap.Toast(toastElement, {
            delay: 10000 // 5000ms = 5 segundos
        });
        toast.show();
    });
</script>
