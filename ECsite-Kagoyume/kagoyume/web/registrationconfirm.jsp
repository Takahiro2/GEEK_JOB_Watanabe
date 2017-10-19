
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%@page import="kagoyume.UserData"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession hs = request.getSession(); // セッション hs
    kagoyumeHelper kh = kagoyumeHelper.getInstance(); // ヘルパークラスの呼び出し
    UserData ud = (UserData) hs.getAttribute("ud"); // 入力フォームに入力した情報を取得
    ArrayList<String> chkList = ud.chkProperties(); // 入力に不備がないかを確認
    String name = "";
    boolean log = false;
    // ログインしている場合
    if (hs.getAttribute("login") != null) {
        log = true;
        // 名前表示のためにセッションから取り出す
        UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
        name = udd.getName();
    }

    // ログイン後のページ遷移先を記録
    hs.setAttribute("returnURL", "registrationconfirm");

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
            <%  if (chkList.size() == 0) {%>
            <h2>登録確認</h2>
            ユーザー名<br>
            <%= ud.getName()%><br><br>
            パスワード<br>
            <%= ud.getPassword()%><br><br>
            メールアドレス<br>
            <%= ud.getMail()%><br><br>
            住所<br>
            <%= ud.getAddress()%><br><br>
            上記の内容で登録いたします。よろしいですか？
            <form action="registrationcomplete" method="post">
                <input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
                <input type="submit" name="yes" value="はい">
            </form>
            <% } else {%>
            <h2>入力に不備があります</h2>
            <%= kh.chk(chkList)%>
            <p>もう一度登録画面からやり直してください</p>
            <% }%><br>
            <form action="registration" method="post">
                <input type="hidden" name="mode" value="reinput">
                <input type="submit" name="no" value="登録画面へ戻る">
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
        </div>
    </body>
</html>
