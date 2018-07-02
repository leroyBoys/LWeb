$(function () {
    $('.lqtable').DataTable($.po('dataTable', {
        'dom': '<"dt-dom-toolbar">frtip'
    }));

    var toolBar = $("#lqtoolbar");
    if(toolBar.length==0){
        toolBar = $('<b style="background-color: red;color: #FFF;">请设置id:lqtoolbar的div</b>');
    }else {
        toolBar = toolBar.clone();
        $("#lqtoolbar").remove();
    }
    $('div.dt-dom-toolbar').html(toolBar.html());
});