// login-scripts.js
document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;

    const loginData = {
        email: email,
        password: password
    };

    // Fetch API를 사용하여 서버로 POST 요청을 보냄
    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
        .then(response => {
            if (response.ok) {
                return response.json(); // 서버에서 JSON 데이터를 변환할 경우 처리
            }
            throw new Error('Login Failed');
        })
        .then(data => {
            console.log('Login Success:', data);
            alert('Login Successful!');
            // 필요한 경우 리디렉션 또는 추가 작업 수행
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Login Failed. Please check your credentials.');
        });
});