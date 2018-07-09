$.fn.lqform = function(options) {
    var defaults = {
        'url':null,
        'before_submit':null,//返回修改后的对象
        'submit_callback':null,
        'check':null,
        'isRight':true
    };
    var settings = defaults;
    if(typeof options === "function"){
        settings.submit_callback = options;
    }else {
        settings=$.extend(defaults, options);
    }
    this.data={};

    var _this = this;
    this.clear = function () {
        
    }
    this.on("submit",function () {
        try{
            var check = settings.check == null?_check: settings.check;
            check(_this);
            if(!settings.isRight){
                event.preventDefault();
                return;
            }

        }catch(e){
            console.error(e);
            event.preventDefault();
            return;
        }

        var url = settings.url == null?$(this).attr("action"):settings.url;
        console.log("form submit  data:",_this.data,"url:"+url);
        if(_this.find("input[type='file']").length != 0){
            var uploading = false;

            console.log(new FormData(_this[0]));
            $.ajax({
                url: url,
                type: 'POST',
                cache: false,
                data: settings.before_submit != null?settings.before_submit(new FormData(_this[0])):new FormData(_this[0]),
                processData : false,         // 告诉jQuery不要去处理发送的数据
                contentType : false,        // 告诉jQuery不要去设置Content-Type请求头
                dataType:"json",
                beforeSend: function(){
                    uploading = true;
                },
                success : function(data) {
                    if(settings.submit_callback != null){
                        settings.submit_callback(res);
                    }
                   console.log("suc",data);
                    uploading = false;
                }
            });
        }else {
            $.post(url, settings.before_submit != null?settings.before_submit(_this.data):_this.data,
                function(res){
                    if(settings.submit_callback != null){
                        settings.submit_callback(res);
                    }
                });
        }
        event.preventDefault();
    })


    this.check =function(_user_check){
        if(_user_check != undefined){
            settings.check = _user_check;
            return false;
        }

        _check();
    }

    function _check(){
        settings.isRight = true;
        var obj = _formatDataForForm(_this.find("input"));
        $.extend( true, obj, _formatDataForForm(_this.find("select")));
        _this.data=obj;
    }

    function _formatDataForForm(elementArray){
        var obj = {};

        elementArray.each(function(e){
            if($(this).attr("name") == undefined){
                return
            }
            var tagName = $(this)[0].tagName;
            var type = $(this).attr("type");
            var name = $(this).attr("name");
            var required = $(this).attr("required");
            if(tagName == "SELECT"){
                obj[name]=$(this).val();
                if(required!= undefined && required){
                    if(obj[name] ==  "" || obj[name].length == 0){
                        settings.isRight = false;
                        _tip(name+"不能为空",$(this));
                    }
                }
            }else if(type == "checkbox"){
                if(obj[name] == undefined){
                    obj[name] =[];
                }
                if($(this).attr("checked") != undefined && $(this).attr("checked")){
                    obj[name].push($(this).val());
                }
            }else{
                obj[name]=$(this).val();

                if(required!= undefined && required){
                    if(obj[name] ==  ""){
                        settings.isRight = false;
                        _tip(name+"不能为空",$(this));
                    }
                }
            }

        });
        return obj;
    }

    function _tip(tipmsg,_element){
        console.log("====_tip:"+tipmsg,_element);
    }

    return this;
}

$("form.lq-form").lqform();

$('.lq-tab').on("click","body",function () {
    console.log("====================lq-tab");
});


$('[data-toggle="tooltip"]').tooltip({trigger: 'hover'});

////url 参数
function UrlParamters() {
    var name,value;
    var str=location.href; //取得整个地址栏
    var num=str.indexOf("?")
    str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]

    var arr=str.split("&"); //各个参数放到数组里
    var params={};

    for(var i=0;i < arr.length;i++){
        num=arr[i].indexOf("=");
        if(num>0){
            name=arr[i].substring(0,num);
            value=arr[i].substr(num+1);
            params[name]=value;
        }
    }
    return params;
}