var plusBtns = document.querySelectorAll(".bi-plus");
var minusBtns = document.querySelectorAll(".bi-dash");
var ok = document.querySelector('.ok');
var replay = document.querySelector('.replay');
var containers = document.querySelectorAll(".fore-container");
var right = document.querySelectorAll(".scoreViewRight");
var left = document.querySelectorAll(".scoreViewLeft");
var num = document.querySelectorAll('.num h2');

plusBtns.forEach(function(btn) {
    btn.addEventListener('click', function() {
        var scores = btn.parentElement.previousElementSibling.querySelector("p");
        var score = parseInt(scores.textContent);
        scores.textContent = (score + 100).toString();
    });
});

minusBtns.forEach(function(btn) {
    btn.addEventListener('click', function() {
        var scores = btn.parentElement.previousElementSibling.querySelector("p");
        var score = parseInt(scores.textContent);
        if (score >= 100) {
            scores.textContent = (score - 100).toString();
        } else {
            scores.textContent = "0";
        }
    });
});

 // 야구팀 랜덤으로 돌리기
 function shuffleLogos() {
    var imgSources = [
        "/images/기아.png",
        "/images/두산.png",
        "/images/롯데.png",
        "/images/삼성.png",
        "/images/키움.png",
        "/images/한화.png",
        "/images/NC.png",
        "/images/SSG.png",
        "/images/LG.png",
        "/images/KT.png"
    ];

    var teamNames = ["kia", "doosan", "lotte", "samsung", "kiwoom", "hanwha", "nc", "ssg", "lg", "kt"];

    var images = document.querySelectorAll('.scoreViewLeft img, .scoreViewRight img');
    var usedIndexes = new Set();

    function getRandomIndex() {
        var randomIndex;
        do {
            randomIndex = Math.floor(Math.random() * teamNames.length);
        } while (usedIndexes.has(randomIndex));
        usedIndexes.add(randomIndex);
        return randomIndex;
    }

    images.forEach(img => {
        var randomIndex = getRandomIndex();
        var teamName = teamNames[randomIndex];

        // 기존 클래스명 삭제
        var existingClass = img.className;
        img.classList.remove(existingClass);
        // 새로운 클래스이름 넣기
        img.classList.add(teamName);

        img.setAttribute('src', imgSources[randomIndex]);
        img.classList.remove(img.classList.item(1));

        // 부모 div의 클래스 변경
        var parentDiv = img.closest('.logo');
        var parentClasses = Array.from(parentDiv.classList);
        parentClasses.forEach(className => {
            parentDiv.classList.remove(className);
        });
        parentDiv.classList.add('logo');
        parentDiv.classList.add(teamName + '-logo');
    });
 }

replay.addEventListener('click', function() {
    shuffleLogos();

    var scores = document.querySelectorAll('.score p');
    scores.forEach(function(score) {
        score.textContent = "0";
    });

    num.forEach(function(num) {
        num.textContent = "0";
    });

    window.scrollTo({ top: 0, behavior: 'smooth' });
});

var result = 0;
ok.addEventListener('click', function() {
    result = 1;
    containers.forEach(function(container) {
       var scoresRight = container.querySelector(".scoreShowRight .score p");
       var scoresLeft = container.querySelector(".scoreShowLeft .score p");
       var scoreLeftValue = parseInt(scoresLeft.innerText);
       var scoreRightValue = parseInt(scoresRight.innerText);

       if (scoreLeftValue !== 0 && scoreRightValue !== 0 && scoreLeftValue === scoreRightValue) {
           alert("코인을 다르게 설정해주세요!");
           result = 0;
           num.forEach(function(num) {
               num.textContent = "0";
           });
           return;
       }
    });
    shouldRandomize(result);
});

var newCoin = 0;
var good = 0, bad = 0;
function shouldRandomize(result) {
    newCoin = 0;
    if (result === 1) {
        num.forEach(function(numElement) {
           var randomNum = Math.floor(Math.random() * 10);
           numElement.textContent = randomNum.toString();
       });
       window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    containers.forEach(function(container) {
        var scoreRight = parseInt(container.querySelector(".scoreTable>.right>h2").innerText);
        var scoreLeft = parseInt(container.querySelector(".scoreTable>.left>h2").innerText);
        var scoresRight = container.querySelector(".scoreShowRight .score p");
        var scoresLeft = container.querySelector(".scoreShowLeft .score p");
        var scoreLeftValue = parseInt(scoresLeft.innerText);
        var scoreRightValue = parseInt(scoresRight.innerText);

        var addMoney = 0;
        if (scoreRight > scoreLeft) {
            addMoney = scoreRightValue - scoreLeftValue;
            good += scoreRightValue;
            bad += scoreLeftValue;
        } else if (scoreRight < scoreLeft) {
            addMoney = scoreLeftValue - scoreRightValue;
            good += scoreLeftValue;
            bad += scoreRightValue;
        } else {
            addMoney = 0;
        }

        var currentUserCoinElement = document.querySelector('.coin .money h3');
        var currentUserCoin = parseInt(currentUserCoinElement.innerText);
        newCoin = currentUserCoin + addMoney;
        currentUserCoinElement.textContent = newCoin.toString();
    });

    var userId = getLoggedInUserId();
    if (userId) {
        console.log("사용자 id : ", userId)
        updateCoinValue(userId, newCoin);
    } else {
        console.error('Failed to get user ID');
    }

    var totalCoins = good - bad;
    var message = "얻은 코인: " + good + "\n" +
                  "잃은 코인: " + bad + "\n" +
                  (totalCoins >= 0 ? totalCoins + "코인 얻었습니다" : Math.abs(totalCoins) + "코인 잃었습니다");
    alert(message);
}

//function getLoggedInUserId() {
//    var loggedInUser = [[${loggedInUser}]];
//    if (loggedInUser) {
//        return loggedInUser.id;
//    } else {
//        // 로그인되지 않은 경우에 대한 처리
//        console.error('User is not logged in');
//        return null;
//    }
//}

function updateCoinValue(userId, newCoinValue) {
    fetch("/forecast", {
        method: 'POST',
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);  // 서버 응답을 콘솔에 출력
    })
    .catch(error => console.error('Error updating coin value:', error));
}