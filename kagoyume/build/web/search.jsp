
<%@page import="kagoyume.ProductData"%>
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%@page import="net.arnx.jsonic.JSON"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    kagoyumeHelper kh = kagoyumeHelper.getInstance(); // ヘルパークラスの呼び出し
    HttpSession hs = request.getSession(); // セッショスタート

    //ログイン状態処理
    String name = "";
    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;
        //名前表示のためにセッションから取り出す
        UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
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
        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>

        <title>検索結果</title>
    </head>

    <body>
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

        <% // 商品情報を変数productへ
            ArrayList<ProductData> product = (ArrayList<ProductData>) session.getAttribute("product");
            
            // もし検索結果が０件だった時用の処理
            if (product.size() == 0) { %>
            <h1>ごめんなさい！　検索したけど見つかりませんでした！</h1>
            <% } else {%>
        <h3>検索キーワード :<%= hs.getAttribute("search")%>  検索結果数 :<%= product.get(0).getTotalResultsAvailable()%>件 </h3>

        <hr>


        <% for (ProductData value : product) {%>

    <tr>

    <tb> <a href="item?code=<%= value.getCode()%>"><img src="<%= value.getImage()%>">  </a> <strong>&yen;  <%= value.getPrice()%></strong></tb>
    <tb><h1><a href="item?code=<%= value.getCode()%>"><%= value.getName()%></a></h1></tb>
</tr>

<% }%>

<hr>
<% }%>

<%= kh.top()%>

</div>

</body>
</html>
