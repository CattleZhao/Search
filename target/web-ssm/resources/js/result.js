$(function(){
    var doc = window.doc = {
        dync: {
            cp: 1,		//当前页码
            key: key
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
    queryData(doc.dync.key, 1);
});


function pager(page, totalPages, totalRecords) {
    pagination.generPageHtml({
        pno: page,
        totalPages: totalPages,
        totalRecords: totalRecords,
        click: function(n) {
            this.selectPage(n);
            doc.dync.cp = n;
            queryData(doc.dync.key, n);
        }
    }, true);
}


function queryData(key, page) {
    if(!key.trim()) {
        return;
    }
    $.ajax({
        cache: false,
        url: ctx+"/s/query",
        type: "GET",
        dataType: "json",
        data: {key: key, page: page},
        async: true,
        success: function(data) {
            //$("#key").val(key);
            doc.dync.key = key;
            var html = "";
            $("#records").text(data.totalRecords);
            $("#usedtime").text(data.usedTime);
            if(!data.totalRecords) {
                $("#result").html("");
                $("#content_none").show();
                $("#norkey").text(key);
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
    $("#key").val(e.state.key);
    $.get(e.state.url).done(queryData(e.state.key, 1));
};

//浏览器历史状态
function winHistory() {
    var title = doc.dync.key+"-ask搜索";
    document.title = title;
    var url = ctx+"/s/showResult?key="+doc.dync.key+"&search=ask";
    var state = {title: title, url: url, key: doc.dync.key};
    history.replaceState(state, title, url);
}

function bind() {
    var url = window.location.href;
    var keyword = decodeURI(url.substring(url.indexOf("=")+1, url.indexOf("&")));
    $("#key").val(key);

    //响应回车事件
    $(document).keyup(function(event) {
        if(event.keyCode == 13) {
            doc.dync.key = $("#key").val();
            queryData(doc.dync.key, 1);
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
        doc.dync.key = $("#key").val();
        queryData(doc.dync.key, 1);
    });

}/**
 * Created by Scorpion on 2017/3/20.
 */
