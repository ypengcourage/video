<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录界面</title>
    <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
    </head>
	<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div class="container theme-showcase" role="main">
	<br><br><br><br><br><br><br><br><br>
    <div id="main" style="height:600px;"></div>
    <!-- ECharts单文件引入 -->
    <script src="${pageContext.request.contextPath }/static/echarts/echarts-all.js"></script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['课程平均播放次数']
            },
            xAxis : [
                {
                    type : 'category',
                    data : ${courseName}
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name": "平均播放次数(times)",
                    "type": "bar",
                    "data": ${times}
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
</div>
</body>
</html>