
var vm=new Vue({
    el:'#urlBody',
    data:{
        items:[],
        longUrl:'',
        shortUrl:'',
        desc:'',
        currentPage:1,
        totalPage:'' ,
        isShowPage:false,
        noneShort:false,
        page:'',
        startTimeOption: {},
        endTimeOption: {},
        start:'',
        end:'',
        requestUrl : ''
        },
    created:function(){
        this.getList();
        },
    mounted() {
        this.start = '';
        this.end = '';
        this.onStartTimeChange(this.start);
        this.onEndTimeChange(this.end)
        },
    methods:{
        //获取列表
        getList:function(){
            that = this;   
            this.requestUrl = "/getAllLink";
            axios.get(this.requestUrl,{
            	  params: {
            		  "longUrl":this.longUrl,
                      "shortUrl":this.shortUrl,
                      "desc":this.desc,
                      "start":this.isNull(this.start)?'':this.timestampToTime(this.transferTime(this.start)),
                      "end":this.isNull(this.end)?'':this.timestampToTime(this.transferTime(this.end)),
                      "currentPage":this.currentPage
            		  }
            		 })
            	.then((res)=> {
	                console.log(res);
	                if(that.isNull(res.data) || res.data.data.data.length==0){
	                    that.noneShort = true;
	                     //隐藏分页
	                     that.isShowPage = false;
	                     that.items = '';
	                }else{
	                    that.noneShort = false;
	                    that.items = res.data.data.data;
	                    that.totalPage = res.data.data.totalPage;
	                    if(res.data.data.totalPage>1){
	                        that.isShowPage = true;
	                    }else{
	                        that.isShowPage = false;
	                    }
	                }
	            }).catch((error)=> {
	            	 that.isShowPage = false;
	                 alert("请求出错");
	            }); 
        },
        //时间转换
        timeChange(time){
            return this.timestampToTime(time); 
        },
        //时间转换(date=>timeStamp=>dateString)
        timeChangeOld(time){
        	return this.timestampToTime(new Date(time).getTime()/1000); 
        },
        // 点击分页
        changePage(val){
            this.currentPage = val;
            this.getList();
        },
        btnClick: function(data){//页码点击事件
            if(data != this.currentPage){
                this.currentPage = data
                this.getList(); 
            }
        },
        pageClick: function(){
            console.log('现在在'+this.currentPage+'页');
            this.getList(); 
        },
        onStartTimeChange(start, type) {
            this.endTimeOption = {
              disabledDate(end) {
                return end <= new Date(start) 
              }
            }
        },
        onEndTimeChange(end, type) {
            this.startTimeOption = {
            //   disabledDate(start) {
            //     return start > new Date(end) || start > Date.now()
            //   }
            }
        },
            //时间戳转日期格式
        timestampToTime(timestamp) {
            timestamp += '';
            if(timestamp.length == 10){
                timestamp = Number(timestamp)*1000;
            }else{
                timestamp = Number(timestamp);
            }
            var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            var Y = date.getFullYear() + '-',
            M = ( date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-',
            D = ( date.getDate() < 10 ? '0'+ date.getDate() : date.getDate() ) + ' ',
            h = ( date.getHours() < 10 ? '0'+ date.getHours() : date.getHours() )+ ':',
            m = ( date.getMinutes() < 10 ? '0'+ date.getMinutes() : date.getMinutes() )+ ':',
            s = ( date.getSeconds() < 10 ? '0'+ date.getSeconds() : date.getSeconds() );
            return Y+M+D+h+m+s;
        },
        //转换时间到时间戳,毫秒
        transferTime(date){
            if(this.isNull(date)){
                return  0;
            }else{
                return  new Date(date).getTime();
            }
        },
        //判断是否为空
        isNull(str) {
            if (str == '' || str == 'undefined' || str == null || str.length == 0 || str == undefined || str == "null") {
                return true;
            } else {
                return false;
            }
        }
    },
    computed: {
        indexs: function(){
          var left = 1;
          var right = this.totalPage;
          var ar = [];
          if(this.totalPage>= 5){
            if(this.currentPage > 3 && this.currentPage < this.totalPage-2){
                    left = this.currentPage - 2;
                    right = this.currentPage + 2;
            }else{
                if(this.currentPage<=3){
                    left = 1;
                    right = 5;
                }else{
                    right = this.totalPage;
                    left = this.totalPage -4;
                }
            }
         }
        while (left <= right){
            ar.push(left);
            left ++;
        }
        return ar;
       }
         
    },
    watch: {
        start: function(){
            this.currentPage = 1;
        },
        end: function(){
            this.currentPage = 1;
        },
        desc: function(){
            this.currentPage = 1;
        },
        shortUrl: function(){
            this.currentPage = 1;
        },
        longUrl: function(){
            this.currentPage = 1;
        }
    }
  });
    