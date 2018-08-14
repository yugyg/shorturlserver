//中文
var chinese = ["江苏","北京","湖南","内蒙古","吉林","宁夏","广东","辽宁","青海","湖北","山西",
				"甘肃","河南","河北","陕西","山东","天津","西藏","江西","云南","福建","贵州","安徽",
					"台湾","四川","浙江","澳门","重庆","上海","香港","海南","黑龙江","新疆","广西"];
//拼音
var phonetic =["jiangsu","beijing","hunan","neimenggu","jilin","ningxia","guangdong","liaoning","qinghai","hubei","shanxi",
				"gansu","henan","hebei","shanxi3","shandong","tianjin","xizang","jiangxi","yunnan","fujian","guizhou","anhui",
				"taiwan","sichuan","zhejiang","aomeng","chongqing","shanghai","xianggang","hainan","heilongjiang","xinjiang","guangxi"]

    // 顶部的数据
    url="http://192.168.0.55:8901/data";
    //url="http://spring.yugyg.com:8901/data";
    $.post(url,{shortUrl:"qAjiMr",witch:""},function (data) {
        var topThree=data.data.topThree;
        $(".total_val").html(topThree.total);
        $(".ip_val").html(topThree.ip);
        $(".uv_val").html(topThree.uv);
    });
    //7天内的访问统计
    //基于准备好的dom，初始化echarts实例
    var myChartSevenDay_tj = echarts.init(document.getElementById('sevenDay_tj'));
    // 指定图表的配置项和数据
    var optionSevenDay = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['总量', '独立用户(UV)', '有效跳转', '无效跳转', 'IP数']
        },
        xAxis: {
            type: 'category',
            data: ['07/27', '07/28', '07/29', '07/30', '07/31', '前天', '昨天', '今天',],
            // axisTick: {
            //             //     alignWithLabel: true
            //             // },
            axisLabel: {
                interval: 0,//横轴信息全部显示
                rotate: 0,//60度角倾斜显示
            }
        },
        yAxis: {},
        series: [
            {
                name: '总量',
                type: 'line',
                data: "",
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
            },
            {
                name: '独立用户(UV)',
                type: 'line',
                data: "",
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
            },
            {
                name: '有效跳转',
                type: 'bar',
                stack: '搜索引擎',
                data: ""
            },
            {
                name: '无效跳转',
                stack: '搜索引擎',
                type: 'bar',
                data: ""
            },
            {
                name: 'IP数',
                type: 'line',
                data: ""
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    //myChartSevenDay_tj.setOption(optionSevenDay);
    comment(url,{shortUrl:"qAjiMr",witch:"A"},optionSevenDay,myChartSevenDay_tj,"A");


    //24小时的访问统计
    //基于准备好的dom，初始化echarts实例
    var myChartTwentyFourHours = echarts.init(document.getElementById('twenty-fourHours'));
    // 指定图表的配置项和数据
    var optionTwentyFourHours = {
        backgroundColor: new echarts.graphic.RadialGradient(0, 0, 0, [{
            offset: 0,
            color: '#f7f8fa'
        }, {
            offset: 1,
            color: '#cdd0d5'
        }]),
        legend: {
            data: ['总量', '独立用户（UV）','有效跳转','无效跳转']
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis: {
            type : 'category',
            axisTick: {
                alignWithLabel: true
            },
            axisLabel:{
                interval:2,//横轴信息全部显示
            },
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            }
        },
        yAxis: {
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            },
            scale: true
        },
        series: [{
            name: '总量',
            data: "",
            type: 'scatter',
            symbolSize: function (data) {
                return Math.sqrt(data[2]) / 5e2;
            },
            label: {
                emphasis: {
                    show: true,
                    formatter: function (param) {
                        return param.data[3];
                    },
                    position: 'top'
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 10,
                    shadowColor: 'rgba(120, 36, 50, 0.5)',
                    shadowOffsetY: 5,
                    color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                        offset: 0,
                        color: 'rgb(251, 118, 123)'
                    }, {
                        offset: 1,
                        color: 'rgb(204, 46, 72)'
                    }])
                }
            }
        }, {
            name: '独立用户（UV）',
            data: "",
            type: 'scatter',
            symbolSize: function (data) {
                return Math.sqrt(data[2]) / 5e2;
            },
            label: {
                emphasis: {
                    show: true,
                    formatter: function (param) {
                        return param.data[3];
                    },
                    position: 'top'
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 10,
                    shadowColor: 'rgba(25, 100, 150, 0.5)',
                    shadowOffsetY: 5,
                    color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                        offset: 0,
                        color: 'rgb(129, 227, 238)'
                    }, {
                        offset: 1,
                        color: 'rgb(25, 183, 207)'
                    }])
                }
            }
        },{
            name: '有效跳转',
            data: "",
            type: 'scatter',
            symbolSize: function (data) {
                return Math.sqrt(data[2]) / 5e2;
            },
            label: {
                emphasis: {
                    show: true,
                    formatter: function (param) {
                        return param.data[3];
                    },
                    position: 'top'
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 10,
                    shadowColor: 'rgba(25, 100, 150, 0.5)',
                    shadowOffsetY: 5,
                    color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                        offset: 0,
                        color: 'rgb(129, 227, 238)'
                    }, {
                        offset: 1,
                        color: 'rgb(25, 183, 207)'
                    }])
                }
            }
        },{
            name: '无效跳转',
            data: "",
            type: 'scatter',
            symbolSize: function (data) {
                return Math.sqrt(data[2]) / 5e2;
            },
            label: {
                emphasis: {
                    show: true,
                    formatter: function (param) {
                        return param.data[3];
                    },
                    position: 'top'
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 10,
                    shadowColor: 'rgba(25, 100, 150, 0.5)',
                    shadowOffsetY: 5,
                    color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                        offset: 0,
                        color: 'rgb(129, 227, 238)'
                    }, {
                        offset: 1,
                        color: 'rgb(25, 183, 207)'
                    }])
                }
            }
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    //myChartTwentyFourHours.setOption(optionTwentyFourHours);
    comment(url,{shortUrl:"qAjiMr",witch:"B"},optionTwentyFourHours,myChartTwentyFourHours,"B");

    //访问者设备系统分析
    //基于准备好的dom，初始化echarts实例
    var myChartOS = echarts.init(document.getElementById('OS'));
    // 指定图表的配置项和数据
    var optionOS = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            bottom: 'bottom',
            data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius: ['50%', '70%'],
                data:'',
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    //myChartOS.setOption(optionOS);
    comment(url,{shortUrl:"qAjiMr",witch:"C"},optionOS,myChartOS,"C");

    //访问者浏览器统计
    //基于准备好的dom，初始化echarts实例
    var myChartBrowser = echarts.init(document.getElementById('browser'));
    // 指定图表的配置项和数据
    var optionBrowser = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis: [
            {
                type: 'category',
                data:"",
                axisLabel:{
                    interval:0,//横轴信息全部显示
                    rotate:60,//60度角倾斜显示
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
                data:""
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    //myChartBrowser.setOption(optionBrowser);
    comment(url,{shortUrl:"qAjiMr",witch:"D"},optionBrowser,myChartBrowser,"D");

    //国内访问地区统计
    //基于准备好的dom，初始化echarts实例
    var myChartProvince = echarts.init(document.getElementById('province'));
    // 指定图表的配置项和数据
    var optionProvince = {
        color: ['#3398DB'],
        title : {
            text: '访问地区TOP10',
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'value',

            }
        ],
        yAxis : [
            {
                type : 'category',
                data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                axisLabel:{
                    interval:0,//横轴信息全部显示
                }
            }
        ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
                data:[10, 52, 200, 334, 390, 330, 220]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
//    myChartProvince.setOption(optionProvince);
    // comment(url,{shortUrl:"qAjiMr",witch:"E"},optionProvince,myChartProvince,"E");

    //基于准备好的dom，初始化echarts实例
    var myChartChina = echarts.init(document.getElementById('china'));
    // 指定图表的配置项和数据
    var optionChina = {
        tooltip : {
            trigger: 'item'
        },
        //左侧小导航图标
        visualMap: {
            min: 0,
            max: 0,
            text:['High','Low'],
            realtime: false,
            calculable: true,
            inRange: {
                color: ['lightskyblue','pink', 'orangered']
            }
        } ,
        //配置属性
        series: [{
            name: '数据',
            type: 'map',
            mapType: 'china',
            roam: false,
            label: {
                normal: {
                    show: true  //省份名称
                },
                emphasis: {
                    show: true
                }
            },
            data:""  //数据
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    //myChartChina.setOption(optionChina);
    myChartChina.on('click', function(param) {
    	getCitysMsg(param.name, myChartChina, optionChina)
    });
    comment(url,{shortUrl:"qAjiMr",witch:"E"},optionChina,myChartChina,"E");

    //网络供应商统计
    //基于准备好的dom，初始化echarts实例
    var myChartIsp = echarts.init(document.getElementById('isp'));
    // 指定图表的配置项和数据
    var optionIsp = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis : [
            {
                type : 'value',

            }
        ],
        yAxis : [
            {
                type : 'category',
                data : "",
                axisLabel:{
                    interval:0,//横轴信息全部显示
                }
            }
        ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
                data:""
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    //myChartIsp.setOption(optionIsp);
    comment(url,{shortUrl:"qAjiMr",witch:"F"},optionIsp,myChartIsp,"F");

    // 7天左侧按钮点击请求时间
    $.post(url,{shortUrl:"qAjiMr",witch:"A"},function (data) {
        var totalArr=data.data.sevenDaysData.series[0].split(",");
        var ipArr=data.data.sevenDaysData.series[4].split(",");
        var uvArr=data.data.sevenDaysData.series[1].split(",");
        var tt=data.data.tt;
        $("#week-summary-total").html(totalArr[totalArr.length-1]);
        $("#week-summary-ip").html(ipArr[ipArr.length-1]);
        $("#week-summary-uv").html(uvArr[uvArr.length-1]);
        $('[data-type="week-summary"]').click(function (e) {
            e.preventDefault();
            $('[data-type="week-summary"]').removeClass('active');
            $(this).addClass('active');
            console.log($(this).data("day"));
            if($(this).data("day")=="today"){
                $("#week-summary-total").html(totalArr[totalArr.length-1]);
                $("#week-summary-ip").html(ipArr[ipArr.length-1]);
                $("#week-summary-uv").html(uvArr[uvArr.length-1]);
            }else if($(this).data("day")=="yesterday"){
                $("#week-summary-total").html(totalArr[totalArr.length-2]);
                $("#week-summary-ip").html(ipArr[ipArr.length-2]);
                $("#week-summary-uv").html(uvArr[uvArr.length-2]);
            }else if($(this).data("day")=="sevenDay"){
                $("#week-summary-total").html(tt.total);
                $("#week-summary-ip").html(tt.ip);
                $("#week-summary-uv").html(tt.uv);
            }
        });
    });
    $(function () {
    //设置筛选条件
    $(".smart_filter").click(function () {
        $("#smart_filter_modal").attr("data-type",$(this).data('type'));
        $("#smart_filter_modal").show();
        $("html").addClass("modal-open");
    });
    $('[data-dismiss="modal"]').click(function () {
        $("#smart_filter_modal").hide();
        $("html").removeClass("modal-open");
    });
    //select查询

    $(".send").click(function () {
        var witchType=$("#smart_filter_modal").attr("data-type");
        var equipment_val=$(".equipment").find("option:selected").val();
        var browser_val=$(".browser").find("option:selected").val();
        var inter_val=$(".inter").find("option:selected").val();
        var status_val=$(".status").find("option:selected").val();
        $.ajax({
            type:'post',
            url:'http://spring.yugyg.com:8901/data?shortUrl=qAjiMr',
            data:{witch:witchType,equipment:equipment_val,browser:browser_val,internet:inter_val,status:status_val},
            dataType: "json",
            success:function () {

            }
        })
    });

});
/**
 * 省切换进市
 * @param province 省中文
 * @param chart
 * @param option
 * @returns
 */
function getCitysMsg(province,chart,option){
	var isfind = false;
	for (var i = 0; i < chinese.length; i++) {
		if(province == chinese[i]){
			isfind = true;
			var p = phonetic[i];
			$.ajax({
				url:'/province/'+p+'.json',
				type:'GET',
				async:false, 
				dataType:'json',
				success:function(data){
					echarts.registerMap(p, data);
					option.series[0].map = p;
				}
			});
			comment(url,{shortUrl:"qAjiMr",witch:"EC",privonce:province},optionChina,myChartChina,"EC");
			return;
		}
	}
	if (!isfind) {
		option.series[0].map = "china";
		comment(url,{shortUrl:"qAjiMr",witch:"E"},optionChina,myChartChina,"E");
		chart.setOption(option); 
	}
}