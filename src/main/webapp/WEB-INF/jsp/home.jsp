<%@ page import="net.minidev.json.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: Scorpion
  Date: 2017/4/14
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/resources/js/echarts.min.js"></script>
<html>
<head>
    <title>Home</title>
</head>
<style>
    .echart_container{
        height: 600px;

        position: absolute;
        left: 22%;
        width: 80%;
        margin-bottom: 20px;
    }
</style>
<body>
<jsp:include page="title.jsp"></jsp:include>
<div id="page-wrapper">
<div id="main" class="echart_container"></div>
</div>
<% String str = (String) request.getAttribute("jsonObject");
%>
<script>
    var obj = '<%=str%>';
    var json = JSON.parse(obj);
    var nameList = json.nameList;
    var numList = json.numList;

</script>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));

    option = {
        title: {
            text: '各个公司新闻量(总量前20)',
            subtext: '数据来自百度新闻爬取'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['新闻量']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: nameList
        },
        series: [
            {
                itemStyle: {
                    normal: {color: '#FFB5C5'}
                },
                name: '新闻量',
                type: 'bar',
                data: numList
            }
        ]
    };

    myChart.setOption(option);
</script>
</body>
</html>
