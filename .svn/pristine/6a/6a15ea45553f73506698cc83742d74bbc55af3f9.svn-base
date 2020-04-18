/**
* 倒计时 小插件
* @since 2018/11/7 12:39 create...
* @since 2019/6/3 18:17 update...
* @author FlyTiger
* */
(function (global, factory) {
    typeof exports === "object" && typeof module !== "undefined" ? module.exports = factory() : typeof define === "function" && define.amd ? (function(){ define(["jquery"],factory);global.countDown = factory();})() : (global.countDown = factory());
})(this, function () {
    var ___=window;
    //模板
    var $defaultTitle=["距离","开始","还有"];
    var $template="<span class=\"active-time pull-right\"><t class='time_prefix'></t><em class=\"time_d\"></em>天<em class=\"time_h\"></em>:<em class=\"time_m\"></em>:<em class=\"time_s\"></em><em class=\"time_ms\"></em><t class='time_suffix'></t></span>";

    //事件
    var events={
        started:"countDownStarted",//开启
        ended:"countDownEnded",//结束
        restarted:"countDownRestarted"//重启
    };

    //常量池
    var GLOBAL_CONSTANTS={
        //毫秒倒计时分度
        MS_TIME_STEP:50,
        //秒倒计时分度
        SEC_TIME_STEP:1000,
        /*相对毫秒的时间计算常量*/
        //天计算常量
        DAY_STEP_MS:86400000,
        //小时计算常量
        HOUR_STEP_MS:3600000,
        //分钟计算常量
        MINUTE_STEP_MS:60000,
        //秒计算常量
        SEC_STEP_MS:1000
    };

    /**
     * 对100以内数字进行前面补0操作.
     * @param num
     * @returns {string}
     */
    var deal2=function (num) {
        return ((num / 10)|0) + "" + num % 10;
    };

    /**
     * 对1000以内数字进行3位补0
     * @param num
     * @returns {string}
     */
    var deal3 = function (num) {
        if(num<100){
            return '0'+deal2(num);
        }
        return ''+num;
    };

    /**
     * 时间毫秒值获取
     * @param time 初始化时间值:可选
     * @returns {number}
     */
    var getTime=function (time) {
        return (time?new Date(time):new Date()).getTime();
    };

    //方法
    var fns={
        //标题处理
        _title:function(){
            var _opts=this.opts;
            var $prefix=this.$timeEms_.$prefix,$suffix=this.$timeEms_.$suffix;
            if((_opts.title&&_opts.title.length)||(!_opts.prefix&&!_opts.suffix)){
                var _1= [].concat($defaultTitle);
                _1.splice(1,1,_opts.title||$defaultTitle[1]);
                $prefix.html(_1.join(""));
            }else{
                $prefix.html(_opts.prefix||"");
                $suffix.html(_opts.suffix||"");
            }
        },
        //初始化
        prepare:function(){
            fns._title.call(this);
            fns._f.call(this);
        },
        _f:function () {
            var _this=this;
            var time_end=this.opts.time_end||getTime();
            var _ems=this.$timeEms_;
            /** 如果需要MS,则倒计时频率为
             * @see #GLOBAL_CONSTANTS.MS_TIME_STEP
             * ,否则为
             * @see #GLOBAL_CONSTANTS.SEC_TIME_STEP
             * */
            var needms = this.opts.needms,timeStep = needms?GLOBAL_CONSTANTS.MS_TIME_STEP:GLOBAL_CONSTANTS.SEC_TIME_STEP;
            if(needms){
                this.$timeEms_.$ms.show();
            }else{
                this.$timeEms_.$ms.hide();
            }
            var f_=function _f(_time_end){
                var time_start = getTime(); //设定当前时间
                var time_end = getTime(_time_end); //设定目标时间
                // 计算时间差
                var time_distance = time_end - time_start;
                if(time_distance<1){
                    if(_this._timestamp_){
                        clearTimeout(_this._timestamp_);
                    }
                    _this._runing++;
                    _this.$container_.hide().triggerHandler(events.ended,[this.$container_,_this._runing,time_start]);
                    return false;
                }
                // 天
                var int_day = Math.floor(time_distance/GLOBAL_CONSTANTS.DAY_STEP_MS);
                time_distance -= int_day * GLOBAL_CONSTANTS.DAY_STEP_MS;
                // 时
                var int_hour = Math.floor(time_distance/GLOBAL_CONSTANTS.HOUR_STEP_MS);
                time_distance -= int_hour * GLOBAL_CONSTANTS.HOUR_STEP_MS;
                // 分
                var int_minute = Math.floor(time_distance/GLOBAL_CONSTANTS.MINUTE_STEP_MS);
                time_distance -= int_minute * GLOBAL_CONSTANTS.MINUTE_STEP_MS;
                // 秒
                var int_second = Math.floor(time_distance/GLOBAL_CONSTANTS.SEC_TIME_STEP);

                // 时分秒为单数时、前面补位零
                int_hour=deal2(int_hour);
                int_minute=deal2(int_minute);
                int_second=deal2(int_second);

                // 显示时间
                _ems.$d.html(int_day);
                _ems.$h.html(int_hour);
                _ems.$m.html(int_minute);
                _ems.$s.html(int_second);

                if(needms){
                    //毫秒
                    var int_ms = time_distance -int_second*GLOBAL_CONSTANTS.SEC_TIME_STEP;
                    int_ms = deal3(int_ms);
                    _ems.$ms.html(int_ms);
                }

                // 设置定时器
                if(_this._timestamp_){
                    clearTimeout(_this._timestamp_);
                }
                _this._timestamp_=setTimeout(function (_) {
                    _f(_);
                },timeStep,_time_end);
            };
            if(this._runing){
                this.$container_.triggerHandler(events.restarted,[this.$container_,this._runing]);
            }else{
                this.$container_.triggerHandler(events.started,[this.$container_,this._runing]);
            }
            this.$container_.show();
            f_(time_end);
        }
    };
    /*
       * @param $container 倒计时容器
       * @param $opts 参数{title,prefix,suffix,time_end,needms},title优先级最高,needms默认为false,设置为true时,倒计时包含毫秒倒计时位.
       * */
        var CountDown=function ($container,$opts) {
            this.opts=$opts||{};
            $container=$container.empty().html($template).addClass("payment-time");
            // 显示时间
            this.$timeEms_={
                $d:$(".time_d",$container),
                $h:$(".time_h",$container),
                $m:$(".time_m",$container),
                $s:$(".time_s",$container),
                $ms : $(".time_ms",$container),
                $prefix:$(".time_prefix",$container),
                $suffix:$(".time_suffix",$container)
            };
            this.$container_=$container;
            this._runing=0;//标识初始执行
            //初始化
            fns.prepare.call(this);
        };

        //重新设置参数
        CountDown.prototype.setOpts=function($opts){
            this.opts=$.extend(this.opts, $opts||{});
            this._runing++;
            fns.prepare.call(this);
        };

        //绑定事件
        CountDown.prototype.on=function () {
             ___.jQuery.fn.on.apply(this.$container_,arguments);
             if(this._runing<1){
                 this.$container_.triggerHandler(events.started,[this.$container_,this._runing]);
             }
             return this;
        };
        //解除事件
        CountDown.prototype.off=function () {
              ___.jQuery.fn.off.apply(this.$container_,arguments);
            return this;
        };

    return function($container,$opts){
        if(!___.jQuery){
            throw new Error("jQuery is required for this plugin");
        }
        if(!$container||!$container.length){
            throw new Error("The container you given is not useful!");
        }
        return new CountDown($container,$opts);
    }
});