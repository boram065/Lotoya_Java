// 아이디 input 요소와 에러 메시지 요소를 가져옵니다.
var pwInput = document.querySelector('.pw_input');
var emailInput = document.querySelector('.email_input');

// 회원가입 버튼 요소를 가져옵니다.
var joinButton = document.querySelector('.join_container .button');

// 회원가입 버튼 클릭 시 이벤트를 처리합니다.
joinButton.addEventListener('click', function(event) {

    if (pwInput.value.trim() === '') {
        alert('비밀번호를 입력하세요');

    } else if (emailInput.value.trim() === '' || domainInput.value.trim() === '') {
        alert('이메일을 입력하세요');
    }

    return true;
});