document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const loginData = {email, password};

    fetch('http://localhost:8081/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
        .then(response => {
            if (response.ok) {
                console.log("Response Status:", response.status);
                console.log("Response OK:", response.ok);

                alert("로그인에 성공하였습니다!");
                return response.json();
            } else {
                alert("로그인에 실패하였습니다!");
                throw new Error("Sign In Failed!");
            }
        })
        .then(data => {
            console.log("Response Data:", data);
            localStorage.setItem('jwtToken', data.token);
            window.location.href = "/v1/static/html/user-info.html";
        })
        .catch(error => {
            console.error('Error during login:', error);
            alert('Login Failed. Please check your credentials.');
        });
});