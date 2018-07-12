
$(document).on("click",".modifyButtonGroup .modify",function () {
    var id = $(this).parent().attr("lq-value");
    console.log("id:"+id)
    window.location.href = $(this).attr("data-url")+"?id="+id;
})

$(document).on("click",".modifyButtonGroup .del",function () {
    var id = $(this).parent().attr("lq-value");
    var url = $(this).attr("data-url");
    lqtip.confirm("确定要删除id为<b>"+id+"</b>的数据吗",function () {
        console.log(url+"?id="+id);
        $.post(url+"?id="+id,function (res) {
            if(res){
                lqtip.tip("删除成功")
                location.reload()
            }
        })
    })
})

$(document).on("click",".more_del",function () {
    var ids ="";
    var i = 0;
    $(".lqtable").find(".checkchild").each(function(){
       if($(this).prop("checked")){
            if(i++!=0){
                ids+=",";
            }
           ids+=$(this).attr("lq-value");
       }
    });

    var url = $(this).attr("data-url");
    lqtip.confirm("确定要删除当前选中的数据吗",function () {
        $.post(url+"?id="+ids,function (res) {
            if(res){
                lqtip.tip("删除成功")
                location.reload()
            }
        })
    })
});
