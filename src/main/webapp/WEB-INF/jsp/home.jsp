<%--
  Created by IntelliJ IDEA.
  User: tonyn
  Date: 2017/10/5
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>开锁</title>
    <meta name="viewport" charset="UTF-8" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
    <link href="${pageContext.request.contextPath}/css/styless.css" rel='stylesheet' type='text/css' />
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!--webfonts
    <link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
    -->

    <!--//webfonts-->

</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>$(document).ready(function(c) {
    $('.close').on('click', function(c){
        $('.login-form').fadeOut('slow', function(c){
            $('.login-form').remove();
        });
    });
});
</script>
<!--SIGN UP-->
<h1>智能资源管理系统</h1>
<div class="login-form">
    <div class="close"> </div>
    <div class="head-info">
        <label class="lbl-1"> </label>
        <label class="lbl-2"> </label>
        <label class="lbl-3"> </label>
    </div>
    <div class="clear"> </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">开锁成功！</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <strong>可以取出您想要的文件了！</strong>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
    <div class="avtar">
        <img src="${pageContext.request.contextPath}/images/avtar.png" />
    </div>
    <form action="${pageContext.request.contextPath}/UserAction/authority" method="post">
        <input type="text" class="text" value="您想要打开的柜子" id="auth" name="authority" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'case';}" >
        <button type="button" class="btn btn-lg btn-success btn-block" id="open">一键开锁</button>
    </form>
</div>
<script type="text/javascript">
    /*var authButton = document.getElementById("open");
    authButton.onclick = function () {
        alert("成功！");
        $('alert1').display('block');
        location.href = '' + 'index.jsp';
    };*/

    $(document).ready(function() {
        //点击注册按钮
        $('#open').click(function() {
            //提交注册表单
            $.post('${pageContext.request.contextPath}/UserAction/authority',
                {
                    authority: $('#auth').val()
                },
                function(data, status) {
                    if (data == '0') {
                        createPopOver('#auth', 'right', '您没有开锁权限', 'show');
                    }else if (data == '1') {
                        //$('#auth').val('');
                        //$('#myModal').modal('hide');
                        //$('#form').css('margin-top', '0px');
                        createPopOver('#auth', 'right', '开锁成功！', 'show');
                    }
                });
        });

        //显示弹出框
        function createPopOver(id, placement, content, action) {
            $(id).popover({
                placement: placement,
                content: content
            });
            $(id).popover(action);
        }

        //点击输入框时销毁弹出框
        $('#auth').click(function() {
            $('#auth').popover('destroy');
        });

    });
</script>
</body>
</html>