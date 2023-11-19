document.addEventListener("DOMContentLoaded", function () {
    var wishButton = document.querySelector('.wish');
    var btnBuy = document.querySelector(".btnBasket");

    wishButton.addEventListener('click', function() {
      var heartIcon = document.querySelector('.bi-suit-heart-fill');
      heartIcon.classList.toggle('wish-click');
    });

     btnBuy.addEventListener('click', async function () {
        btnBuy.classList.toggle('btnBasket-click');

        var playerId = btnBuy.getAttribute("data-player-id");

        var playerIdValue = playerId ? parseInt(playerId) : 0;
        var requestData = {
            playerId: playerIdValue
        };

        try {
            const response = await fetch("/buyPlayer/" + playerId, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            const data = await response.text();
            var dataArray = data.split("|");
            alert(dataArray[0]);
            console.log(dataArray[0]);

            var updatedCoin = dataArray[0].replace("/[^0-9]/g", "");
            document.querySelector('.money h3').innerText = updatedCoin;
        } catch (error) {
            console.error('Error:', error);
        }
    });

    async function isPlayerBought(playerId) {
        try {
            const response = await fetch("/buyPlayer/" + playerId, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            const data = await response.text();
            return data === 'true';
        } catch (error) {
            console.error('Error:', error);
            return false;
        }
    }
});