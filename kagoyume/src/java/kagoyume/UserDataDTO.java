package kagoyume;

import java.sql.Timestamp;

public class UserDataDTO {

    // 変数の宣言
    private int userID;
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    private Timestamp newDate;
    private int deleteFlg;
    private String itemCode;
    private int type;
    private int buyID;
    private Timestamp buyDate;

    // UserID() ユーザー ID情報
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Name() 名前 情報
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Password() パスワード 情報
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Mail() メールアドレス 情報
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    // Address() 住所 情報
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Total() 総購入金額 情報
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    // Date() 登録時の日時 情報
    public Timestamp getNewDate() {
        return newDate;
    }

    public void setNewDate(Timestamp newDate) {
        this.newDate = newDate;
    }

    // getDeleteFlg() 削除されているかを確認する為のフラグ
    public int getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(int deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    // itemCode() 商品のコード 情報
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    // getType() 商品の発送方法 情報
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    // buyID() 商品を購入したユーザーID
    public int getbuyID() {
        return buyID;
    }

    public void setBuyID(int buyID) {
        this.buyID = buyID;
    }

    // buyDate() 購入時間の情報
    public Timestamp getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Timestamp buyDate) {
        this.buyDate = buyDate;
    }
}
