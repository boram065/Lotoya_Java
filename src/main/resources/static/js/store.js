document.addEventListener("DOMContentLoaded", function () {
    var players = document.querySelectorAll(".player");

    players.forEach(function (player) {
        player.addEventListener("click", function () {
            var playerId = player.getAttribute("data-player-id");
            window.location.href = "/buyPlayer/" + playerId;
        });
    });
});

//document.addEventListener("DOMContentLoaded", function() {
//    $.get("/", function(data) {
//        var players = JSON.parse(data);
//
//        // player-container에 선수 정보를 동적으로 추가
//        var container = document.getElementById("player-container");
//        players.forEach(function(player) {
//            addPlayerToContainer(container, player);
//        });
//    });
//});

//// 선수 정보를 받아 HTML 엘리먼트를 동적으로 생성하여 컨테이너에 추가하는 함수
//function addPlayerToContainer(container, player) {
//    // 새로운 선수를 추가하는 HTML 엘리먼트 생성
//    var playerDiv = document.createElement("div");
//    playerDiv.className = "player";
//
//    var playerImage = document.createElement("img");
//    playerImage.src = player.imgLink;
//    playerImage.alt = player.name;
//
//    var bottomName = document.createElement("div");
//    bottomName.className = "bottomName";
//
//    var playerName = document.createElement("h3");
//    playerName.textContent = player.name;
//
//    bottomName.appendChild(playerName);
//    playerDiv.appendChild(playerImage);
//    playerDiv.appendChild(bottomName);
//
//    container.appendChild(playerDiv);
//}

//// 새로운 플레이어를 추가하는 함수
//function addPlayer(image, name) {
//    var playerContainer = document.createElement("div");
//    playerContainer.className = "player";
//    playerContainer.onclick = function () {
//        // 해당 선수 페이지로 이동
//        window.location.href = "buyPlayer.html"; // 이동할 페이지 URL을 지정합니다.
//    };
//
//    var playerImage = document.createElement("img");
//    playerImage.src = image;
//    playerImage.alt = name;
//
//    var bottomName = document.createElement("div");
//    bottomName.className = "bottomName";
//    var playerName = document.createElement("h3");
//    playerName.textContent = name;
//
//    bottomName.appendChild(playerName);
//    playerContainer.appendChild(playerImage);
//    playerContainer.appendChild(bottomName);
//
//    var playerContainerDiv = document.getElementById("player-container");
//    playerContainerDiv.appendChild(playerContainer);
//}

//if (imgList || nameList || numList) {
//    // imgList, nameList, numList가 모두 null이 아닌 경우
//    // 원하는 작업을 수행하십시오.
//    for (var i = 0; i < imgList.length; i++) {
//        addPlayer(imgList[i], nameList[i], numList[i]);
//    }
//} else {
//    // imgList, nameList, numList 중 하나라도 null인 경우
//    // 원하는 작업을 수행하십시오.
//    console.log("하나 이상의 LIST가 null입니다.");
//}

//// 검색 기능 구현
//var searchInput = document.querySelector('.search .input');
//searchInput.addEventListener('input', function() {
//    var searchTerm = searchInput.value.toLowerCase();
//    var filtered;
//
//    if (searchTerm === '') {
//        filtered = song;
//    } else {
//        filtered = song.filter(function(item) {
//            return item.title.toLowerCase().includes(searchTerm);
//        });
//    }
//
//    renderFilteredSongs(filtered);
//});
//
//function renderFilteredSongs(filteredSongs) {
//    viewContainer.innerHTML = '';
//
//    filteredSongs.forEach(function(item) {
//        var songDiv = document.createElement('div');
//        songDiv.classList.add('view');
//
//        songDiv.addEventListener('click', function() {
//            if (currentAudio) {
//                currentAudio.pause();
//            }
//            var audio = new Audio(item.song);
//            audio.play();
//            currentAudio = audio;
//            songTitleDiv.textContent = item.title;
//        });
//
//        viewContainer.appendChild(songDiv);
//    });
//}