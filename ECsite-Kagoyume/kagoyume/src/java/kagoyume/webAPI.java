package kagoyume;

/**
 * YahooショッピングAPIを使って商品データを検索するメソッド集
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import net.arnx.jsonic.JSON;

public class webAPI {

    // 自分のYahoo!デベロッパーAPP_ID
    private static String appID = "dj00aiZpPWQxTko2Q0JwRzBXWiZzPWNvbnN1bWVyc2VjcmV0Jng9NDY-";
    // Yahoo!ションピングAPIのベースURI
    private static String baseURI = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";
    // Yahoo!ショッピングAPI 商品詳細確認時のベースURI
    private static String baseDetailURI = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup";

    // インスタンス生成
    public static webAPI getInstance() {
        return new webAPI();
    }

    /**
     * searchResult() 検索ワードを用いて検索結果を取得する
     */
    public static ArrayList searchResult(String query) throws MalformedURLException, IOException {

        ArrayList<String> searchResult = new ArrayList<String>();

        // 商品データがある時～
        if (query != null) {

            // 入力情報をパーセントエンコーディング
            String urlEnc = URLEncoder.encode(query, "UTF-8");
            // URLの作成
            URL url = new URL(baseURI + "?appid=" + appID + "&query=" + urlEnc);
            // 接続用HttpURLConnectionオブジェクトの作成
            HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
            // リクエストメソッドの設定
            urlconn.setRequestMethod("GET");
            // リダイレクトを自動で許可しない設定
            urlconn.setInstanceFollowRedirects(false);

            // 接続
            urlconn.connect();

            // URL先の情報をテキストファイルで読み込む
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

            // 文字列操作可能なresponseBufferを宣言
            StringBuffer responseBuffer = new StringBuffer();

            // テキストファイルが存在する限り読み込む
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                responseBuffer.append(line); // appendで結合していく
            }

            reader.close(); // リーダーをクローズ
            urlconn.disconnect(); // 接続を切る

            // 読み込んだテキストファイルを文字列に変換
            String response = responseBuffer.toString();

            searchResult = parse(response);

        }
        return searchResult;
    }

    /**
     * searchDetail() 商品コードを用いて商品詳細情報を取得する
     */
    public static ProductData searchDetail(String code) throws IOException {

        URL url = new URL(baseDetailURI + "?appid=" + appID + "&itemcode=" + code + "&responsegroup=medium");

        HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
        urlconn.setRequestMethod("GET");
        urlconn.setInstanceFollowRedirects(false);

        urlconn.connect();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

        ProductData pb = new ProductData();
        // Jsonをロード、Map型に変換すると利用できる
        Map root = (Map) JSON.decode(reader);
        // ResultSet以下を取得
        Map resultSet = (Map) root.get("ResultSet");
        // 0番目以下を取得
        Map zero = (Map) resultSet.get("0");
        // Result以下を取得
        Map result = (Map) zero.get("Result");
        // 商品を取得
        Map item = (Map) result.get("0");
        Map price = (Map) item.get("Price");
        // 商品のImage以下を取得
        Map image = (Map) item.get("Image");
        // Review以下を取得
        Map review = (Map) item.get("Review");
        // Shipping以下を取得
        Map shipping = (Map) item.get("Shipping");

        // 商品名をset
        pb.setName((String) item.get("Name"));
        // 商品名コードをset
        pb.setCode((String) item.get("Code"));
        // 画像をset
        pb.setImage((String) image.get("Medium"));
        // キャッチコピーをset
        pb.setHeadline((String) item.get("Headline"));
        // 概要をset
        pb.setDescription((String) item.get("Description"));
        // 状態をset
        pb.setCondition((String) item.get("Condition"));
        // 評価をset
        pb.setRate((String) review.get("Rate"));
        // 販売価格をset
        pb.setPrice((String) price.get("_value"));
        // shippingNameをset
        pb.setShippingName((String) shipping.get("Name"));

        urlconn.disconnect();
        reader.close();

        return pb;
    }

    /**
     * JSONテキストを変換する、上記関数にて使用する
     */
    private static ArrayList parse(String jsonText) {

        // JSONをjavaにデコード後格納
        Map<String, Map<String, Object>> json = JSON.decode(jsonText);

        ArrayList<ProductData> pl = new ArrayList<ProductData>();

        // total～は該当件数の総個数、それが0でない時～
        if (!Integer.valueOf((String) json.get("ResultSet").get("totalResultsAvailable")).equals(0)) {

            @SuppressWarnings("unchecked") // キャスト失敗の可能性時に出る警告を回避

            // ResultSet 0番目の商品情報Resultを取得（20件?)
            Map result = ((Map<String, Object>) ((Map<String, Map<String, Object>>) json.get("ResultSet").get("0")).get("Result"));

            for (Integer i = 0; i < 20; i++) {

                Map item = (Map) result.get(i.toString()); // 1つの商品情報を取得
                Map price = (Map) item.get("Price"); // 商品のPrice以下の情報を取得
                Map image = (Map) item.get("Image"); // 商品のimage以下の情報を取得

                ProductData pb = new ProductData(); // 1つ商品データを入れる毎に初期化

                // ProductDataクラス pb に情報を収納していく
                pb.setName((String) item.get("Name")); // 商品名を収納
                pb.setPrice((String) price.get("_value")); // 価格情報を収納
                pb.setImage((String) image.get("Medium")); // 画像URL情報を収納
                pb.setCode((String) item.get("Code")); // アイテムコード情報を収納
                // 総検索数を収納　全てのデータに挿入する事になるが・・
                pb.setTotalResultsAvailable((String) json.get("ResultSet").get("totalResultsAvailable"));

                // 戻り値 pl へ情報を収納
                pl.add(pb);

            }
        }

        return pl;

    }

}
