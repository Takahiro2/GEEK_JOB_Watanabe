<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.kagoyumeHelper"%>

<%

    kagoyumeHelper kh = kagoyumeHelper.getInstance(); // ヘルパークラスの呼び出し
    HttpSession hs = request.getSession(); // セッションをhsに
    UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD"); // 会員情報を取得
    String name = udd.getName(); // 名前情報を取得
    String password = udd.getPassword(); // パスワード情報を取得
    String mail = udd.getMail(); // メールアドレス情報を取得
    String address = udd.getAddress(); // 住所情報を取得

    // 更新後何らかの不備で返ってきた場合 session "chkList"を取得
    ArrayList<String> chkList = (ArrayList<String>) hs.getAttribute("chkList");

    // ログイン状態処理
    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;
    }

    // ログイン後のページ遷移先を記録
    hs.setAttribute("returnURL", "myupdate");

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
        <script type="text/javascript">
            function check() {
                if (confirm("情報を更新しますか？")) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
        <title>ユーザー情報更新画面 | かご夢</title>
    </head>
    <body>

        <div class="container" align="center">
            <hr>

            <h2>ユーザー情報更新フォーム</h2>
            <hr>
            <h3>12文字以内での変更をお願いします。</h3>
            <%      if (chkList != null) {%>
                    <%= kh.chk(chkList)%><%
                    } else if (hs.getAttribute("registered") != null) { %>
            <div class="alert-danger">
                <h4>このユーザ名はすでに登録されています。</h4>
            </div>
            <% hs.setAttribute("registered", null);
                }%>

            <form action="myupdateresult" method="post" onsubmit="return check()">
                ユーザー名<br>
                <input type="text" name="name" size="20" value="<%= name%>"><br><br>
                パスワード<br>
                <input type="text" name="password" size="20" value="<%= password%>"><br><br>
                メールアドレス<br>
                <input type="email" name="mail" size="30" value="<%= mail%>"><br><br>
                住所<br>
                <input type="text" name="address" size="50" value="<%= address%>"><br><br>
                <input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
                <input type="submit" name="btn" value="更新する">
            </form><br>
            <hr>
            <% // ログイン,アウト表示分岐
                if (!log) {
            %>
            <%= kh.login()%> 
            <% } else {%>
            <%= kh.logout(name)%>
            <% }%>
            <br>
            <%= kh.top()%>
        </div>
    </body>
</html>
