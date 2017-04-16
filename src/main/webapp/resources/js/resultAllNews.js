$(function(){
    var doc = window.doc = {
        dync: {
            cp: 1		//当前页码
        },
        bindFunc: function() {
            bind();
        },
        init: function() {
            doc.bindFunc();
        }
    };

    doc.init();
//	alert(doc.dync.key);
    queryData(1);
});


function pager(page, totalPages, totalRecords) {
    pagination.generPageHtml({
        pno: page,
        totalPages: totalPages,
        totalRecords: totalRecords,
        click: function(n) {
            this.selectPage(n);
            doc.dync.cp = n;
            queryData(n);
        }
    }, true);
}


function queryData(page) {
    $.ajax({
        cache: false,
        url: "http://localhost:8080/s/queryNews",
        type: "GET",
        dataType: "json",
        data: {page: page},
        async: true,
        success: function(data) {
            //$("#key").val(key);
            var html = "";
            $("#records").text(data.totalRecords);
            if(!data.totalRecords) {
                $("#result").html("");
                $("#content_none").show();
                pager(page, data.totalPages, data.totalRecords);
                return;
            }
            $("#content_none").hide();
            html = template("rslt", data);
            html = changeHtml(html);
            $("#result").html(html);
            winHistory();
            pager(page, data.totalPages, data.totalRecords);
        },
        erroor: function() {
            alert("啦啦啦o(^▽^)o出错啦");
        }
    });
}

function changeHtml(html) {
    html = html.replace(/&lt;/gi, "<");
    html = html.replace(/&gt;/gi, ">");
    //html = html.replace(/&nbsp;/gi, "");
    return html;
}

window.onpopstate = function(e) {
    document.title = e.state.title;
    $.get(e.state.url).done(queryData(1));
};

//浏览器历史状态
function winHistory() {
    var title = doc.dync.key+"-ask搜索";
    document.title = title;
    var url = ctx+"/s/queryNews";
    var state = {title: title, url: url};
    history.replaceState(state, title, url);
}

function bind() {
    var url = window.location.href;
    // var keyword = decodeURI(url.substring(url.indexOf("=")+1, url.indexOf("&")));
    // $("#key").val(key);

    //响应回车事件
    $(document).keyup(function(event) {
        if(event.keyCode == 13) {
            queryData(1);
        }
    });

    //动态查询
    $("#key").livesearch({
        searchCallback: queryData,
        innerText: "",
        queryDelay: 500,
        minimumSearchLength: 1
    });

    //点击查询
    $("#sub").click(function() {
        queryData(1);
    });

}/**
 * Created by Scorpion on 2017/3/20.
 */

