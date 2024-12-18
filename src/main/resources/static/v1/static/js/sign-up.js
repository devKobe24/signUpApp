// scripts.js
document.addEventListener('DOMContentLoaded', function (){
    document.getElementById('signupForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const userName = document.getElementById('userName').value;
        const userEmail = document.getElementById('userEmail').value;
        const userPassword = document.getElementById('userPassword').value;
        const confirmUserPassword = document.getElementById('confirmUserPassword').value;

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

        // registerData 객체를 출력하여 userPassword와 confirmUserPassword 필드가 제대로 들어가는지 확인하니 위한 로그
        // console.log("Register Data:", registerData); // 디버그 용 콘솔 로그

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
                    // console.log("Response Status:", response.status); // 디버그 용 콘솔 로그
                    // console.log("Response OK:", response.ok); // 디버그 용 콘솔 로그

                    alert("회원가입에 성공하였습니다!");
                    return;
                } else {
                    alert("회원가입에 실패했습니다, 다시 시도해주세요.")
                    throw new Error('Sign Up Failed!');
                }
            })
            .then(data => {
                // console.log("Response Data:", data); // 디버그 용 콘솔 로그
                window.location.href = "/v1/main.html";
            })
            .catch(error => {
                console.error('Error:', error);
                alert("회원가입에 실패했습니다.")
            });
    });
});
