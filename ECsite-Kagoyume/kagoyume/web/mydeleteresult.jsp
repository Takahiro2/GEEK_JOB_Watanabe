
<%@page import="kagoyume.kagoyumeHelper"%>
<%
    kagoyumeHelper kh = kagoyumeHelper.getInstance();
    HttpSession hs = request.getSession();
    
    // ログイン後のページ遷移先を記録
    hs.setAttribute("returnURL", "mydeleteresult");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
        <title>かごゆめ</title>
    </head>
    <body>
        <div class="container" align="center">
            <h1>削除しました</h1>
            <br>
            <%= kh.top()%>
        </div>
    </body>
</html>
