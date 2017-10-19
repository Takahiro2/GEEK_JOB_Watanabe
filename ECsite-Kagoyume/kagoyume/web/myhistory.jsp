

<%@page import="kagoyume.ProductData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%
    kagoyumeHelper jh = kagoyumeHelper.getInstance();
    HttpSession hs = request.getSession();
    String name = "";
    // ログイン状態処理
    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;
        // 名前表示のためにセッションから取り出す
        UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
        name = udd.getName();
    }
    ArrayList<ProductData> lastList = (ArrayList<ProductData>) hs.getAttribute("lastList");

    // ログイン後のページ遷移先を記録
    hs.setAttribute("returnURL", "myhistory");

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
                <% //ログイン,アウト表示分岐(ログアウト状態では来れない?)
                    if (!log) {
                %>
                <%= jh.login()%> 
                <% } else {%>
                <%= jh.logout(name)%>
                <% } %>
            </header>
            <hr>
            <main>
                <%if (lastList == null) {%>
                <p>まだ商品を購入していません</p>
                <%} else {%>
                <table border="1" bordercolor="#333" cellpadding="5" cellspacing="3">
                    <tr>
                        <th>イメージ</th>
                        <th>商品名</th>
                        <th>金額</th>
                    </tr>
                    <% for (ProductData pd : lastList) {%>
                    <tr>
                        <td><img src="<%= pd.getImage()%>"></td>
                        <td><%= pd.getName()%></td>
                        <td><%= pd.getPrice()%>円</td>
                    </tr>
                    <%}%>
                </table>
                <%}%><br><br>
                <%= jh.mydata()%><br><br>
                <%= jh.top()%><br>
            </main>
        </div>
    </body>
</html>