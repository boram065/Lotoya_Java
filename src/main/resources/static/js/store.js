var buttons = document.querySelectorAll('.button');

buttons.forEach(function(button) {
   button.addEventListener('click', function() {
       // 클릭 시 배경색 변경
       button.classList.toggle('clicked');
   });
});

$(document).ready(function() {
    // 서버로부터 데이터를 가져오기 위한 AJAX 요청
    $.get("/store-data", function(data) {
        var playerData = JSON.parse(data);
        var playerNameList = playerData.nameList;
        var playerImgList = playerData.imgList;

        // HTML 엘리먼트에 데이터 추가
        var playerContainer = $("#player-container");
        for (var i = 0; i < playerNameList.length; i++) {
            addPlayer(playerImgList[i], playerNameList[i]);
        }
    });
});

// 새로운 플레이어를 추가하는 함수
function addPlayer(image, name) {
    var playerContainer = document.createElement("div");
    playerContainer.className = "player";
    playerContainer.onclick = function () {
        // 해당 선수 페이지로 이동
        window.location.href = "buyPlayer.html"; // 이동할 페이지 URL을 지정합니다.
    };

    var playerImage = document.createElement("img");
    playerImage.src = image;
    playerImage.alt = name;

    var bottomName = document.createElement("div");
    bottomName.className = "bottomName";
    var playerName = document.createElement("h3");
    playerName.textContent = name;

    bottomName.appendChild(playerName);
    playerContainer.appendChild(playerImage);
    playerContainer.appendChild(bottomName);

    var playerContainerDiv = document.getElementById("player-container");
    playerContainerDiv.appendChild(playerContainer);
}

if (imgList || nameList || numList) {
    // imgList, nameList, numList가 모두 null이 아닌 경우
    // 원하는 작업을 수행하십시오.
    for (var i = 0; i < imgList.length; i++) {
        addPlayer(imgList[i], nameList[i], numList[i]);
    }
} else {
    // imgList, nameList, numList 중 하나라도 null인 경우
    // 원하는 작업을 수행하십시오.
    console.log("하나 이상의 LIST가 null입니다.");
}

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