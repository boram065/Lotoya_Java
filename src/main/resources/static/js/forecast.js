document.addEventListener("DOMContentLoaded", function() {
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

    ok.addEventListener('click', function() {
        num.forEach(function(num) {
          var randomNum = Math.floor(Math.random() * 10);
          num.textContent = randomNum.toString();
        });
        window.scrollTo({ top: 0, behavior: 'smooth' });
    });
});