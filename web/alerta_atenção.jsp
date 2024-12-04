<style>
    * {
        margin: 0;
        padding: 0;
    }

    a {
        text-decoration: none !important;
        color: #000;
    }

    a:hover {
        color: #ffff !important;
    }
    .ah{
        margin-top: 100px;
    }
</style>

<%
    // Verifica se existe uma mensagem na sessão
    String mensagemAlerta = (String) session.getAttribute("mensagemAlerta");
    if (mensagemAlerta != null) {
        // Se houver uma mensagem, exibe o toast e remove a mensagem da sessão
        session.removeAttribute("mensagemAlerta");
%>

<svg xmlns="http://www.w3.org/2000/svg" class="d-none">
    <symbol id="exclamation-triangle-fill" viewBox="0 0 16 16" >
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
    </symbol>
</svg>

<div id="alerta" class="ah container text-center alert alert-warning d-flex align-items-center" role="alert">
    <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
    <button type="button" class="btn-close" aria-label="Close" onclick="fecharAlerta()" style="position: absolute; top: 10px; right: 10px;"></button>
    <p class="text-center">
        <%= mensagemAlerta%>
    </p>

</div>
<%
    }
%>

<script>
    // Função para mostrar o alerta
    function mostrarAlerta() {
        document.getElementById('alerta').classList.remove('d-none');
    }

    // Função para fechar o alerta
    function fecharAlerta() {
        document.getElementById('alerta').classList.add('d-none');
        // Redireciona para a página principal após fechar o alerta
        window.location.href = "login.jsp";
    }
    ;
</script>
