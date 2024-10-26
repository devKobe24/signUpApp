// scripts.js
document.getElementById('adminSignupForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const adminName = document.getElementById('adminName').value;
    const adminNumber = document.getElementById('adminNumber').value;
    const adminEmail = document.getElementById('adminEmail').value;
    const adminPassword = document.getElementById('adminPassword').value;
    const confirmAdminPassword = document.getElementById('confirm-adminPassword').value;

    if (adminPassword != confirmAdminPassword) {
        alert('Passwords do not match!')
        return;
    }

    const registerData = {
        adminName: adminName,
        adminNumber: adminNumber,
        adminEmail: adminEmail,
        adminPassword: adminPassword
    };

    // Fetch API를 사용하여 서버로 POST 요청을 보냄
    fetch('/api/regist/admin', {
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