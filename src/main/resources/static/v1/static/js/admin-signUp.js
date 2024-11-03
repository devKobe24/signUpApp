// scripts.js
document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('adminSignupForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const adminName = document.getElementById('adminName').value;
        const adminNumber = document.getElementById('adminNumber').value;
        const adminEmail = document.getElementById('adminEmail').value;
        const adminPassword = document.getElementById('adminPassword').value;
        const confirmAdminPassword = document.getElementById('confirmAdminPassword').value;

        if (adminPassword != confirmAdminPassword) {
            alert('Passwords do not match!')
            return;
        }

        const registerData = {
            adminName: adminName,
            adminNumber: adminNumber,
            adminEmail: adminEmail,
            adminPassword: adminPassword,
            confirmAdminPassword: confirmAdminPassword
        };

        // Fetch API를 사용하여 서버로 POST 요청을 보냄
        fetch('http://localhost:8081/api/admin-register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registerData)
        })
            .then(response => {
                if (response.ok) {
                    alert("Admin 회원가입에 성공하였습니다!")
                    return; // 서버에서 JSON 데이터를 반환할 경우 처리
                }
                alert("Admin 회원가입에 실패하였습니다, 다시 시도해주세요.")
                throw new Error('Sign Up Failed!');
            })
            .then(data => {
                // 필요한 결우 리디렉션 또는 추가 작업 수행
                window.location.href = "/v1/main.html";
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Admin 회원가입에 실패했습니다.');
            });
    });
});