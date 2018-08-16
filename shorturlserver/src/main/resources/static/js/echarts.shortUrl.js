//中文
var chinese = ["江苏","北京","湖南","内蒙古","吉林","宁夏","广东","辽宁","青海","湖北","山西",
				"甘肃","河南","河北","陕西","山东","天津","西藏","江西","云南","福建","贵州","安徽",
					"台湾","四川","浙江","澳门","重庆","上海","香港","海南","黑龙江","新疆","广西"];
//拼音
var phonetic =["jiangsu","beijing","hunan","neimenggu","jilin","ningxia","guangdong","liaoning","qinghai","hubei","shanxi",
				"gansu","henan","hebei","shanxi3","shandong","tianjin","xizang","jiangxi","yunnan","fujian","guizhou","anhui",
				"taiwan","sichuan","zhejiang","aomeng","chongqing","shanghai","xianggang","hainan","heilongjiang","xinjiang","guangxi"]
    url="/data";
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
//    comment(url,{shortUrl:myShort,witch:"A"},optionSevenDay,myChartSevenDay_tj,"A");


    //24小时的访问统计
    //基于准备好的dom，初始化echarts实例
    var myChartTwentyFourHours = echarts.init(document.getElementById('twenty-fourHours'));
    // 指定图表的配置项和数据
    var optionTwentyFourHours =  {
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
                data: "",
                axisTick: {
                    alignWithLabel: true
                           },
                axisLabel: {
                    interval: 3,//横轴信息全部显示
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
                }
            ]
        };
;
    // 使用刚指定的配置项和数据显示图表。
    //myChartTwentyFourHours.setOption(optionTwentyFourHours);
//    comment(url,{shortUrl:myShort,witch:"B"},optionTwentyFourHours,myChartTwentyFourHours,"B");

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
//    comment(url,{shortUrl:myShort,witch:"C"},optionOS,myChartOS,"C");

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
//    comment(url,{shortUrl:myShort,witch:"D"},optionBrowser,myChartBrowser,"D");

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
//    comment(url,{shortUrl:myShort,witch:"E"},optionChina,myChartChina,"E");

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
//    comment(url,{shortUrl:myShort,witch:"F"},optionIsp,myChartIsp,"F");
    
  //刷新判断是否有本地储存短链接
    if (!isNull(window.localStorage.getItem("myShortUrl"))){
        myShort=window.localStorage.getItem("myShortUrl");
    }else {
    	localStorage.setItem("myShortUrl","bu6Rfu");
    	myShort = "bu6Rfu";
    }
    comment(url,{shortUrl:myShort,witch:"A"},optionSevenDay,myChartSevenDay_tj,"A");
    comment(url,{shortUrl:myShort,witch:"B"},optionTwentyFourHours,myChartTwentyFourHours,"B");
    comment(url,{shortUrl:myShort,witch:"C"},optionOS,myChartOS,"C");
    comment(url,{shortUrl:myShort,witch:"D"},optionBrowser,myChartBrowser,"D");
    comment(url,{shortUrl:myShort,witch:"E"},optionChina,myChartChina,"E");
    comment(url,{shortUrl:myShort,witch:"F"},optionIsp,myChartIsp,"F");
    log(url,{shortUrl:myShort,witch:"H"});
    ipAnalyse(url,{shortUrl:myShort,witch:"I"});
    $.post(url,{shortUrl:myShort,witch:""},function (data) {
    	var topThree=data.data.topThree;
    	$(".total_val").html(topThree.total);
    	$(".ip_val").html(topThree.ip);
    	$(".uv_val").html(topThree.uv);
    });
    $(".shortUrl_input").val(myShort);
    ipAnalyse(url,{shortUrl:myShort,witch:"I"});
    log(url,{shortUrl:myShort,witch:"H"});
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
			comment(url,{shortUrl:myShort,witch:"EC",privonce:province},optionChina,myChartChina,"EC");
			return;
		}
	}
	if (!isfind) {
		option.series[0].map = "china";
		comment(url,{shortUrl:myShort,witch:"E"},optionChina,myChartChina,"E");
		chart.setOption(option); 
	}
}