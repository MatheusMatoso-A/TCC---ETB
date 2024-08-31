<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="css/whatsmenu.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=New+Amsterdam&family=Oswald:wght@200..700&display=swap" rel="stylesheet">

 <div class="whatsapp-bar" onclick="toggleMenu()">
        <i class="bi bi-whatsapp"></i> FALE CONOSCO
    </div>
    <div class="whatsapp-menu" id="whatsappMenu">
        <h2>Central de Atendimento</h2>
        <div class="menu-body">
            <p>Fale diretamente com nossa equipe de atendimento!</p>
            <img src="imagens/call-center.gif" alt="Atendimento WhatsApp">
            <a href="https://wa.me/5561991844270" target="_blank">Clique aqui para iniciar uma conversa</a>
            <!-- Adicione mais links conforme necessário -->
        </div>
    </div>

    <script>
        function toggleMenu() {
            var menu = document.getElementById('whatsappMenu');
            if (menu.style.display === 'none' || menu.style.display === '') {
                menu.style.display = 'block';
            } else {
                menu.style.display = 'none';
            }
        }

        // Fechar o menu se clicar fora dele
        window.onclick = function (event) {
            var menu = document.getElementById('whatsappMenu');
            if (!event.target.matches('.whatsapp-bar') && !event.target.matches('.whatsapp-bar i')) {
                if (menu.style.display === 'block') {
                    menu.style.display = 'none';
                }
            }
        };

    </script>
