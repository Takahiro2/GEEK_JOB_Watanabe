package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ページで入出力されるユーザー情報を持ちまわるJavaBenas DTOと違い画面表示系への結びつきが強い
 * DTOへの変換メソッド、入力チェックリストを出力するメソッドも準備されている
 */
public class UserData implements Serializable {

    // 各種変数の宣言
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    private int userID;

    public UserData() {
        this.name = "";
        this.password = "";
        this.mail = "";
        this.address = "";
    }

    // Name() は入力した 氏名 の情報
    public String getName() {
        return name;
    }

    public void setName(String name) {
        // 未入力の場合は空文字をセット
        if (name.trim().length() == 0) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    // Password() は パスワード の情報
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.trim().length() == 0) {
            this.password = "";
        } else {
            this.password = password;
        }
    }

    // Mail() は メールアドレス の情報
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if (mail.trim().length() == 0) {
            this.mail = "";
        } else {
            this.mail = mail;
        }
    }

    // Address() は 住所 の情報
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.trim().length() == 0) {
            this.address = "";
        } else {
            this.address = address;
        }
    }

    // Total() は 総購入金額 の情報
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    // UserID() は ユーザーID  の情報
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // chkProperties() 登録時未入力がないかを調べる
    public ArrayList<String> chkProperties() {
        ArrayList<String> chkList = new ArrayList<String>();
        if (this.name.equals("") || this.name.length() > 12) {
            chkList.add("name");
        }
        if (this.password.equals("") || this.password.length() > 12) {
            chkList.add("password");
        }
        if (this.mail.equals("") || this.mail.length() > 12) {
            chkList.add("mail");
        }
        if (this.address.equals("") || this.address.length() > 12) {
            chkList.add("address");
        }

        return chkList;

    }

    // 入力した情報をDTO用にマッピング
    public void toDTOMapping(UserDataDTO udd) {
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
        udd.setTotal(this.total);
        udd.setUserID(this.userID);
    }
}
