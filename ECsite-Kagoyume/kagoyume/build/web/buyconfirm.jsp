<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.ProductData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.kagoyumeHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    kagoyumeHelper jh = kagoyumeHelper.getInstance();
    HttpSession hs = request.getSession();
    // UserIDをsessionにて利用するので取り出す
    UserDataDTO udd = (UserDataDTO) session.getAttribute("welcomeUDD");
    ArrayList<ProductData> pd = (ArrayList<ProductData>) hs.getAttribute(String.valueOf(udd.getUserID()));
    String name = "";
    int cartTotal = 0;
    // ログイン状態処理
    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;
        // 名前表示のためにセッションから取り出す
        name = udd.getName();
    }

    // ログイン後のページ移行のために情報をセッションに格納
    hs.setAttribute("returnURL", "buyconfirm");
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
            <header>
                <hr>
                <% //ログイン,アウト表示分岐
                    if (!log) {
                %>
                <%= jh.login()%> 
                <% } else {%>
                <%= jh.logout(name)%>
                <% } %>
            </header>
            <hr>
            <main>
                <table border="1" bordercolor="#333" cellpadding="10" cellspacing="5">
                    <tr>
                        <th>商品名</th>
                        <th>価格</th>
                    </tr>
                    <% for (int i = 0; i < pd.size(); i++) {%>
                    <tr>
                        <td><%= pd.get(i).getName()%></td>
                        <td><%= pd.get(i).getPrice()%></td>
                    </tr>
                    <% cartTotal += Integer.parseInt(pd.get(i).getPrice()); %>
                    <%}%>
                </table>
                合計金額:<%= cartTotal%>円
                <form action="buycomplete" method="post">
                    発送方法:<br>
                    ゆうパック:<input type="radio" name="radiotype" value="1" checked="checked">
                    宅急便:<input type="radio" name="radiotype" value="2">
                    <input type="hidden" name="total" value="<%= cartTotal%>">
                    <br>
                    <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
                    <input type="submit" name="btn" value="購入する">
                </form>
                <br><br>
                <form action="cart" method="post">
                    <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
                    <input type="submit" name="btn" value="買い物カゴに戻る">
                </form>

                <%= jh.top()%><br>
            </main>
        </div>

    </body>
</html>
