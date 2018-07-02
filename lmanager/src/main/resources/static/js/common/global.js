$.fn.lqform = function(options) {
    var defaults = {
        'url':null,
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
            console.log(e);
        }

        var url = settings.url == null?$(this).attr("action"):settings.url;
        console.log("form submit  data:",_this.data,"url:"+url);
        $.post(url, _this.data,
            function(res){
                if(settings.submit_callback != null){
                    settings.submit_callback(res);
                }
            });

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