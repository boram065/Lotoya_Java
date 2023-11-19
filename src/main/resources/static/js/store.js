document.addEventListener("DOMContentLoaded", function () {
    var buttons = document.querySelectorAll(".button.filter");
    var players = document.querySelectorAll(".player");

    players.forEach(function (player) {
        player.addEventListener("click", function () {
            var playerId = player.getAttribute("data-player-id");
            // 확인: 콘솔에 playerId를 출력하여 값이 존재하는지 확인합니다.
            console.log("Player ID:", playerId);
            // 확인: buyPlayer 페이지로 이동하는 부분이 정상적으로 동작하는지 확인합니다.
            window.location.href = "/buyPlayer/" + playerId;
        });
    });

    buttons.forEach(function(button) {
        button.addEventListener("click", function() {
            button.classList.toggle("clicked");
            var clickedButtons = Array.from(buttons).filter(b => b.classList.contains("clicked"));
            var club = clickedButtons
                .filter(b => b.classList.contains("club"))
                .map(b => b.textContent.trim())
                .join(",");

            var position = clickedButtons
                .filter(b => b.classList.contains("position"))
                .map(b => b.textContent.trim())
                .join(",");

            if (position === "") {
                position = null;
            } else if (club === ""){
                club = null;
            }

            if (!position && !club) {
                filterPlayers(null, null);
                return;
            }

            filterPlayers(club, position);
        });
    });

    var searchInput = document.querySelector(".inputSearch");
    searchInput.addEventListener("input", function () {
        var searchTerm = searchInput.value;
        filterPlayersBySearch(searchTerm);
    });
});

// 필터링된 선수들을 가져오는 함수
function filterPlayers(club, position) {
    var url = '/store';
    var requestBody = {
        club: club,
        position: position
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
        .then(response => response.json())
        .then(data => {
            updatePlayerList(data);
        })
        .catch(error => console.error('Error:', error));
}

function updatePlayerList(data) {
    var playerContainer = document.getElementById('player-container');
    playerContainer.innerHTML = '';

    data.forEach(function (player) {
        var playerDiv = document.createElement('div');
        playerDiv.classList.add('player');
        playerDiv.setAttribute('data-player-id', player.id);

        var img = document.createElement('img');
        img.setAttribute('src', player.imgLink);
        img.setAttribute('alt', player.name);

        var bottomNameDiv = document.createElement('div');
        bottomNameDiv.classList.add('bottomName');
        var h4 = document.createElement('h4');
        h4.textContent = player.backNum + '. ' + player.name;

        bottomNameDiv.appendChild(h4);
        playerDiv.appendChild(img);
        playerDiv.appendChild(bottomNameDiv);

        playerContainer.appendChild(playerDiv);
    });
}

// 검색 필터링
function filterPlayersBySearch(searchTerm) {
    console.log(searchTerm);
    var allPlayers = document.querySelectorAll(".player");
    allPlayers.forEach(function (player) {
        var playerNameElement = player.querySelector(".bottomName h4");

        if (!searchTerm || searchTerm === "") {
            player.style.display = "flex";
            return;
        }

        if (playerNameElement) {
            var playerName = playerNameElement.dataset.playerName;
            var playerNumber = playerNameElement.dataset.playerNumber;

            if (playerName.includes(searchTerm) || playerNumber.includes(searchTerm)) {
                player.style.display = "flex";
            } else {
                player.style.display = "none";
            }
        }
    });
}