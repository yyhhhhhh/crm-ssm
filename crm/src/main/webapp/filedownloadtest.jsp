<%--
  Created by IntelliJ IDEA.
  User: yyh
  Date: 2022/3/18
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>演示文件下载</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.11.1-min.js"></script>
    <script>
        $(function(){
            $('#fileDownloadBtn').click(function(){
                alert("hello")
                //文件下载请求——>同步请求
                window.location.href =
                    "${pageContext.request.contextPath}/workbench/activity/fileDownload.do"
            })
        })
    </script>
</head>
<body>
<input type="button" value="下载" id="fileDownloadBtn">
</body>
</html>
