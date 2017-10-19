
<%@page import="kagoyume.UserData"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    kagoyumeHelper jh = kagoyumeHelper.getInstance(); // ヘルパークラスの呼び出し
    HttpSession hs = request.getSession(); // セッションをhsに
    // ユーザーデータを表示用に取り出す
    UserData ud = (UserData) request.getAttribute("ud");
    
    // ログイン後のページ遷移先を記録
    hs.setAttribute("returnURL", "registrationcomplete");
    
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
            <h3>下記の内容で登録しました</h3><br>
            ユーザー名<br>
            <%= ud.getName()%><br><br>
            パスワード<br>
            <%= ud.getPassword()%><br><br>
            メールアドレス<br>
            <%= ud.getMail()%><br><br>
            住所<br>
            <%= ud.getAddress()%><br>
            <p>トップ画面に戻りログインしてください</p>
            <hr>
            <%= jh.top()%>
        </div>
    </body>
</html>
