<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%

    kagoyumeHelper jh = kagoyumeHelper.getInstance();
    HttpSession hs = request.getSession();
    String name = "";
    //ログイン状態処理(cart.jspと同じく、ログイン状態のはずだが)
    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;
        //名前表示のためにセッションから取り出す
        UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
        name = udd.getName();
    }

    // ログイン後のページ移行のために情報をセッションに格納
    hs.setAttribute("returnURL", "buycomplete");

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
            <header>

                <hr>
                <% //ログイン,アウト表示分岐
                    if (!log) {
                %>
                <%= jh.login()%> 
                <% } else {%>
                <%= jh.logout(name)%>
                <% }%>
            </header>
            <main>
                <strong>ありがとうございました！</strong>
                <br>
                <%= jh.top()%><br>
            </main>
        </div>

    </body>
</html>
