<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="${pageContext.request.contextPath}/image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2022&nbsp;YYH</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="${pageContext.request.contextPath}/workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" id="loginAct" type="text" value="${cookie.loginAct.value}" placeholder="用户名">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" id="loginPwd" type="password" value="${cookie.loginPwd.value}" placeholder="密码">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						<label>
							<c:if test="${not empty cookie.loginAct and not empty cookie.loginPwd}">
								<input type="checkbox" id="isRemPwd" checked>
							</c:if>
							<c:if test="${empty cookie.loginAct or empty cookie.loginPwd}">
								<input type="checkbox" id="isRemPwd">
							</c:if>
							 十天内免登录
						</label>
						&nbsp;&nbsp;&nbsp;
						<span id="msg"></span>
					</div>
					<button id="loginBtn" type="button" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
<script>
	$(function(){
		// 键盘按下事件(整个浏览器)
		$(window).keyup(function(e){
			if(e.keyCode === 13){
				$('#loginBtn').click() // 模拟发起单击事件
			}
		})

		$('#loginBtn').click(function(){
			let loginAct = $.trim($('#loginAct').val())
			let loginPwd = $.trim($('#loginPwd').val())
			let isRemPwd = $('#isRemPwd').prop('checked')
			if(!loginAct){
				alert('用户名不能为空')
				return
			}
			if(!loginPwd){
				alert('密码不能为空')
				return
			}
			//收集表单参数
			$.ajax({
				url : '${pageContext.request.contextPath}/settings/qx/user/login.do',
				data : {
					loginAct : loginAct,
					loginPwd : loginPwd,
					isRemPwd : isRemPwd
				},
				type : 'post',
				dataType : 'json',
				success : function (data){
					if(data.code === '1'){ //跳转到业务页面
						window.location.href = '${pageContext.request.contextPath}/workbench/index.do'
					}else{
						$('#msg').html(data.message)
					}
				},
				beforeSend : function (){
					// 在ajax向后台发送请求之前,自动执行函数
					// 该函数返回值能够决定ajax是否真正向后台发送请求
					// 返回true,ajax真正向后台发送请求
					// 返回false,ajax不能向后台发送请求
					$('#msg').html('正在努力验证...')
					return true
				}
			})
		})
	})
</script>
</body>
</html>