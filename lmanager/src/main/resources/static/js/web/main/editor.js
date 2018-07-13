//这是另一种写法
$(function () {
    $(".down").click(function () {
        html2canvas($(".page-content")[0]).then(function (canvas) {
            var imgUri = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream"); // 获取生成的图片的url
            console.log(imgUri);
           // window.location.href = imgUri; // 下载图片
            $("#img_out").attr("src",imgUri)
        });


        //html2canvas($('.tongxingzheng_bg'), {
        //    onrendered: function (canvas) {
        //        var data = canvas.toDataURL("image/png");//生成的格式
        //        //data就是生成的base64码啦
        //        alert(data);
        //    }
        //});
    });
});