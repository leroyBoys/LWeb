(function () {

    var lqForm= $("#adminForm").lqform({
        "before_submit":function (formData) {
            if($cronBlob != null){
                var img_name=$("#head_pic").val()
                formData.append("file",$cronBlob,img_name)
            }
            return formData;
        },
        "submit_callback":function (data) {
            console.log(data,from);
            if(from == "self"){
                lqtip.alert("保存成功,请刷新页面!")
            }else {
                window.location.href=document.referrer;
            }
        }
    });

    var from = "self";
    function createNew() {
        from = "other";
        $("input[type=password]").parent().parent().remove();
        $(".form-group.head_editor").remove();
    }

    function createSelf() {//修改自己的资料
        var id = $("#admin_id").val();
        $("input[name='id']").val(id);
        $.post("/web/admin/get?id="+id,function (data) {
            if(data==null){
                return
            }
            lqForm.update(data);
            $("input[type='password']").val("");
        })
    }

    function modify(id) {
        createNew();
        $.post("/web/admin/get?id="+id,function (data) {
            if(data==null){
                return
            }
            lqForm.update(data);
        })
    }


    var id = UrlParamters()['id'];
    if(id == undefined){
        //
        var newStr = UrlParamters()['self'];
        if(newStr == undefined){
            createNew();
            return
        }
        createSelf();
        return
    }
    modify(id);

})(Window)

var $image = $('#sourceImage');
var $cronBlob = null;
var $cronBlob_old = null;
var $options ={
    aspectRatio: 4/3,
    dragMode:'move',
    zoomOnWheel:false,
    movable:false,
    'preview': '#simpleCropperPreview >.img-preview',
    'responsive': !![]
};

$("#head_pic").on("change",function (e) {
    var file = e.target.files[0] || e.dataTransfer.files[0];
    if(!file){
        return
    }
    var _name = $(this).val();
    var _fileName = _name.substring(_name.lastIndexOf(".") + 1).toLowerCase();
    if (_fileName !== "png" && _fileName !== "jpg") {
        lqtip.alert("上传图片格式不正确，请重新上传");
        return
    }

    var reader = new FileReader();
    reader.onload = function () {
        $cronBlob = file;
        $cronBlob_old = file;
        $image.cropper('destroy').attr('src', this.result).cropper($options);
    }
    reader.readAsDataURL(file);

});


$(".savecropper").click(function () {
    var cropper = $image.data('cropper');
    if (!cropper) {
        return;
    }
    cropper.getCroppedCanvas().toBlob(function(blob){
        $cronBlob = blob;

        var reader = new FileReader();
        reader.onload = function () {
            $image.cropper('replace',this.result,true );
        }
        reader.readAsDataURL($cronBlob);

        cropper.clear();
        $(".cropper-preview .img-preview").hide();
    });
})


$(".begincropper").click(function () {

    var cropper = $image.data('cropper');
    if (!cropper) {
        $image.cropper($options);
        return;
    }
    cropper.crop();
    $(".cropper-preview .img-preview").show();
})

$(".resetcropper").click(function () {

    var cropper = $image.data('cropper');
    if (!cropper) {
        return;
    }else if($cronBlob_old == null){
        cropper.reset();
        return
    }
    var reader = new FileReader();
    reader.onload = function () {
        $image.cropper('replace',this.result,true );
    }
    reader.readAsDataURL($cronBlob_old);
})