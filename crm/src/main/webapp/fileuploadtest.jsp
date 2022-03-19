<%--
  Created by IntelliJ IDEA.
  User: yyh
  Date: 2022/3/19
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>演示文件上传</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.11.1-min.js"></script>
</head>
<body>
<%--
    文件上传三个条件
        1.表单组件只能用 file
        2.文件上传只能post
        3.编码格式只能用multipart/form-data
            默认的为urlencoded 只能对文本数据进行编码
--%>
<form action="${pageContext.request.contextPath}/workbench/activity/fileUpload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="myFile"><br>
    <input type="text" name="userName"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
