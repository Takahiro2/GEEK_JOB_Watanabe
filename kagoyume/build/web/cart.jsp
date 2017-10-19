<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.ProductData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.kagoyumeHelper"%>

<%
    kagoyumeHelper kh = kagoyumeHelper.getInstance(); // ヘルパークラスの呼び出し
    HttpSession hs = request.getSession(); // セッションスタート
    int totalPrice = 0; // 買い物カゴに入っている商品の合計金額

    // UserIDを利用する為情報を取り出す
    UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
    
    // カートを入れるから来た時用の処理 選択した商品を買い物カゴに収納する
    if (session.getAttribute("cartList") != null) {
        session.setAttribute(String.valueOf(udd.getUserID()), session.getAttribute("cartList"));
        session.removeAttribute("cartList");
    }
    
    // 買い物カゴに入れている商品情報をArrayList pdl へ
    ArrayList<ProductData> pdl = (ArrayList) session.getAttribute(String.valueOf(udd.getUserID()));

    // ログイン有無の判断
    String name = "";
    boolean log = false;
    // ログインしている場合
    if (hs.getAttribute("login") != null) {
        log = true;
        // 名前表示のためにセッションから取り出す
        name = udd.getName();
    }

    // ログイン後のページ遷移先を記録
    hs.setAttribute("returnURL", "cart");
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
        <title>カートページ</title>
    </head>
    <body style="padding: 5px">
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
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12 text-center"><h1><strong>Cart</strong></h1></div>
            </div><br><br>
            <% if (pdl != null && pdl.size() != 0) {%>
            <% for (ProductData value : pdl) {%>
            <div class="row">
                <div class="col-lg-2">
                    <table class="table">
                        <tr>
                            <th>Image</th>
                        </tr>
                        <tr>
                            <td><img src="<%= value.getImage()%>"></td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-7">
                    <ul class="list-unstyled">
                        <li>Name:<td><a href="item?code=<%= value.getCode()%>"><%= value.getName()%></a></li><br><br>
                        <li>Price:<%= value.getPrice()%></li><br><br>
                        <li>
                            <form action="cart" method="post">
                                <button class="btn btn-default" type="submit">削除</button>
                                <input type="hidden" name="delete" value="<%=value.getCode()%>">
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
            <%
                totalPrice += Integer.parseInt(value.getPrice()); // 各商品の金額を合計していく
                session.setAttribute("totalPrice", totalPrice); // セッションに記録
            %><br>
            <% }%>
            <% } else { %>
            <div class="row">
                <div class="col-lg-offset-2 col-lg-10 text-left">
                    <strong>Empty</strong>
                </div>
            </div>
            <% }%><br>
            <div class="row">
                <div class="col-lg-offset-2 col-lg-10 text-left">
                    <ul class="list-unstyled">
                        <li>TotalPrice:<%=totalPrice%></li><br>
                        <li>
                            <% if (totalPrice != 0) { %>
                            <form action="buyconfirm" >
                                <button type="submit" class="btn btn-default">購入する</button>
                            </form>
                            <% }%>
                        </li>
                    </ul>
                </div>
            </div><br><br>
            <%=kh.top()%>
        </div>
    </body>
</html>