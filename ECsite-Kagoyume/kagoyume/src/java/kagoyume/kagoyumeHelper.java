package kagoyume;

import java.util.ArrayList;

/**
 * 画面の表示、処理、遷移を司るHelper
 */

public class kagoyumeHelper {

    private final String topURL = "top.jsp"; // topページURL
    private final String searchURL = "search.jsp"; // searchページURL
    private final String itemURL = "item.jsp"; // itemページURL
    private final String mydataURL = "mydata.jsp"; // mydataページURL

    
    // インスタンス生成
    public static kagoyumeHelper getInstance() {
        return new kagoyumeHelper();
    }

    // top() トップページリンク
    public String top() {
        return "<a href=\"" + topURL + "\">トップ画面へ</a>";
    }

    // search() 検索結果ページリンク
    public String search() {
        return "<a href=\"" + searchURL + "\">検索結果画面へ</a>";
    }

    // item() 商品詳細画面ページリンク
    public String item() {
        return "<a href=\"" + itemURL + "\">商品詳細画面へ</a>";
    }

    // mydata() ユーザー情報ページリンク
    public String mydata() {
        return "<a href=\"" + mydataURL + "\">ユーザー情報画面へ</a>";
    }

    // login() 未ログイン時の表示 ログインボタンがある
    public String login() {
        String in = "ようこそ'ゲスト'さん\n"
                + "<form action=\"login\" method=\"post\">\n"
                + "  <input type=\"submit\" name=\"btn\" value=\"ログイン\">\n"
                + "</form>";
        return in;
    }

    // logout() ログイン時の表示 買い物カゴ・ログアウトボタンがある　
    public String logout(String name) {
        String out = "ようこそ<a href=\"mydata\">" + name
                + "</a>さん\n"
                + "<form action=\"cart\" method=\"post\">\n"
                + "<input type=\"submit\" name=\"btn\" value=\"買い物かご\">\n"
                + "</form>"
                + "<form action=\"login\" method=\"post\">\n"
                + "<input type=\"submit\" name=\"btn\" value=\"ログアウト\">\n"
                + "</form>";
        return out;
    }

    /**
     * chk(chkList) フォーム未入力の項目を表示する
     */
    public String chk(ArrayList<String> chkList) {
        String output = "";

        for (String c : chkList) {
            
            if (c.equals("name")) {
                output += "ユーザー名";
            }
            if (c.equals("password")) {
                output += "パスワード";
            }
            if (c.equals("mail")) {
                output += "メールアドレス";
            }
            if (c.equals("address")) {
                output += "住所";
            }
            output += " の項目に不備があります<br>";
            
        }
        
        return output;
        
    }
}
