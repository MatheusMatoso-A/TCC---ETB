<div class="col-md-4 ">
    <div class="card text-dark border-dark mb-3 carousel-card">
        <div class="card-header text-center bg-danger"><h4> <strong> Plano ${p.nome} </strong></h4></div>
        <div class="card-body text-center">
            <h5 class="card-title">${p.velocidade} de Velocidade </h5>
            <p class="card-text text-center">Conex�o ultra r�pida para todos os usos</p>
            <p class="card-text text-center">Download ilimitado</p>
            <p class="card-text text-center">Suporte 24/7</p>
            <p class="card-text text-center"><strong>R$ <fmt:formatNumber value="${p.valor}" pattern="#,##0.00" />/m�s</strong></p>
            <button id="openModal" class="btn btn-red vantagem-img" data-bs-toggle="modal"
                    data-bs-target="#myModal" data-id="${p.id}">
                Assinar
            </button>
        </div>
    </div>
</div>

<link rel="stylesheet" type="text/css" href="css/reset.css" />
<script src="js/planos.js" defer=""></script>