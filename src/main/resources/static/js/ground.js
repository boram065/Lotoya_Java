var buttons = document.querySelectorAll('.button');

buttons.forEach(function(button) {
    button.addEventListener('click', function() {
        // 클릭 시 배경색 변경
        button.classList.toggle('clicked');
    });
});