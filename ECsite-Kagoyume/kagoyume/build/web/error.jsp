
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    kagoyumeHelper kh = kagoyumeHelper.getInstance(); // kagoyumeHelperクラスを呼び出す
    HttpSession hs = request.getSession(); // セッションの開始

    String name = "";
    boolean log = false;
    // ログインしている場合
    if (hs.getAttribute("login") != null) {
        log = true;
        // 名前表示のためにセッションから取り出す
        UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
        name = udd.getName();
    }

    // ログイン後のページ移行のために情報をセッションに格納
    hs.setAttribute("returnURL", "top");

%>

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
            <header>
                <% //ログイン,アウト表示分岐
                    if (!log) {
                %>
                <%= kh.login()%> 
                <% } else {%>
                <%= kh.logout(name)%>
                <% }%>
            </header>
            <h1>error!</h1>
            エラー理由:<%= request.getAttribute("error")%><br><br>
            <hr>
            <%= kagoyumeHelper.getInstance().top()%>
        </div>
    </body>
</html>
