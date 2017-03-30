<%--
  Created by IntelliJ IDEA.
  User: Scorpion
  Date: 2017/3/20
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/resources/css/base.css" rel="stylesheet"/>
<LINK href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon" rel=icon>
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.2.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/base.js" type="text/javascript"></script>
<html>
<head>
    <meta charset="utf-8"/>
    <title>企业新闻搜索引擎</title>
</head>
<body>
<div id="content">
    <div class="divCenter">
        <form action="${pageContext.request.contextPath }/s/showResult" method="get">
            <div style="white-space: nowrap"><span class="spanSearch">
			<input id="key" class="indexSearch" type="text" vertical-align="middle" name="key"/>
			</span>
                <span>
			<input id="sub" class="indexButton" type="submit" name="search" value="搜&nbsp;&nbsp;索"/>
			</span>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#sub").click(function () {
        if (!$("#key").val().trim()) {
            return false;
        }
    });
</script>
</html>
