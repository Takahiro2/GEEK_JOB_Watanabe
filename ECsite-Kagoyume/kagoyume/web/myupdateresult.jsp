<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.UserData"%>
<%@page import="kagoyume.kagoyumeHelper"%>

<%
    kagoyumeHelper kh = kagoyumeHelper.getInstance(); // ヘルパークラスの呼び出し
    HttpSession hs = request.getSession(); // セッションをhsに
    // 各種変数の宣言
    String name = "";
    String password = "";
    String mail = "";
    String address = "";
    
   // ログイン状態処理
    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;
        // 名前表示を含めたユーザー情報表示のためにセッションから取り出す
        UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
        name = udd.getName();
        password = udd.getPassword();
        mail = udd.getMail();
        address = udd.getAddress();
    }
    
    // ログイン後のページ遷移先を記録
    hs.setAttribute("returnURL", "myupdateresult");
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
            <h3>下記の内容で更新しました。</h3>
            ユーザー名:<br>
            <%= name%><br><br>
            パスワード:<br>
            <%= password%><br><br>
            メールアドレス:<br>
            <%= mail%><br><br>
            住所:<br>
            <%= address%><br><br>

            <%= kh.mydata()%><br><br>
            <%= kh.top()%>
        </div>
    </body>
</html>
