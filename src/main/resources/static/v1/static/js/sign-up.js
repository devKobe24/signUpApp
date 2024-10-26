// scripts.js
document.getElementById('signupForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const userName = document.getElementById('userName').value;
    const userEmail = document.getElementById('userEmail').value;
    const userPassword = document.getElementById('userPassword').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (userPassword != confirmPassword) {
        alert('Passwords do not match!')
        return;
    }

    const registData = {
        userName: userName,
        userEmail: userEmail,
        userPassword: userPassword
    };

    // Fetch API를 사용하여 서버로 POST 요청을 보냄
    fetch('/api/regist', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registData)
    })
        .then(response => {
            if (response.ok) {
                return response.json(); // 서버에서 JSON 데이터를 반환할 경우 처리
            }
            throw new Error('Sign Up Failed!');
        })
        .then(data => {
            console.log('Sign Up Success: ', data);
            alert('Sign Up Successful!');
            // 필요한 결우 리디렉션 또는 추가 작업 수행
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Sign Up Failed.');
        });
});