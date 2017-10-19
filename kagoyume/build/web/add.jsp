<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    kagoyumeHelper kh = kagoyumeHelper.getInstance();
    HttpSession hs = request.getSession();

    //名前表示のためにセッションから取り出す
    UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");

    
    //ログイン状態処理
    String name = "";
    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;

        name = udd.getName();
    }

    //login後のページ移行のために情報をセッションに格納
    hs.setAttribute("returnURL", "search");
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
                <div class="container" align="center">
                    <% //ログイン,アウト表示分岐
                        if (!log) {
                    %>
                    <%= kh.login()%> 
                    <% } else {%>
                    <%= kh.logout(name)%>
                    <% }%>
            </header>

            <hr>

            <h1>カートに追加しました。</h1>
            <div><li><a href="cart">Cart</a></li></div><br>
            <div><li><%= kh.top()%></li></div>
        </div>
    </body>
</html>
