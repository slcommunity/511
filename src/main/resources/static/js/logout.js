function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userId')
    sessionStorage.removeItem('userId');
    alert('로그아웃을 완료했습니다.');
    window.location.href = '/';
}