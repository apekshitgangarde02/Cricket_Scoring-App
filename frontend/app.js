const apiBaseUrl = "http://localhost:8080/api/auth"; // your backend URL

async function registerUser() {
    const username = document.getElementById('register-username').value;
    const password = document.getElementById('register-password').value;

    const response = await fetch(`${apiBaseUrl}/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    });

    const message = document.getElementById('message');
    if (response.ok) {
        message.innerText = "✅ Registration Successful!";
        message.style.color = "green";
    } else {
        message.innerText = "❌ Registration Failed! (Username might already exist)";
        message.style.color = "red";
    }
}

// Select the message element
const messageElement = document.getElementById('message');

async function loginUser() {
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    const response = await fetch(`${apiBaseUrl}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    });

    if (response.ok) {
        const message = await response.text();
        messageElement.innerText = message;
        messageElement.style.color = "green";
        window.location.href = 'series.html';  // Redirect after successful login
    } else if (response.status === 401) {
        const errorMessage = await response.text();
        messageElement.innerText = errorMessage;
        messageElement.style.color = "red";
    } else {
        messageElement.innerText = "Something went wrong!";
        messageElement.style.color = "red";
    }
}




