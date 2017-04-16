<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="../resources/vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <![endif]-->

</head>
<script>
    function search() {
        document.getElementById('search').submit();
    }
</script>
<body>

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
                                <input id = "key" name = "key" type="text" style="width: 140px"class="form-control" placeholder="Search...">
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
<!-- /#wrapper -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/resources/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="${pageContext.request.contextPath}/resources/vendor/raphael/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/morrisjs/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/data/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/resources/dist/js/sb-admin-2.js"></script>

</body>

</html>

