<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<script>
    // Referência ao carrossel
    var carouselElement = document.getElementById('ads');
    var carousel = new bootstrap.Carousel(carouselElement);
          
    // Pausar o carousel ao passar o mouse nos cartões
    var cards = document.querySelectorAll('.carousel-card');
          
    cards.forEach(function(card) {
card.addEventListener('mouseenter', function () {
        carousel.pause(); // Pausa o carousel
      });
          
      card.addEventListener('mouseleave', function() {
        carousel.cycle(); // Reinicia o carousel
      });
    });
</script>

