
$(".panel .item_button").each(function () {
    var img = $(this).parent().parent().find(".caption-figure");

    var iframe =  $("<iframe width='500px' height='200px'  style='position:absolute;z-index:-1;visibility: hidden'  frameborder='no' marginheight='0' marginwidth='0' allowTransparency='true'></iframe>");
    iframe.prependTo('body');

    iframe.attr("src", $(this).attr("lq-url")+$(this).attr("lq-value"));

    iframe.load( function() {
        var target = $(this).contents().find(".panel-body")[0];
        html2canvas(target).then(function (canvas) {
            var imgUri = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream"); // 获取生成的图片的url
            img.attr("src",imgUri)
        });
    });
})

$(".show_button").click(function () {
    var img = $(this).find(".caption-figure");
    $("#item_image_show img").attr("src",img.attr("src"));
})
