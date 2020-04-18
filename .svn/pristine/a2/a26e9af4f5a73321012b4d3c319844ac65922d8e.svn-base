<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>我的工作台</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/resource/js/echarts/echarts.js"></script>

</head>
  
  <body >
  <div style="width:100%;height:100%;">
	<div id="main" style="width:50%;height:45%;float: left;border:5px ;"></div> 
		<script type="text/javascript">
  		   // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        var option = {
    title: {
        text: '漏斗图',
        subtext: '纯属虚构',
        left: 'left',
        top: 'bottom'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}%"
    },
    toolbox: {
        orient: 'vertical',
        top: 'center',
        feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
        }
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['展现','点击','访问','咨询','订单']
    },
    calculable: true,
    series: [
        {
            name: '漏斗图',
            type: 'funnel',
            width: '40%',
            height: '45%',
            left: '5%',
            top: '50%',
            data:[
                {value: 60, name:'访问'},
                {value: 30, name:'咨询'},
                {value: 10, name:'订单'},
                {value: 80, name:'点击'},
                {value: 100, name:'展现'}
            ]
        },
        {
            name: '金字塔',
            type: 'funnel',
            width: '40%',
            height: '45%',
            left: '5%',
            top: '5%',
            sort: 'ascending',
            data:[
                {value: 60, name:'访问'},
                {value: 30, name:'咨询'},
                {value: 10, name:'订单'},
                {value: 80, name:'点击'},
                {value: 100, name:'展现'}
            ]
        },
        {
            name: '漏斗图',
            type:'funnel',
            width: '40%',
            height: '45%',
            left: '55%',
            top: '5%',
            label: {
                normal: {
                    position: 'left'
                }
            },
            data:[
                {value: 60, name: '访问'},
                {value: 30, name: '咨询'},
                {value: 10, name: '订单'},
                {value: 80, name: '点击'},
                {value: 100, name: '展现'}
            ]
        },
        {
            name: '金字塔',
            type:'funnel',
            width: '40%',
            height: '45%',
            left: '55%',
            top: '50%',
            sort: 'ascending',
            label: {
                normal: {
                    position: 'left'
                }
            },
            data:[
                {value: 60, name: '访问'},
                {value: 30, name: '咨询'},
                {value: 10, name: '订单'},
                {value: 80, name: '点击'},
                {value: 100, name: '展现'}
            ]
        }
    ]
};
  			// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
  	
  	
  	</script>

  	<div id="main1" style="width:50%;height:45%;float: left;border:1px;"></div>
  	<script type="text/javascript">
     // 基于准备好的dom，初始化echarts实例
      var myChart1 = echarts.init(document.getElementById('main1'));
	  var option = {
		    title : {
		        text: '某站点用户访问来源',
		        subtext: '纯属虚构',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:335, name:'直接访问'},
		                {value:310, name:'邮件营销'},
		                {value:234, name:'联盟广告'},
		                {value:135, name:'视频广告'},
		                {value:1548, name:'搜索引擎'}
		            ],
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
        myChart1.setOption(option);
    </script>
  	<div  id="main2"  style="width:50%;height:45%;float: left;border:1px solid #F00;border:1px;"></div>
  	<script type="text/javascript">
  		 // 基于准备好的dom，初始化echarts实例
      var myChart2 = echarts.init(document.getElementById('main2'));
	  var option = {
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['周一','周二','周三','周四','周五','周六','周日']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'邮件营销',
            type:'line',
            stack: '总量',
            data:[120, 132, 101, 134, 90, 230, 210]
        },
        {
            name:'联盟广告',
            type:'line',
            stack: '总量',
            data:[220, 182, 191, 234, 290, 330, 310]
        },
        {
            name:'视频广告',
            type:'line',
            stack: '总量',
            data:[150, 232, 201, 154, 190, 330, 410]
        },
        {
            name:'直接访问',
            type:'line',
            stack: '总量',
            data:[320, 332, 301, 334, 390, 330, 320]
        },
        {
            name:'搜索引擎',
            type:'line',
            stack: '总量',
            data:[820, 932, 901, 934, 1290, 1330, 1320]
        }
    ]
};
  		// 使用刚指定的配置项和数据显示图表。
        myChart2.setOption(option);
  	
  	</script>
  	<div  id="main3"  style="width:50%;height:45%;float: left;border:1px;"></div>
  <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart3 = echarts.init(document.getElementById('main3'));

        var option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎','百度','谷歌','必应','其他']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['周一','周二','周三','周四','周五','周六','周日']
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
		            data:[320, 332, 301, 334, 390, 330, 320]
		        },
		        {
		            name:'邮件营销',
		            type:'bar',
		            stack: '广告',
		            data:[120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name:'联盟广告',
		            type:'bar',
		            stack: '广告',
		            data:[220, 182, 191, 234, 290, 330, 310]
		        },
		        {
		            name:'视频广告',
		            type:'bar',
		            stack: '广告',
		            data:[150, 232, 201, 154, 190, 330, 410]
		        },
		        {
		            name:'搜索引擎',
		            type:'bar',
		            data:[862, 1018, 964, 1026, 1679, 1600, 1570],
		            markLine : {
		                lineStyle: {
		                    normal: {
		                        type: 'dashed'
		                    }
		                },
		                data : [
		                    [{type : 'min'}, {type : 'max'}]
		                ]
		            }
		        },
		        {
		            name:'百度',
		            type:'bar',
		            barWidth : 5,
		            stack: '搜索引擎',
		            data:[620, 732, 701, 734, 1090, 1130, 1120]
		        },
		        {
		            name:'谷歌',
		            type:'bar',
		            stack: '搜索引擎',
		            data:[120, 132, 101, 134, 290, 230, 220]
		        },
		        {
		            name:'必应',
		            type:'bar',
		            stack: '搜索引擎',
		            data:[60, 72, 71, 74, 190, 130, 110]
		        },
		        {
		            name:'其他',
		            type:'bar',
		            stack: '搜索引擎',
		            data:[62, 82, 91, 84, 109, 110, 120]
		        }
		    ]
		};

        // 使用刚指定的配置项和数据显示图表。
        myChart3.setOption(option);
    </script>
  </div>
  
  	
    
   
  
   <!--   <table width="100%" border="0" cellspacing="5" cellpadding="0"  >
   <tr>
    <td style="width:50%;height:100%">
    为ECharts准备一个具备大小（宽高）的Dom
    <div span id="main"  style="width:50%;height:100%;" ></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        var option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎','百度','谷歌','必应','其他']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['周一','周二','周三','周四','周五','周六','周日']
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
		            data:[320, 332, 301, 334, 390, 330, 320]
		        },
		        {
		            name:'邮件营销',
		            type:'bar',
		            stack: '广告',
		            data:[120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name:'联盟广告',
		            type:'bar',
		            stack: '广告',
		            data:[220, 182, 191, 234, 290, 330, 310]
		        },
		        {
		            name:'视频广告',
		            type:'bar',
		            stack: '广告',
		            data:[150, 232, 201, 154, 190, 330, 410]
		        },
		        {
		            name:'搜索引擎',
		            type:'bar',
		            data:[862, 1018, 964, 1026, 1679, 1600, 1570],
		            markLine : {
		                lineStyle: {
		                    normal: {
		                        type: 'dashed'
		                    }
		                },
		                data : [
		                    [{type : 'min'}, {type : 'max'}]
		                ]
		            }
		        },
		        {
		            name:'百度',
		            type:'bar',
		            barWidth : 5,
		            stack: '搜索引擎',
		            data:[620, 732, 701, 734, 1090, 1130, 1120]
		        },
		        {
		            name:'谷歌',
		            type:'bar',
		            stack: '搜索引擎',
		            data:[120, 132, 101, 134, 290, 230, 220]
		        },
		        {
		            name:'必应',
		            type:'bar',
		            stack: '搜索引擎',
		            data:[60, 72, 71, 74, 190, 130, 110]
		        },
		        {
		            name:'其他',
		            type:'bar',
		            stack: '搜索引擎',
		            data:[62, 82, 91, 84, 109, 110, 120]
		        }
		    ]
		};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    </td>
    <td style="width:50%;">
    <div id="main1" style="width:100%;height:500px;"></div>
    <script type="text/javascript">
     // 基于准备好的dom，初始化echarts实例
      var myChart1 = echarts.init(document.getElementById('main1'));
	  var option = {
		    title : {
		        text: '某站点用户访问来源',
		        subtext: '纯属虚构',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:335, name:'直接访问'},
		                {value:310, name:'邮件营销'},
		                {value:234, name:'联盟广告'},
		                {value:135, name:'视频广告'},
		                {value:1548, name:'搜索引擎'}
		            ],
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
        myChart1.setOption(option);
    </script>
    </td>
    </tr>
    <tr>
    <td style="width:50%;height:50%;">

    </td>
    <td style="width:50%;height:50%;">
    </td>
    </tr>
    </table> -->
  </body>
</html>
