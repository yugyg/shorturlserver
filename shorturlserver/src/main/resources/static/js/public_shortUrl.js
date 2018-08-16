var shortUrlPath="";
var totalArr="";
var ipArr="";
var uvArr="";
var tt="";
var witchType="";
var equipment_val="";
var browser_val="";
var inter_val="";
var prov_val="";
var status_val="";
var nowDate=new Date();
var nowYear=nowDate.getFullYear();
var nowMonth=nowDate.getMonth()+1;
var tag="";
var myShort="";
var url="/data";


//判断日期是否大于10
if (nowMonth<10){
    nowMonth=String(String(0)+String(nowMonth));
}
var nowDay=nowDate.getDate();
if (nowDay<10){
    nowDay=String(String(0)+String(nowDay));
}
var nowTime=nowYear + "-" + nowMonth + "-" + nowDay;
var xAxis="";
function comment(url,parm,option,myChart,witch){
    $.post(url,parm,function (data) {
        var seriesArr;
        switch (witch) {
                // 7天内的访问统计
            case "A":
                option.xAxis.data=data.data.sevenDaysData.xAxis.split(",");
                seriesArr=data.data.sevenDaysData.series;
                xAxisT=option.xAxis.data[option.xAxis.data.length-1];
                xAxisY=option.xAxis.data[option.xAxis.data.length-2];
                for (var i=0;i<seriesArr.length;i++) {
                    option.series[i].data = seriesArr[i].split(",");
                }
                totalArr=data.data.sevenDaysData.series[0].split(",");
                ipArr=data.data.sevenDaysData.series[4].split(",");
                uvArr=data.data.sevenDaysData.series[1].split(",");
                $("#week-summary-total").html(totalArr[totalArr.length-1]);
                $("#week-summary-ip").html(ipArr[ipArr.length-1]);
                $("#week-summary-uv").html(uvArr[uvArr.length-1]);
                $('[data-type="week-summary"]').click(function (e) {
                    e.preventDefault();
                    $(this).siblings().removeClass('active');
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
                tt=data.data.tt;
                if(xAxisT!=nowTime){
                    $('[data-day="today"]').html(xAxisT);
                    $('[data-day="yesterday"]').html(xAxisY)
                }else {
                    $('[data-day="today"]').html("今天");
                    $('[data-day="yesterday"]').html("昨天");
                }
                break;
                // 24小时内访问统计
            case "B":
                option.xAxis.data=data.data.twentyHours.xAxis.split(",");
                seriesArr=data.data.twentyHours.series;
                option.series[0].data = seriesArr[0].split(",");
                option.series[1].data = seriesArr[1].split(",");
                option.series[2].data = seriesArr[2].split(",");
                option.series[3].data = seriesArr[3].split(",");
                break;
                // 访问者设备系统分析
            case "C":
            	option.series[0].data=data.data.equit;
                var arr=option.series[0].data;
                var list=[];
                var totalDeviceNum = 0;
                var colorClass = ['aqua','green','yellow','red','aqua'];
                arr.forEach(function(data){
                    list.push(data.name);
                    totalDeviceNum += data.value;
                });
                console.log(arr);
                $("#user-device").empty();
                arr.forEach(function(data,index){
                    var templete = `
                        <div class="progress-group" style="margin: 40px 0;">
                        <span class="progress-text">${data.name}</span>
                        <span class="progress-number">${(data.value/totalDeviceNum*100).toFixed(2)}%</span>
                        <div class="progress sm">
                            <div class="progress-bar progress-bar-${colorClass[index]}" style="width:${(data.value/totalDeviceNum*100).toFixed(2)}%;"></div>
                        </div>
                    </div>
                    `.trim();
                    $("#user-device").append(templete);
                    if(index == 5) return;
                });
                option.legend.data=list;
                break;
                // 访问者浏览器统计
            case "D":
                if(!data.data.brow){
                    option.xAxis[0].data="";
                    option.series[0].data ="";
                    break;
                }
                option.xAxis[0].data=data.data.brow.xAxis.split(",");
                seriesArr=data.data.brow.seriesStr;
                option.series[0].data = seriesArr.split(",");
                break;
                // 国内访问地区统计
            case "E":
                console.log(data);
                option.series[0].data=data.data.privon;
                option.visualMap.min=data.data.privon[data.data.privon.length-1].value;
                option.visualMap.max=Math.ceil(data.data.privon[0].value);
                var privon = data.data.privon;
                var y=[];var x = [];//y 轴数据，x轴数据
                for (var i = (privon.length>=10?9:privon.length-1); i >= 0; i--) {
					y.push(privon[i].name)
					x.push(privon[i].value)
				}
                optionProvince.yAxis[0].data=y;
                optionProvince.series[0].data=x;
                break;
                // 网络供应商统计
            case "EC":
            	console.log(data);
            	option.series[0].data=data.data.city;
            	option.visualMap.min=data.data.city[data.data.city.length-1].value;
            	option.visualMap.max=Math.ceil(data.data.city[0].value);
            	var privon = data.data.city;
            	var y=[];var x = [];//y 轴数据，x轴数据
                for (var i = (privon.length>=10?9:privon.length-1); i >= 0; i--) {
					y.push(privon[i].name)
					x.push(privon[i].value)
				}
                optionProvince.yAxis[0].data=y;
                optionProvince.series[0].data=x;
            	break;
            case "F":
                console.log(data);
                if(!data.data.inter){
                    option.yAxis[0].data="";
                    option.series[0].data ="";
                    break;
                }
                option.yAxis[0].data=data.data.inter.xAxis.split(",");
                seriesArr=data.data.inter.seriesStr;
                option.series[0].data=seriesArr.split(",");
                break;
        }
        myChart.setOption(option);
        if(witch == "E"||witch == "EC"){
        	myChartProvince.setOption(optionProvince)
        }
    })
}



//查询短链接网址
$(".shortUrl_search").click(function () {
    shortUrlPath=$(".shortUrl_input").val();
    //添加到本地缓存
    localStorage.setItem("myShortUrl",shortUrlPath);
    myShort = localStorage.getItem("myShortUrl");
    comment(url,{shortUrl:myShort,witch:"A"},optionSevenDay,myChartSevenDay_tj,"A");
    comment(url,{shortUrl:myShort,witch:"B"},optionTwentyFourHours,myChartTwentyFourHours,"B");
    comment(url,{shortUrl:myShort,witch:"C"},optionOS,myChartOS,"C");
    comment(url,{shortUrl:myShort,witch:"D"},optionBrowser,myChartBrowser,"D");
    comment(url,{shortUrl:myShort,witch:"E"},optionChina,myChartChina,"E");
    comment(url,{shortUrl:myShort,witch:"F"},optionIsp,myChartIsp,"F");
    log(url,{shortUrl:myShort,witch:"H"});
    ipAnalyse(url,{shortUrl:myShort,witch:"I"});
    // 顶部三个数据
    $.post(url,{shortUrl:myShort,witch:""},function (data) {
        var topThree=data.data.topThree;
        $(".total_val").html(topThree.total);
        $(".ip_val").html(topThree.ip);
        $(".uv_val").html(topThree.uv);
    });
    // 7天左侧按钮点击请求时间
    $.post(url,{shortUrl:myShort,witch:"A"},function (data) {
        totalArr=data.data.sevenDaysData.series[0].split(",");
        ipArr=data.data.sevenDaysData.series[4].split(",");
        uvArr=data.data.sevenDaysData.series[1].split(",");
        tt=data.data.tt;
        console.log(tt);
    });
});
// 请求记录明细
function log(url,parms){
    $.post(url,parms,function(data){
        if( data && data.result =="success" ){
        	var totalPage = data.data.totalPage;
        	$('#pageLimit').bootstrapPaginator({
        	    currentPage: 1,//当前的请求页面。
        	    totalPages: totalPage,//一共多少页。
        	    size:"normal",//应该是页眉的大小。
        	    bootstrapMajorVersion: 3,//bootstrap的版本要求。
        	    alignment:"right",
        	    numberOfPages:5,//一页列出多少数据。
        	    itemTexts: function (type, page, current) {//如下的代码是将页眉显示的中文显示我们自定义的中文。
        	        switch (type) {
        	            case "first": return "首页";
        	            case "prev": return "上一页";
        	            case "next": return "下一页";
        	            case "last": return "末页";
        	            case "page": return page;
        	        }
        	    },
        	    onPageClicked: function (event, originalEvent, type, page){//给每个页眉绑定一个事件，其实就是ajax请求，其中page变量为当前点击的页上的数字。
        	        $.ajax({
        	            url:'/data?witch=H&shortUrl='+myShort,
        	            type:'POST',
        	            data:{'page':page},
        	            dataType:'JSON',
        	            success:function (data) {
        	                if( data && data.result =="success" ) {
        	                	addLog(data);
        	                	var top = $("#fwmx").offset().top;
        	                	$(document).scrollTop(top);
        	                }else{
        	                	alert("网络出问题了，请刷新后查看！");
        	                }
        	            }
        	        })
        	    }
        	});
            addLog(data);
        }else {
        	alert("网络出问题了，请刷新后查看！")
        }
    })
}

// 在页面添加记录明细
function addLog(data){
    data = data.data;
    var allRecord = data.allRecord;//列表数据
    if( !allRecord ) allRecord = [];

    var $logTable = $("#logs-area");
    var $tbody = $logTable.find("tbody");
    $tbody.empty();
    allRecord.forEach(function(list){
        var templete = `
    <tr class="${list.status == 1 ? 'warning' : '' }">
            <td>${ timestampToTime(list.time) }</td>
            <td title="设备号">${list.equipment}</td>
            <td title="${list.useragent}">${list.browser}</td>
            <td>${list.ip}</td>
            <td>${list.ipbelong}</td>
            <td>
                <div class="refer_area">${list.referer}</div>
            </td>
            <td><span class="${list.status == 1 ? 'label label-warning' : '' }">${list.status == 1 ? '过滤' : '成功'}</span></td>
        </tr>
    `.trim();
        $tbody.append(templete);
    });
}

//高频访问IP名单
function ipAnalyse(url,parms){
    $.post(url,parms,function(data){
        var ips = data.data.ips;
        var $ipRank = $("#ip-rank");
        var $tbody = $ipRank.find("tbody");
        $tbody.empty();
        // IP地址	归属地	次数
        ips.forEach(function(list){
            var templete = `
            <tr>
                <td>${list.ip}</td>
                <td>${list.ipBelong}</td>
                <td>${list.count}</td>
            </tr>
            `.trim();
            $tbody.append(templete);
        })
    })
}

//时间戳转日期格式
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    // M = ( date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    // D = ( date.getDate() < 10 ? '0'+ date.getDate() : date.getDate() ) + ' ';
    // h = ( date.getHours() < 10 ? '0'+ date.getHours() : date.getHours() )+ ':';
    // m = ( date.getMinutes() < 10 ? '0'+ date.getMinutes() : date.getMinutes() )+ ':';
    // s = ( date.getSeconds() < 10 ? '0'+ date.getSeconds() : date.getSeconds() );
    var M = (date.getMonth()+1).toString().padStart(2, '0') + '-';
    var D = date.getDate().toString().padStart(2, '0') + ' ';
    var h = date.getHours().toString().padStart(2, '0') + ':';
    var m = date.getMinutes().toString().padStart(2, '0') + ':';
    var s = date.getSeconds().toString().padStart(2, '0');

    return Y+M+D+h+m+s;
}

$(function () {
    //设置筛选条件
    $(".smart_filter").click(function () {
        $("#smart_filter_modal").attr("data-type",$(this).data('type'));
        $("#smart_filter_modal").attr("data-tag",$(this).data('tag'));
        tag = $(this).data("tag");
        $("#smart_filter_modal").show();
        $("html").addClass("modal-open");
        var data_tag=$(this).data("tag");
        switch (data_tag)
        {
            case "_c":
                $(".choose_os").hide();
                $(".choose_os").siblings().show();
                break;
            case "_d":
                $(".choose_browser").hide();
                $(".choose_browser").siblings().show();
                break;
            case "_e":
                $(".choose_city").hide();
                $(".choose_city").siblings().show();
                break;
            case "_f":
                $(".choose_inter").hide();
                $(".choose_inter").siblings().show();
                break;
            case "_h":
                $("#smart_filter_modal_body").find(".filter_tpl").show();
            case "_i":
                $("#smart_filter_modal_body").find(".filter_tpl").show();
        }
    });
    $('[data-dismiss="modal"]').click(function () {
        $("#smart_filter_modal").hide();
        $("html").removeClass("modal-open");
    });
    //点击设置发送select请求
    $(".send").click(function () {
        witchType=$("#smart_filter_modal").attr("data-type");
        equipment_val=$(".equipment").find("option:selected").val();
        browser_val=$(".browser").find("option:selected").val();
        inter_val=$(".inter").find("option:selected").val();
        status_val=$(".status").find("option:selected").val();
        prov_val=$(".pro").find("option:selected").text();
        console.log(prov_val);
        $("#smart_filter_modal").hide();
        $("html").removeClass("modal-open");

        switch (witchType) {
            case "C":
                comment(url,{shortUrl:myShort,witch:witchType,equipment:equipment_val,browser:browser_val,internet:inter_val,status:status_val,privonce:prov_val},optionOS,myChartOS,witchType);
                break;
            case "D":
                comment(url,{shortUrl:myShort,witch:witchType,equipment:equipment_val,browser:browser_val,internet:inter_val,status:status_val,privonce:prov_val},optionBrowser,myChartBrowser,witchType);
                break;
            case "E":
                comment(url,{shortUrl:myShort,witch:witchType,equipment:equipment_val,browser:browser_val,internet:inter_val,status:status_val,privonce:prov_val},optionChina,myChartChina,witchType);
                break;
            case "F":
                comment(url,{shortUrl:myShort,witch:witchType,equipment:equipment_val,browser:browser_val,internet:inter_val,status:status_val,privonce:prov_val},optionIsp,myChartIsp,witchType);
                break;
            case "H":
                log(url,{shortUrl:myShort,witch:witchType,equipment:equipment_val,browser:browser_val,internet:inter_val,status:status_val,privonce:prov_val});
                break;
            case "I":
                ipAnalyse(url,{shortUrl:myShort,witch:witchType,equipment:equipment_val,browser:browser_val,internet:inter_val,status:status_val,privonce:prov_val});
                break;
        }
    });
});
//判断是否为空
function isNull(str){
    if(str == '' || str == 'undefined' || str == null || str.length==0 || str == undefined){
        return true;
    }else{
        return false;
    }
}

$.post("/getProvinces",{},function (data) {
    for(var i=0;i<data.length;i++){
        var pro=`
            <option>
                ${data[i]["provinceName"]}
            </option>
        `.trim();
        $(".pro").append(pro);
    }
});
