document.addEventListener("DOMContentLoaded", function () {
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
            if (data === 'alreadyBought') {
                alert("이미 구매한 선수입니다");
                return;
            } else if (data === 'noMoney'){
                alert("돈이 부족합니다");
                return;
            }
            var dataArray = data.split("|");
            alert("선수 구매가 완료되었습니다");
            console.log("선수 구매 성공");

            var updatedCoin = dataArray[0];
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