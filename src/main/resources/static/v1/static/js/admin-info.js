// admin-info.js
document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('jwtToken');
    if (!token) {
        alert('로그인이 필요합니다.');
        window.location.href = "/v1/main.html";
        return;
    }

    fetch('http://localhost:8081/api/admin-info', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })
        .then(response => {
            if (response.ok) {
                console.log(response); // 응답 객체를 확인하기 위해 추가 // 디버그 용 콘솔 로그
                return response.json(); // JSON 현태로 변환
            } else {
                throw new Error('Failed to fetch user data');
            }
        })
        .then(data => {
            console.log("Fetched user data: ", data); // 데이터 확인 // 디버그 용 콘솔 로그
            // 데이터가 성공적으로 로드되면 HTML에 삽입
            document.getElementById('admin_name').textContent = data.name;
            document.getElementById('admin_number').textContent = data.adminNumber;
            document.getElementById('admin_email').textContent = data.email;
        })
        .catch(error => {
            console.error('Error in Fetching user data:', error);
            alert('로그인 정보가 유효하지 않습니다.');
            window.location.href = "/v1/main.html"
        });

    document.getElementById('adminpageForm').addEventListener('submit', function (event) {
        event.preventDefault(); // 기본 폼 제출 동작 방지
        window.location.href = "/v1/main.html";
    });
});