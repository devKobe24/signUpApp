document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const userEmail = document.getElementById('userEmail').value;
    const userPassword = document.getElementById('userPassword').value;

    const loginData = { userEmail, userPassword };

    fetch('http://localhost:8081/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
        .then(response => {
            if (response.ok) {
                // console.log("Response Status:", response.status); // 디버그 용 콘솔 로그
                // console.log("Response OK:", response.ok); // 디버그 용 콘솔 로그
                alert("로그인에 성공하였습니다!");
                return response.json();
            } else {
                alert("로그인에 실패하였습니다!");
                throw new Error("Sign In Failed!");
            }
        })
        .then(data => {
            // console.log("Response Data:", data); // 디버그 용 콘솔 로그
            localStorage.setItem('jwtToken', data.token);
            window.location.href = "/v1/static/html/user-info.html";
        })
        .catch(error => {
            console.error('Error during login:', error);
            alert('Login Failed. Please check your credentials.');
        });
});