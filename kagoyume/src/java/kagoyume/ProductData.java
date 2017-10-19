package kagoyume;

/**
 * 商品情報用のクラス
 */

public class ProductData {

    private String name;
    private String price;
    private String image;
    private String code;
    private String headline;
    private String description;
    private String condition;
    private String rate;
    private String defaultPrice;
    private String shippingName;
    private String totalResultsAvailable;

    ProductData() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (!(name == null)) {
            this.name = name;
        } else {
            this.name = "";
        }
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        if (!(price == null)) {
            this.price = price;
        } else {
            this.price = "";
        }
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        if (!(image == null)) {
            this.image = image;
        } else {
            this.image = "";
        }
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        if (!(code == null)) {
            this.code = code;
        } else {
            this.code = "";
        }
    }

    public String getHeadline() {
        return this.headline;
    }

    public void setHeadline(String headline) {
        if (!(headline == null)) {
            this.headline = headline;
        } else {
            this.headline = "";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (!(description == null)) {
            this.description = description;
        } else {
            this.description = "";
        }
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        if (!(condition == null)) {
            this.condition = condition;
        } else {
            this.condition = "";
        }
    }

    public String getRate() {
        return this.rate;
    }

    public void setRate(String rate) {
        if (!(rate == null)) {
            this.rate = rate;
        } else {
            this.rate = "";
        }
    }

    public String getDefaultPrice() {
        return this.defaultPrice;
    }

    public void setDefaultPrice(String defaultPrice) {
        if (!(defaultPrice == null)) {
            this.defaultPrice = defaultPrice;
        } else {
            this.defaultPrice = "";
        }
    }

    public String getShippingName() {
        return this.shippingName;
    }

    public void setShippingName(String shippingName) {
        if (!(shippingName == null)) {
            this.shippingName = shippingName;
        } else {
            this.shippingName = "";
        }
    }

    // TotalResultsAvailable() 検索結果数
    public String getTotalResultsAvailable() {
        return this.totalResultsAvailable;
    }

    public void setTotalResultsAvailable(String tra) {
        if (!(tra == null)) {
            this.totalResultsAvailable = tra;
        } else {
            this.totalResultsAvailable = "";
        }

    }
}
