<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- BootstrapのCSS読み込み -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery読み込み -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- BootstrapのJS読み込み -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        
        <h1>削除確認</h1>
        以下の内容を削除します。よろしいですか？
        名前:<%= session.getAttribute("name")%><br>
        生年月日:<%= session.getAttribute("birthday")%><br>
        種別:<%= session.getAttribute("type")%><br>
        電話番号:<%= session.getAttribute("tell")%><br>
        自己紹介:<%= session.getAttribute("comment")%><br>
        登録日時:<%= session.getAttribute("newdate")%><br>

        <form action="DeleteResult" method="POST">
            <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
            <input type="submit" name="YES" value="はい" style="width:100px">
        </form><br>
        <form action="ResultDetail" method="POST">
            <input type="hidden" name="id" value="<%= session.getAttribute("ID")%>">
            <input type="submit" name="NO" value="いいえ" style="width:100px">
        </form>
        <%=jh.home()%>    
    </body>
</html>
