
$(function () {
    var lqtables = $('.lqtable');
    if(lqtables.length == 0){
        return
    }

    function cloneTarget(_this) {
        var target = _this.attr("lq-tool");
        var c = target.charAt(0);
        if(c == '#'){
            $(target).hide();
            return $(target).clone();
        }else if(c == '.'){
            $(target).hide();
            return $(target).clone();
        }
        //check box
        _this.html('<div class="tableCheckBox"><span class="all link">全选</span> | <span class="unselect link">反选</span></div>');
        return $('<input type="checkbox"  class="checkchild"  />');
    }

    $(document).on("change",".checkchild",function () {
        var _parent = $(this).parent().parent().parent().parent().parent();
        console.log("==", $(this),$(this).prop("checked"));
    })

    $(document).on("click",".tableCheckBox .all",function () {
        var _parent = $(this).parent().parent().parent().parent().parent();
        _parent.find(".checkchild").prop("checked",true);
    })

    $(document).on("click",".tableCheckBox .unselect",function () {
        var _parent = $(this).parent().parent().parent().parent().parent();
        _parent.find(".checkchild").each(function(){
            $(this).prop("checked",!$(this).prop("checked"));
        });

        //_parent.DataTable().draw();
    })


    $('.lqtable').each(function () {
        var url = $(this).attr("lq-url");
        var columns = [];
        $(this).find("thead tr th").each(function () {

            var data = {"data":$(this).attr("lq-colum"),"bSortable":false}
            if(data.data == undefined){
                data.data = "id";
            }

            if($(this).attr("lq-tool") != undefined){
                var target =cloneTarget($(this));
                if(target.length != 0){
                    target.show()
                    data.sClass= "text-center";
                    data.render= function (_data, type, full, meta) {
                        target.attr("lq-value",_data);
                        target.attr("lq-full",JSON.stringify(full));
                        return target.prop("outerHTML");
                    };
                }else {
                    console.error("not find element : "+$(this).attr("lq-tool"));
                }
            }

            if($(this).attr("lq-sort") != undefined){
                data.bSortable= $(this).attr("lq-sort")=="false"?false:true;
            }

            columns.push(data)
        })
        var dom = 'rtlip';
        var lqTool = $(this).attr("lq-tool");
        var lqToolExit = lqTool!= undefined && lqTool.length>0;
        if(lqToolExit){
            dom = '<"lq-table-toolbar">rtlip'
        }

        var post =  $(this).attr("lq-type") == undefined||$(this).attr("lq-type") == "get"?"get":"post";
        $(this).DataTable({
            'language': {
                'sSearchPlaceholder': '快速查找',
                'lengthMenu': '每页显示\x20_MENU_\x20条',
                'search': '_INPUT_',
                'info': '共 _TOTAL_ 条',
                'infoEmpty': '共 0 条',
                'emptyTable': '无数据',
                'zeroRecords': '抱歉，没有找到符合条件的记录',
                'sInfoFiltered': '(从 _MAX_ 条记录中查找)',
                'loadingRecords': '加载中，请稍后…',
                'processing': '正在处理，请稍后…',
                'paginate': {
                    'first': '第一页',
                    'last': '最后一页',
                    'previous': '<i class="icon wb-chevron-left-mini"></i>',
                    'next': '<i class="icon wb-chevron-right-mini"></i>'
                },
                'aria': {
                    'sortAscending': '升序排列',
                    'sortDescending': '降序排列'
                }
            },
            'dom': dom,
            "processing": true,
            "serverSide": true,
            "retrieve": true,
            "destroy":true,
            "columns": columns,
            "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                console.log(aoData)
                aoData = merageObject(aoData);

                var order = [];
                if(aoData.order && aoData.order.length>0){
                    for(var i in aoData.order){
                        var colum = aoData.columns[aoData.order[i].column].data;
                        var v = aoData.order[i].dir;
                        order.push(colum+"."+v);
                    }
                }

                var jsonData = {
                    "start":aoData.start,
                    "end":aoData.length,
                    "order":order
                };
                oSettings.jqXHR = $.ajax({
                    "type": post,
                    "contentType":"application/json;charset=utf-8",
                    "data":JSON.stringify(jsonData),
                    "url": url,
                    "dataType": "JSON",
                    "success": function (resp) {
                        oSettings.iDraw = oSettings.iDraw + 1;
                        var data = {};
                        data.aaData = resp.results;
                        var totalCounts = resp.allCount

                        data.iTotalRecords = totalCounts;
                        data.iTotalDisplayRecords = totalCounts;
                        data.sEcho = oSettings;
                        fnCallback(data);
                    },
                    "error": function(resp) {
                        var data = {};
                        data.aaData = [];
                        var totalCounts = 0;

                        data.iTotalRecords = totalCounts;
                        data.iTotalDisplayRecords = totalCounts;
                        data.sEcho = oSettings;
                        fnCallback(data);
                    }
                });

            },
            columnDefs : [],
            "order" : [],
            "footerCallback": function (row, data, start, end, display) {

                $('[data-toggle="tooltip"]').tooltip({trigger: 'hover'});
            }
        });

        if(lqToolExit){
            var toolBar = $(lqTool);
            if(toolBar.length==0){
                toolBar = $('<b style="background-color: red;color: #FFF;">请设置id/class:'+lqTool+'的div/span</b>');
            }else {
                toolBar = toolBar.clone();
                toolBar.show();
                $(lqTool).remove();
            }

            $('div.lq-table-toolbar').html(toolBar.prop("outerHTML"));
        }

    })


    function merageObject(arr) {
        var obj = {};
        for(var i in arr){
            obj[arr[i].name] = arr[i].value;
        }
        return obj;
    }

});