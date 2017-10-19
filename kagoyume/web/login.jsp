
<%@page import="kagoyume.kagoyumeHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    kagoyumeHelper kh = kagoyumeHelper.getInstance(); // ヘルパークラスの呼び出し
    HttpSession hs = request.getSession(); // セッション開始
    
    // 未ログインで飛んできた場合の処理
    String al = "";
    boolean alert = false;
    // session"noadd"に値がある場合はalertを有効に
    if (session.getAttribute("noadd") != null) {
        alert = true;
        // "noadd"に含まれている警告文を al に収納
        al = (String) session.getAttribute("noadd");
        // その後"noadd"は null に戻す
        session.setAttribute("noadd", null);
    }

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
            <h1>ログインページ</h1>
            <%               
                if (alert) {
                    out.print(al);
                }
            %>
            <form action="loginCheck" method="post">
                名前<br>
                <input type="text" name="name"><br><br>
                パスワード<br>
                <input type="password" name="pass"><br><br>
                <input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
                <input type="submit" name="submit" value="ログイン">
            </form>
            <form action="registration" method="post">
                <input type="submit" name="btn" value="新規登録画面">
            </form><br>
            <%= kh.top()%>
        </div>
    </body>
</html>
