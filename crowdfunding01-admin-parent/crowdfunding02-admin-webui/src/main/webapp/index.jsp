<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>SSM整合测试</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">

        // 第一种方式
        $(function () {
            $("#btn1").click(function () {
              $.ajax({
                  "url":"test/array/one.html",
                  "type":"post",
                  "data":{
                      "array":[5,8,9]
                  },
                  "dataType":"json",        //如何对待服务器端返回数据
                  "success":function (response) {
                      alert("请求成功"+response);
                  },
                  "error":function (response) {
                      alert("请求失败"+response.message);
                  }
              });
            });
        });

        //第二种方式
        $(function () {
            $("#btn2").click(function () {
                $.ajax({
                    "url":"test/array/one.html",
                    "type":"post",
                    "data":{
                        "array":[5,8,9]
                    },
                    "dataType":"text",        //如何对待服务器端返回数据
                    "success":function (response) {
                        alert("请求成功"+response);
                    },
                    "error":function (response) {
                        alert("请求失败"+response.message);
                    }
                });
            });

            $("#btn4").click(function () {
                layer.msg("Layer PopUp!");
            });
        });


    </script>
</head>

<body>

<a href="test/ssm.html">测试SSM整合环境</a>
<br>

<button id="btn1">Send [5,8,9] one</button>
<button id="btn2">Send [5,8,9] two</button>
<button id="btn3">Send Object</button>
<button id="btn4">Layer Popup!</button>
</body>
</html>
