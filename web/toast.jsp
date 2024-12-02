
<%
    // Verifica se existe uma mensagem na sessão
    String mensagemToast = (String) session.getAttribute("mensagemToast");
    if (mensagemToast != null) {
        // Se houver uma mensagem, exibe o toast e remove a mensagem da sessão
        session.removeAttribute("mensagemToast");
%>

<div class="toast align-items-center text-bg-success border-0" id="toast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="d-flex">
        <div class="toast-body">
            <%= mensagemToast %>  <!-- Exibe a mensagem passada pelo servlet -->
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
</div>

<% }%>


<script>
    document.addEventListener('DOMContentLoaded', function () {
        var toastElement = document.getElementById('toast');
        var toast = new bootstrap.Toast(toastElement, {
            delay: 5000 // 5000ms = 5 segundos
        });
        toast.show();
    });
</script>

