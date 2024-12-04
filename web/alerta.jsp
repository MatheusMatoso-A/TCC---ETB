<style>
    * {
        margin: 0;
        padding: 0;
    }

    a {
        text-decoration: none ;
        color: #000;
    }

    a:hover {
        color: #ffff ;
    }

</style>

<%
    // Verifica se existe uma mensagem na sessão
    String mensagemAlerta = (String) session.getAttribute("mensagemAlerta");
    if (mensagemAlerta != null) {
        // Se houver uma mensagem, exibe o toast e remove a mensagem da sessão
        session.removeAttribute("mensagemAlerta");
%>

<div id="alerta" class="container text-center alert alert-success" role="alert">
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width="16"
        height="16"
        fill="currentColor"
        class="bi bi-check-circle-fill"
        viewBox="0 0 16 16"
        >
        <path
            d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"
            />
    </svg>
    <button type="button" class="btn-close" aria-label="Close" onclick="fecharAlerta()" style="position: absolute; top: 10px; right: 10px;"></button>
    <p class="text-center">
        <%= mensagemAlerta%>
    </p>

    <div>
        <button  type="button" class="btn btn-outline-success" onclick="fecharAlerta()">Continuar</button>
    </div>
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
        window.location.href = "index.jsp";
    };

  
</script>


