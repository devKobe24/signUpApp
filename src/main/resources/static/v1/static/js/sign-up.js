// scripts.js
document.getElementById('signupForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (password !== confirmPassword) {
        alert('Passwords do not match!');
        return;
    }

    // Example of form data processing (you can replace it with actual form submission)
    const formData = {
        username: document.getElementById('username').value,
        email: document.getElementById('email').value,
        password: password
    };

    console.log('Form Data:', formData);
    alert('Signup Successful!');
});