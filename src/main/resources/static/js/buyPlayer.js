document.addEventListener("DOMContentLoaded", function () {
    var wishButton = document.querySelector('.wish');
    var btnBuy = document.querySelector(".btnBasket");

    wishButton.addEventListener('click', function() {
      var heartIcon = document.querySelector('.bi-suit-heart-fill');
      heartIcon.classList.toggle('wish-click');
    });

    btnBuy.addEventListener('click', function() {
        btnBuy.classList.toggle('btnBasket-click');
    });

//    var buyPlayerButton = document.getElementById("btnBuy");
//    buyPlayerButton.addEventListener("click", function () {
//        btnBuy.classList.add('btnBasket-click');
//        var playerId = buyPlayerButton.getAttribute("data-player-id");
//
//        var formData = new FormData();
//        formData.append('playerId', playerId);
//
//        fetch("/buyPlayer", {
//            method: 'POST',
//            headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded',
//            },
//            body: formData
//        })
//        .then(response => response.text())
//        .then(data => {
//            console.log(data);
//        })
//        .catch(error => console.error('Error:', error));
//    });
});