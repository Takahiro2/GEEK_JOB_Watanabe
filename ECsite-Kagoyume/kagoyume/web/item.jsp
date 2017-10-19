
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.ProductData"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%@page import="com.mysql.jdbc.SocketMetadata.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    kagoyumeHelper kh = kagoyumeHelper.getInstance(); // ヘルパークラスの呼び出し
    HttpSession hs = request.getSession(); // セッションスタート
    ProductData pd = (ProductData) session.getAttribute("productData"); // 商品詳細情報を pd に
    
    // ログイン情報から名前を取り出す
    String name = "";
    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;
        UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
        name = udd.getName();
    }

    // ログイン後のページ情報を記録
    hs.setAttribute("returnURL", "item");

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

        <title>商品情報</title>
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
                <% }%></a>

            </header>
            <hr>
            <main>
                <p><div class="data-subject">商品名:</div><br>
                <%= pd.getName()%></p>
                <br><br>
                <p><div class="data-subject">イメージ:</div></p>
                <img src="<%= pd.getImage()%>">
                <br><br>
                <p><div class="data-subject">概要:</div>
                <%= pd.getDescription()%></p>
                <br><br>
                <p><div class="data-subject">価格:<%= pd.getPrice()%> 円</div></p>
                <br>
                <form action="add" method="post">
                    <input type="submit" name="btn" value="カートに追加">
                </form><br>

                <%= kh.search()%>
                <br><br>
                <%= kh.top()%>
                
            </main>
            <hr>
        </div>
    </body>
</html>
