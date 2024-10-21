// login-scripts.js
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;

    // Example of form data processing (you can replace it with actual form submission)
    const loginData = {
        email: email,
        password: password
    };

    console.log('Login Data:', loginData);
    alert('Login Successful!');
});