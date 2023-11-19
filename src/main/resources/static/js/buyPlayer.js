document.addEventListener("DOMContentLoaded", function () {
     btnBuy.addEventListener('click', async function () {
        btnBuy.classList.toggle('btnBasket-click');

        var playerId = btnBuy.getAttribute("data-player-id");

        var playerIdValue = playerId ? parseInt(playerId) : 0;
        var requestData = {
            playerId: playerIdValue
        };

//         wishButton.addEventListener('click', function() {
//           var heartIcon = document.querySelector('.bi-suit-heart-fill');
//           heartIcon.classList.toggle('wish-click');
//           if(index < 1) { // 1이 아니면 안눌린거니까 1로 바꿈
//               index = 1;
//           }else{ // 1이면 눌린상태니까 index 0으로 바꾸고 removewishlist 실행
//               index = 0;
//           }
//         });

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

//async function addwishlist() {
//    try{
//        var playerId = btnBuy.getAttribute("data-player-id");
//
//        var playerIdValue = playerId ? parseInt(playerId) : 0;
//        var requestData = {
//            playerId: playerIdValue
//        };
//
//        if(index < 1) {
//            var heartIcon = document.querySelector('.bi-suit-heart-fill');
//            heartIcon.classList.toggle('wish-click');
//            const response = await fetch("/buyPlayer/addwishlist/" + playerId, {
//                method: 'POST',
//                headers: {
//                    'Content-Type': 'application/json',
//                },
//            });
//            index = 1;
//            const data = await response.text();
//            return data === 'true';
//        }else{
//            var heartIcon = document.querySelector('.bi-suit-heart-fill');
//            heartIcon.classList.toggle('wish-click');
//            const response = await fetch ("/buyPlayer/removeWishlist/" + playerId, {
//                method: 'POST',
//                headers: {
//                    'Content-Type': 'application/json',
//                },
//            });
//            index = 0;
//            const data = await response.text();
//            return data === 'true';
//        }
//    }catch (error) {
//        console.error('Error:', error);
//        return false;
//    }
//}