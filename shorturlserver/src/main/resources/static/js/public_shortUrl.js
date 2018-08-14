function comment(url,parm,option,myChart,witch){
    $.post(url,parm,function (data) {
        var seriesArr;
        switch (witch) {
                // 7天内的访问统计
            case "A":
                option.xAxis.data=data.data.sevenDaysData.xAxis.split(",");
                seriesArr=data.data.sevenDaysData.series;
                for (var i=0;i<seriesArr.length;i++) {
                    option.series[i].data = seriesArr[i].split(",");
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
                // 访客设备分辨率信息分析
            // case "G":
            //     console.log(data)
        }
        myChart.setOption(option);
        if(witch == "E"||witch == "EC"){
        	myChartProvince.setOption(optionProvince)
        }
    })
}
var totalPage=1; //访问记录明细总页数
// /访问记录明细分页初始化
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
            url:'http://spring.yugyg.com:8901/data?shortUrl=qAjiMr&witch=H',
            type:'POST',
            data:{'page':page},
            dataType:'JSON',
            success:function (data) {
                if( data && data.result =="success" ) addLog(data);
                else alert("网络出问题了，请刷新后查看！");
            }
        })
    }
});

// 请求记录明细
function log(url,parms){
    $.post(url,parms,function(data){
        if( data && data.result =="success" ){
            addLog(data);
            console.log(data)
        }
        else alert("网络出问题了，请刷新后查看！");
    })
}
log("http://192.168.0.55:8901/data",{shortUrl:"qAjiMr",witch:"H"});

// 在页面添加记录明细
function addLog(data){
    data = data.data;
    totalPage = data.totalPage;//总页数
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
                <div class="refer_area">${list.searchlong}</div>
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
ipAnalyse("http://192.168.0.55:8901/data",{shortUrl:"qAjiMr",witch:"I"});


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
