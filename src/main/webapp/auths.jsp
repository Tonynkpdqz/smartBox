<%--
  Created by IntelliJ IDEA.
  User: tonyn
  Date: 2017/11/2
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <script type="text/javascript">
        var url = 'ws://' + window.location.host + '<%=request.getContextPath()%>/auths';
        var sock = new WebSocket(url);
        sock.onopen = function () {
            console.log('kaiqilianjie');

        }
        sock.onmessage = function (e) {
            console.log(e.data);
        }
    </script>
</head>
<body>
    hello world!
</body>
</html>
