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