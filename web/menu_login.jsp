<%@page contentType="text/html" pageEncoding="UTF-8"%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/menu_login.css" type="text/css">



<div class="sidebar p-3">
    <h1 class="text-center"> <img src="imagens/tai.png" alt="Logo"></h1>
    <nav class="nav flex-column">
        <a class="nav-link" href="#"><i class="fas fa-home"></i> Início</a>
        <a class="nav-link" href="#"><i class="fas fa-user"></i> Clientes</a>
        <a class="nav-link" href="#"><i class="fas fa-file-invoice-dollar"></i> Fatura</a>
        <a class="nav-link" href="#"><i class="fas fa-box"></i> Produtos</a>
        <a class="nav-link" href="#"><i class="fas fa-calendar-alt"></i> Agenda</a>
        <a class="nav-link" href="#"><i class="fas fa-map-marked-alt"></i> Área de Cobertura</a>
        <a class="nav-link" href="#"><i class="fas fa-users"></i> Funcionários</a>
        <a class="nav-link" href="#"><i class="fas fa-tags"></i> Vendas</a>
        <a class="nav-link" href="#"><i class="fas fa-user-plus"></i> Possíveis Clientes</a>
    </nav>
</div>


<div class="header">
    <h1></h1>
    <div class="header-content">
        <div class="user-menu dropdown">
            <button class="btn btn-danger dropdown-toggle" type="button" id="userDropdown" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <i class="bi bi-people"></i>
                Nome do Usuário
            </button>
            <ul class="dropdown-menu dropdown-menu-lg-end" aria-labelledby="userDropdown">
                <li><a class="dropdown-item" href="#">Alterar Cadastro</a></li>
                <li><a class="dropdown-item" href="index.jsp">Sair</a></li>
            </ul>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
crossorigin="anonymous"></script>
