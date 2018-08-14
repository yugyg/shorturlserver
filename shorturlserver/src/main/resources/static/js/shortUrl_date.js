$(function () {
    var beginTimeStore = '';
    var endTimeStore = '';
    var beginTimeTake;
    var url="http://192.168.0.55:8901/data";
    // 7天时间
    $('#week-calender').daterangepicker({
        "timePicker": false,
        "timePicker24Hour": false,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "opens":"left",
        maxDate: moment(new Date()), //设置最大日期
        "locale": {
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        },
        dateLimit : {
            days : 7
        }, //起止时间的最大间隔
    }, function(start, end, label) {
        beginTimeStore = start;
        endTimeStore = end;

        var startDate=this.startDate.format(this.locale.format);
        var endDate=this.endDate.format(this.locale.format);
        if(!this.startDate){
            this.element.val('');
        }else{
            this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
            comment(url,{shortUrl:"bu6Rfu",witch:"A",startTime:startDate,endTime:endDate},optionSevenDay,myChartSevenDay_tj,"A");
        }
    });

    // 24小时
    $('#day-calender').daterangepicker({
            singleDatePicker: true,
            showDropdowns: false,
            autoUpdateInput: false,
            timePicker24Hour : false,
            timePicker : false,
            opens:"left",
            maxDate: moment(new Date()), //设置最大日期
            "locale": {
                format: 'YYYY-MM-DD',
                applyLabel: "应用",
                cancelLabel: "取消",
                resetLabel: "重置",
                "weekLabel": "W",
                "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
                "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                "firstDay": 1
            }
        },
        function(start, end, label) {
            beginTimeTake = start;
            if(!this.startDate){
                this.element.val('');
            }else{
                this.element.val(this.startDate.format(this.locale.format));
                var startHour=this.startDate.format(this.locale.format);
                console.log(this.startDate.format(this.locale.format));
                comment(url,{shortUrl:"bu6Rfu",witch:"B",endTime:startHour + " 23:59:59"},optionTwentyFourHours,myChartTwentyFourHours,"B");
            }
        });

    // 访问者设备系统分析
    $('#os-calender').daterangepicker({
        "timePicker": false,
        "timePicker24Hour": false,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "opens":"left",
        maxDate: moment(new Date()), //设置最大日期
        "locale": {
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        },
    }, function(start, end, label) {
        beginTimeStore = start;
        endTimeStore = end;

        var startDate=this.startDate.format(this.locale.format);
        var endDate=this.endDate.format(this.locale.format);
        if(!this.startDate){
            this.element.val('');
        }else{
            this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
            comment(url,{shortUrl:"bu6Rfu",witch:"C",startTime:startDate,endTime:endDate},optionOS,myChartOS,"C");
            console.log(this.startDate.format(this.locale.format))
        }
    });

    //访问者浏览器统计
    $('#browser-calender').daterangepicker({
        "timePicker": false,
        "timePicker24Hour": false,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "opens":"left",
        maxDate: moment(new Date()), //设置最大日期
        "locale": {
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        },
    }, function(start, end, label) {
        beginTimeStore = start;
        endTimeStore = end;
        var startDate=this.startDate.format(this.locale.format);
        var endDate=this.endDate.format(this.locale.format);
        if(!this.startDate){
            this.element.val('');
        }else{
            this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
            comment(url,{shortUrl:"bu6Rfu",witch:"D",startTime:startDate,endTime:endDate},optionBrowser,myChartBrowser,"D");
            console.log(this.startDate.format(this.locale.format))
        }
    });

    //国内访问地区统计
    $('#china-calender').daterangepicker({
        "timePicker": false,
        "timePicker24Hour": false,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "opens":"left",
        maxDate: moment(new Date()), //设置最大日期
        "locale": {
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        },
    }, function(start, end, label) {
        beginTimeStore = start;
        endTimeStore = end;
        var startDate=this.startDate.format(this.locale.format);
        var endDate=this.endDate.format(this.locale.format);
        if(!this.startDate){
            this.element.val('');
        }else{
            this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
            comment(url,{shortUrl:"bu6Rfu",witch:"E",startTime:startDate,endTime:endDate},optionChina,myChartChina,"E");
        }
    });

    //网络供应商统计
    $('#isp-calender').daterangepicker({
        "timePicker": false,
        "timePicker24Hour": false,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "opens":"left",
        maxDate: moment(new Date()), //设置最大日期
        "locale": {
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        },
    }, function(start, end, label) {
        beginTimeStore = start;
        endTimeStore = end;
        var startDate=this.startDate.format(this.locale.format);
        var endDate=this.endDate.format(this.locale.format);
        if(!this.startDate){
            this.element.val('');
        }else{
            this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
            comment(url,{shortUrl:"bu6Rfu",witch:"F",startTime:startDate,endTime:endDate},optionIsp,myChartIsp,"F");
        }
    });

    //访问记录明细
    $('#log-calender').daterangepicker({
        "timePicker": false,
        "timePicker24Hour": false,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "opens":"left",
        maxDate: moment(new Date()), //设置最大日期
        "locale": {
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        },
    }, function(start, end, label) {
        beginTimeStore = start;
        endTimeStore = end;
        var startDate=this.startDate.format(this.locale.format);
        var endDate=this.endDate.format(this.locale.format);
        if(!this.startDate){
            this.element.val('');
        }else{
            this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
            console.log(this.startDate.format(this.locale.format));
            console.log(this.endDate.format(this.locale.format));
            log(url,{shortUrl:"bu6Rfu",witch:"H",startTime:startDate,endTime:endDate})
        }
    });

    //高频访问IP名单
    $('#ip-calender').daterangepicker({
        "timePicker": false,
        "timePicker24Hour": false,
        "linkedCalendars": false,
        "autoUpdateInput": false,
        "opens":"left",
        maxDate: moment(new Date()), //设置最大日期
        "locale": {
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        },
    }, function(start, end, label) {
        beginTimeStore = start;
        endTimeStore = end;
        var startDate=this.startDate.format(this.locale.format);
        var endDate=this.endDate.format(this.locale.format);
        if(!this.startDate){
            this.element.val('');
        }else{
            this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
            ipAnalyse(url,{shortUrl:"bu6Rfu",witch:"I",startTime:startDate,endTime:endDate})
        }
    });
});