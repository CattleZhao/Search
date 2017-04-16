<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/resources/css/pagination.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/result.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon" rel="icon">
<link href="../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="../resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="../resources/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="../resources/vendor/morrisjs/morris.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/metisMenu/metisMenu.min.js"></script>
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
<table style="display: none">
    <tr>
        <%--<a id="resultLogo" href="${pageContext.request.contextPath}">--%>
            <%--<img alt="到ask首页" title="到ask首页" src="${pageContext.request.contextPath}/resources/img/logo.png"/>--%>
        <%--</a>--%>
        <form action="${pageContext.request.contextPath }/s/showResult" method="get">
        <div style="white-space: nowrap" class="frm">
		  <span class="inputContent">
			<input id="key" type="text" name="key" value="${key}" class="searchFrame" style="display: none"/>
		  </span>
            <span class="resultSearch">
			<input id="sub" type="submit" name="search" class="searchSubmit" value="搜&nbsp;索" style="display: none"/>
		  </span>
        </div>
        </form>
    </tr>
</table>
<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">企业监管搜索引擎</a>
        </div>
        <!-- /.navbar-header -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <form id = "search" action="${pageContext.request.contextPath }/s/showResult" method="get">
                                <input id = "key" name = "key" type="text" style="width: 140px"class="form-control" value="${key}">
                                <span class="input-group-btn">
                                <button class="btn btn-default" onclick="search()">search</button>
                                </span>
                            </form>
                        </div>
                        <!-- /input-group -->
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/s/showHome">首页</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/s/showNews">企业新闻</a>
                    </li>
                    <li>
                        <a href="tables.html">Tables</a>
                    </li>
                    <li>
                        <a href="forms.html">Forms</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>


</div>
<div id="page-wrapper" style="height:1000px;">
<div style="position: absolute;
        left: 22%;">
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
