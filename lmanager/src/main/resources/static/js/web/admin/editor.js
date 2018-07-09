
(function () {

    function createNew() {
        $("input[type=password]").parent().parent().remove();
    }

    function createSelf() {//修改自己的资料

    }

    function modify(id) {//修改自己的资料
        $("input[type=password]").remove();
    }


    var id = UrlParamters()['id'];

    if(id == undefined){
        //
        var newStr = UrlParamters()['new'];
        if(newStr == undefined || newStr==1){
            createNew();
            return
        }
        createSelf();
        return
    }
    modify(id);
})(Window)

$image = $('#sourceImage');
$image.cropper({
    'preview': '#simpleCropperPreview >.img-preview',
    'responsive': !![]
});

$("#head_pic").on("change",function (e) {
    var file = e.target.files[0] || e.dataTransfer.files[0];
    var _name = $(this).val();
    var _fileName = _name.substring(_name.lastIndexOf(".") + 1).toLowerCase();
    if (_fileName !== "png" && _fileName !== "jpg") {
        lqtip.alert("上传图片格式不正确，请重新上传");
        return
    }
/*

    var URL = window.URL || window.webkitURL;
    var files = this.files;
    var file;

    if (!$image.data('cropper')) {
        return;
    }

    if (files && files.length) {
        file = files[0];

        if (/^image\/\w+$/.test(file.type)) {

            if (uploadedImageURL) {
                URL.revokeObjectURL(uploadedImageURL);
            }

            var uploadedImageURL = URL.createObjectURL(file);
            $image.cropper('destroy').attr('src', uploadedImageURL).cropper({ aspectRatio: 16 / 9,});

        } else {
            window.alert('Please choose an image file.');
        }
    }
*/


    var reader = new FileReader();
    reader.onload = function () {
        $("#sourceImage").cropper('destroy').attr('src', this.result).cropper({
            'preview': '#simpleCropperPreview >.img-preview',
            'responsive': !![]
        });

      //  $("#simpleCropper").html('  <img id="sourceImage" src="'+this.result+'" alt="headimage">');
    /*    $('#sourceImage').cropper({
            'preview': '#simpleCropperPreview >.img-preview',
            'responsive': !![]
        });*/
    }

    reader.readAsDataURL(file);

});

