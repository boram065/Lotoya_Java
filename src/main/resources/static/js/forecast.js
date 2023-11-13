document.addEventListener("DOMContentLoaded", function() {
    var plusBtns = document.querySelectorAll(".bi-plus");
    var minusBtns = document.querySelectorAll(".bi-dash");
    var ok = document.querySelector('.ok');
    var replay = document.querySelector('.replay');
    var containers = document.querySelectorAll(".fore-container");

    plusBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {
            var scoreElement = btn.parentElement.previousElementSibling.querySelector("p");
            var score = parseInt(scoreElement.textContent);
            scoreElement.textContent = (score + 100).toString();
        });
    });

    minusBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {
            var scoreElement = btn.parentElement.previousElementSibling.querySelector("p");
            var score = parseInt(scoreElement.textContent);
            if (score >= 100) {
                scoreElement.textContent = (score - 100).toString();
            } else {
                scoreElement.textContent = "0";
            }
        });
    });

    // 야구팀 랜덤으로 돌리기
    function shuffle(array) {
        for (let i = array.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [array[i], array[j]] = [array[j], array[i]];
        }
    }

    replay.addEventListener('click', function() {
        var num = document.querySelectorAll('.num h2');
        num.forEach(function(num) {
            num.textContent = "0";
        });

        var containerArray = Array.from(containers);
        shuffle(containerArray);

        containerArray.forEach(function(container, index) {
            container.style.order = index + 1;
        });
    });

    ok.addEventListener('click', function() {
        var num = document.querySelectorAll('.num h2');
        num.forEach(function(num) {
          var randomNum = Math.floor(Math.random() * 10);
          num.textContent = randomNum.toString();
        });
    });
});