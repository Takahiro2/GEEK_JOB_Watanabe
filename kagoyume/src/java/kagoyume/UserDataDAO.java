package kagoyume;

import base.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * DB操作の操作処理を行うクラス 接続系はDBManagerで
 */
public class UserDataDAO {

    public static UserDataDAO getInstance() {
        return new UserDataDAO();
    }

    // insert() DB操作 insertを行う
    public void insert(UserDataDTO ud) throws SQLException {

        Connection con = null; // DB接続オブジェクト
        PreparedStatement st = null; // SQL文オブジェクト

        try {

            // DB接続後順次INSERT操作を行う
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO user_t(name, password, mail, address, newDate) VALUES(?, ?, ?, ?, ?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            // SQL格納用にタイムスタンプをミリ秒に変換
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate(); // 変更を実行後、更新されたテーブルの行数を返す
            System.out.println("insert completed"); // inesrtが完了したかLOGで確認できる

        } catch (SQLException e) {

            System.out.println(e.getMessage()); // 何らかのエラーが出た場合エラーメッセージをLOGに表示
            throw new SQLException(e);

        } finally {

            // DB接続をクローズ
            if (con != null) {
                con.close();

            }
        }
    }

    // search() DB操作 selectを行う
    public UserDataDTO search(UserDataDTO udd) throws SQLException {
        
        Connection con = null; // DB接続オブジェクト
        PreparedStatement st; // SQL文オブジェクト
        ResultSet rs; // 取得した情報を格納する

        try {
            
            // DTOの情報を使ってSELECTを行う
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM user_t WHERE name = ? AND password = ? AND deleteFlg = ?");
            st.setString(1, udd.getName());
            st.setString(2, udd.getPassword());
            st.setInt(3, 0);

            rs = st.executeQuery(); 
            System.out.println("search completed");

            // 情報があればUserDataDTOに情報を入れて返す
            UserDataDTO UDD = new UserDataDTO();
            if (rs.next()) {
                UDD.setUserID(rs.getInt("userID"));
                UDD.setName(rs.getString("name"));
                UDD.setPassword(rs.getString("password"));
                UDD.setMail(rs.getString("mail"));
                UDD.setAddress(rs.getString("address"));
                UDD.setTotal(rs.getInt("total"));
                UDD.setNewDate(rs.getTimestamp("newDate"));
            } else {
                UDD = null;
            }
            return UDD;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    // update() DB操作 updateを行う
    public void update(UserDataDTO udd) throws SQLException {
        
        Connection con = null;
        PreparedStatement st;

        try {
            
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET name = ?, password = ?, mail = ?, address = ?, newDate = ? WHERE userID = ?");
            st.setString(1, udd.getName());
            st.setString(2, udd.getPassword());
            st.setString(3, udd.getMail());
            st.setString(4, udd.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.setInt(6, udd.getUserID());
            st.executeUpdate();
            System.out.println("update completed");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    // delete() DB操作 updateにてdaleteFlgを1にする
    public void delete(int userid) throws SQLException {
        
        Connection con = null;
        PreparedStatement st;

        try {
            
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET deleteFlg = ? WHERE userID = ?");
            st.setInt(1, 1);
            st.setInt(2, userid);
            st.executeUpdate();
            System.out.println("delete completed");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    // totalUp() 合計購入金額を更新
    public void totalUp(UserDataDTO udd) throws SQLException {
        
        Connection con = null;
        PreparedStatement st;

        try {
            
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET total = ? WHERE userID = ?");
            st.setInt(1, udd.getTotal());
            st.setInt(2, udd.getUserID());
            st.executeUpdate();
            System.out.println("update total completed");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    // buyInsert() 購入した商品情報をDB操作にて格納
    public void buyInsert(UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement st;
        
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO buy_t(userID, itemCode, type, buyDate) VALUES(?, ?, ?, ?)");
            st.setInt(1, udd.getUserID());
            st.setString(2, udd.getItemCode());
            st.setInt(3, udd.getType());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }
        finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    // searchHistory() 購入履歴をDB操作で呼び出す
    public ArrayList<UserDataDTO> searchHistory(int id) throws SQLException{
        Connection con = null;
        PreparedStatement st;
        ResultSet rs;
        
        ArrayList<UserDataDTO> buyList = new ArrayList<>();
        
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM buy_t WHERE userID = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            System.out.println("search buy completed");
            
            while(rs.next()){
                UserDataDTO bdd = new UserDataDTO();
                bdd.setBuyID(rs.getInt(1));
                bdd.setUserID(rs.getInt(2));
                bdd.setItemCode(rs.getString(3));
                bdd.setType(rs.getInt(4));
                bdd.setBuyDate(rs.getTimestamp(5));
                buyList.add(bdd);
            }
            return buyList;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }
        finally{
            if(con != null){
                con.close();
            }
        }
}

    // check() DB操作 selectを行い、登録するユーザ名がすでに登録されていないかを確かめる
    static public UserDataDTO check(String name) throws SQLException {
        
        Connection con = null; // DB接続オブジェクト
        PreparedStatement st; // SQL文オブジェクト
        ResultSet rs; // 取得した情報を格納する

        try {
            
            // DTOの情報を使ってSELECTを行う
            con = DBManager.getConnection();
            st = con.prepareStatement("SELECT * FROM user_t WHERE name = ? AND deleteFlg = ?");
            st.setString(1, name);
            st.setInt(2, 0);

            rs = st.executeQuery(); 
            System.out.println("search completed");

            // 情報があればUserDataDTOに情報を入れて返す
            UserDataDTO UDD = new UserDataDTO();
            if (rs.next()) {
                UDD.getName();
            } else {
                UDD = null;
            }
            return UDD;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}


