<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%
    kagoyumeHelper kh = kagoyumeHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
    String name = udd.getName();
    String mail = udd.getMail();
    String address = udd.getAddress();

    // ログイン後のページ遷移先を記録
    hs.setAttribute("returnURL", "mydelete");
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
                if (confirm("本当に削除しますか？"))
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        </script>
        <title>会員情報削除確認</title>
    </head>
    <body>
        <div class="container" align="center">

            <header>
                <%= kh.logout(name)%>
            </header>

            <h3>このユーザー情報を削除しますか?</h3>
            ユーザー名:<br>
            <%= name%><br><br>
            メールアドレス:<br>
            <%= mail%><br><br>
            住所:<br>
            <%= address%><br><br>
            <form action="mydeleteresult" method="post" onsubmit="return check()">
                <input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
                <input type="submit" name="yesbtn" value="削除する">
            </form>
            <br>
            <form action="mydata" method="post">
                <input type="submit" name="nobtn" value="前ページに戻る">
            </form>

            <br><br>
            <%= kh.top()%>
        </div>
    </body>
</html>
