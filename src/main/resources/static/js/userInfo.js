function setImage(event) {
    let reader = new FileReader();

    reader.onload = function (event) {
        $("#preview-image").attr('src', event.target.result)
    };

    reader.readAsDataURL(event.target.files[0]);
}

function deleteImage() {
    $("#preview-image").attr('src', "image/default-image.jpg")
}

function updateInfo() {
    let image = $("#input-image")[0].files[0]
    let name = $("#name").val()
    let password = $("#password").val()
    let passwordRepeat = $("#passwordRepeat").val()
    let blog = $("#blog").val()
    let github = $("#github").val()

    if(password !== passwordRepeat){
        alert("비밀번호와 비밀번호 확인이 같지 않습니다.")
        return;
    }
    let formData = new FormData()

    formData.append("image", image)
    formData.append("name", name)
    formData.append("password", password)
    formData.append("blog", blog)
    formData.append("github", github)

    console.log(localStorage.getItem('token'))

    $.ajax({
        type: "PUT",
        url: "/userInfo",
        data: formData,
        processData: false, // 필수
        contentType: false, // 필수
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        },
        success: function (response) { // 성공하면
            alert("회원정보 수정 완료!");
            window.location.href = 'index.html'
        }
    })
}