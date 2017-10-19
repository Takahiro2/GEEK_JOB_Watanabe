
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.UserData"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession hs = request.getSession();
    kagoyumeHelper kh = kagoyumeHelper.getInstance();
    UserData udb = null;

    boolean reinput = false;
    if (request.getParameter("mode") != null && request.getParameter("mode").equals("reinput")) {
        reinput = true;
        udb = (UserData) hs.getAttribute("ud");
    }

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

    // ログイン後のページ遷移先を記録
    hs.setAttribute("returnURL", "registration");
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
            <h2>新規登録フォーム</h2><br>
            <hr>
            <h3>12文字以内での登録をお願いします。</h3>
            <% if (hs.getAttribute("registered") != null) { %>
            <div class="alert-danger">
                <h4>このユーザ名はすでに登録されています。</h4>
            </div>
            <% hs.setAttribute("registered", null);
                }%>
            <form action="registrationconfirm" method="post">
                ユーザー名<br>
                <input type="text" name="name" size="20" value="<% if (reinput) {
                        out.print(udb.getName());
                    } %>"><br><br>
                パスワード<br>
                <input type="password" name="password" size="20"><br><br>
                メールアドレス<br>
                <input type="email" name="mail" size="30" value="<% if (reinput) {
                        out.print(udb.getMail());
                    } %>"><br><br>
                住所<br>
                <input type="text" name="address" size="50" value="<% if (reinput) {
                        out.print(udb.getAddress());
                    }%>"><br><br>
                <input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
                <input type="submit" name="btn" value="確認画面へ">
            </form><br>
            <% //ログイン,アウト表示分岐
                if (!log) {
            %>
            <%= kh.login()%> 
            <% } else {%>
            <%= kh.logout(name)%>

            <% }%>
            <hr>
            <%= kh.top()%>
            <br>
        </div>
    </body>
</html>
