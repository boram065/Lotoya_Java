// 아이디 input 요소와 에러 메시지 요소를 가져옵니다.
var idInput = document.querySelector('.id_input');
var pwInput = document.querySelector('.pw_input');
var emailInput = document.querySelector('.email_input');
var domainInput = document.querySelector('.domain_input');

var errorMessage = document.getElementById('ErrorMessage');

// 에러 메시지를 초기화합니다.
errorMessage.style.visibility = 'hidden';

// 회원가입 버튼 요소를 가져옵니다.
var joinButton = document.querySelector('.join_container button');

// 회원가입 버튼 클릭 시 이벤트를 처리합니다.
joinButton.addEventListener('click', function(event) {

    var hasError = false;

    // 입력 상태를 확인하고 에러 메시지를 출력합니다.
    if (idInput.value.trim() === '') {
        errorMessage.textContent = '아이디를 입력하세요';
        hasError = true;

    } else if (pwInput.value.trim() === '') {
        errorMessage.textContent = '비밀번호를 입력하세요';
        hasError = true;

    } else if (emailInput.value.trim() === '' || domainInput.value.trim() === '') {
        errorMessage.textContent = '이메일을 입력하세요';
        hasError = true;
    }

    // id, pw, email을 다 입력했을 때 로그인 화면으로 넘어가기
    // 그렇지 않으면 에러메시지를 보여주고 페이지 이동이 되지 않기
    if(hasError == false) {
        window.location.href = 'login.html';
    } else {
        errorMessage.style.visibility = 'visible';
        event.preventDefault();
    }
});

// 도메인 직접 입력 or domain option 선택
var domain = document.querySelector('.domain')

// select 옵션 변경 시
domain.addEventListener('change', (e) => {
  // option에 있는 도메인 선택 시
  if(e.target.value !== "self") {
    // 선택한 도메인을 input에 입력하고 disabled
    domainInput.value = e.target.value
    domainInput.disabled = true
  } else { // 직접 입력 시
    // input 내용 초기화 & 입력 가능하도록 변경
    domainInput.value = ""
    domainInput.disabled = false
    domainInput.focus(); // 직접 입력란에 커서 포커스
  }
});