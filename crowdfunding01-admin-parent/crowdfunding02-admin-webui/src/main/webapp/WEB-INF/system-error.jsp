<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<title>出错啦！</title>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $("button#btn1").click(function () {
                window.history.back();
            });
            
            $("button#login").click(function () {
                //（待完善）返回登录页
            })
        });
    </script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <h1 style="text-align: center;">请求出现了错误QAQ</h1>
    <h2 style="text-align: center;">错误消息：${requestScope.exception.message}</h2>
    <br/><br/>
    <button style="width:150px;margin: 0 auto;" id="btn1" class="btn btn-lg btn-success btn-block">返回上一步</button>
    <button style="width:150px;margin: 0 auto;" id="back_login" class="btn btn-lg btn-success btn-block">返回登录页</button>
</div>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>