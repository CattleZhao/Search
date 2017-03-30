<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/resources/css/pagination.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/result.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon" rel="icon">
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.2.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/pagination.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/livesearch/jquery.livesearch.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/result.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/template/template.js" type="text/javascript"></script>
<html>
<head>
    <meta charset="utf-8"/>
    <title>搜索结果</title>
</head>
<body>
<table id="global">
    <tr>
        <a id="resultLogo" href="${pageContext.request.contextPath}">
            <img alt="到ask首页" title="到ask首页" src="${pageContext.request.contextPath}/resources/img/logo.png"/>
        </a>
        <form action="${pageContext.request.contextPath }/s/showResult" method="get">
        <div style="white-space: nowrap" class="frm">
		  <span class="inputContent">
			<input id="key" type="text" name="key" value="${key}" class="searchFrame"/>
		  </span>
            <span class="resultSearch">
			<input id="sub" type="submit" name="search" class="searchSubmit" value="搜&nbsp;索"/>
		  </span>
        </div>
        </form>
    </tr>
</table>
<div id="content-left">
    <div id="toptotal">
        找到约 <span id="records">0</span> 条结果
        <nobr>（用时 <span id="usedtime">0</span> 秒）</nobr>
    </div>
    <ul id="result">
        <!--  <div>
             <div id="title"><h3>
                 <li><a></a></li></h3>
             </div>
             <div id="abstract">
               <span></span>
             </div>
         </div> -->
    </ul>
    <div id="content_none" style="display:none">
        <div class="nors">
            <p>很抱歉，找不到和您的查询
                <span style="font-family:宋体">“</span>
                <em id="norkey"></em>
                <span style="font-family:宋体">”</span>
                相符的内容或信息。
            </p><br>
            <div class="tip_head">温馨提示：</div>
            <ul>
                <li>请检查您的输入是否正确。</li>
                <li>请尝试其他查询词。</li>
                <li>请改用较常见的字词。</li>
            </ul>
        </div>
        <br clear="all">
    </div>
    <div id="page" class="pagination">
        <!-- <a href="#" class="np">&lt;上一页</a>
        <strong><span class="pg">1</span></strong>
        <a href="#" onclick="return false"><span class="pg">2</span></a>
        <a href="#"><span class="pg">3</span></a>
        <a href="#"><span class="pg">4</span></a>
        <a href="#"><span class="pg">5</span></a>
        <a href="#"><span class="pg">6</span></a>
        <a href="#"><span class="pg">7</span></a>
        <a href="#"><span class="pg">8</span></a>
        <a href="#"><span class="pg">9</span></a>
        <a href="#"><span class="pg">10</span></a>
        <a href="#" class="np">下一页&gt;</a> -->
    </div>
</div>
</body>
<script type="text/javascript">
    var ctx = "${pageContext.request.contextPath}";
    var key = $("#key").val();
</script>

<script id="rslt" type="text/html">

    {{each resultList as hit i}}
    <div>
        <div id="title">
            <h3 class="t">
                <li class="r_li"><a href="{{hit.url}}" target="_blank">{{hit.title}}</a></li>
            </h3>
        </div>
        <div id="showurl">
            <span class="showurl">{{hit.url}}</span>
        </div>
        <div class="abstract">


            <span>{{hit.description}}</span>
        </div>
        <div class="abstract">
            <span id="date">{{hit.date}}</span>
        </div>
    </div>
    {{/each}}
</script>
</html>
