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

    ok.disabled = false;

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
    updateOkButtonState();
});

num.forEach(function (numElement) {
    numElement.addEventListener('input', function () {
        updateOkButtonState();
    });
});

// OK 버튼의 활성화 상태를 업데이트하는 함수
function updateOkButtonState() {
    var isAllScoresZero = Array.from(num).every(function (numElement) {
        return parseInt(numElement.textContent, 10) === 0;
    });
    ok.disabled = !isAllScoresZero || areAnyNumElementsNonZero();
}

function areAnyNumElementsNonZero() {
    return Array.from(num).some(function (numElement) {
        return parseInt(numElement.textContent, 10) !== 0;
    });
}

var newCoin = 0;
var good = 0, bad = 0;
function shouldRandomize(result) {
    newCoin = 0, good = 0, bad = 0;
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

    var totalCoins = good - bad;
    var message = "얻은 코인: " + good + "\n" +
                  "잃은 코인: " + bad + "\n" +
                  (totalCoins >= 0 ? totalCoins + "코인 얻었습니다" : Math.abs(totalCoins) + "코인 잃었습니다");
    alert(message);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/forecast-coins', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
       if (xhr.readyState === 4) {
           if (xhr.status === 200) {
               console.log("코인이 업데이트되었습니다.");
           } else {
               console.error("코인 업데이트 중 오류 발생:", xhr.statusText);
               console.log("코인 업데이트 중 오류가 발생했습니다.");
           }
       }
    };
    xhr.send('totalCoinsDifference=' + newCoin);
}