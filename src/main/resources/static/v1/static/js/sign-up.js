// scripts.js
document.addEventListener('DOMContentLoaded', function (){
    document.getElementById('signupForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const userName = document.getElementById('username').value;
        const userEmail = document.getElementById('email').value;
        const userPassword = document.getElementById('password').value;
        const confirmUserPassword = document.getElementById('confirm-userPassword').value;

        if (userPassword != confirmUserPassword) {
            alert("Passwords do not match!")
            return;
        }

        const registerData = {
            userName: userName,
            userEmail: userEmail,
            userPassword: userPassword,
            confirmUserPassword: confirmUserPassword
        };

        // Fetch API를 사용하여 서버로 POST 요청을 보냄
        fetch('http://localhost:8081/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registerData)
        })
            .then(response => {
                if (response.ok) {
                    console.log("Response Status:", response.status);
                    console.log("Response OK:", response.ok);

                    alert("회원가입에 성공하였습니다!");
                    return;
                } else {
                    alert("회원가입에 실패했습니다, 다시 시도해주세요.")
                    throw new Error('Sign Up Failed!');
                }
            })
            .then(data => {
                console.log("Response Data:", data);
                window.location.href = "/v1/main.html";
            })
            .catch(error => {
                console.error('Error:', error);
                alert("회원가입에 실패했습니다.")
            });
    });
});
