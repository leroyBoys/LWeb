$(function () {
    var lqtables = $('.lqtable');
    if(lqtables.length == 0){
        return
    }

    $('.lqtable').each(function () {
        console.log($(this),this);
    })



    $('.lqtable').DataTable($.po('dataTable', {
        'dom': '<"#lqtoolbar">frtip'
    }));

    var toolBar = $("#lqtoolbar");
    if(toolBar.length==0){
        toolBar = $('<b style="background-color: red;color: #FFF;">请设置id:lqtoolbar的div</b>');
    }else {
        toolBar = toolBar.clone();
        $("#lqtoolbar").remove();
    }
    $('#lqtoolbar').html(toolBar.html());
});