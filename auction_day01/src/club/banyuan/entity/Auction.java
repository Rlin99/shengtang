package club.banyuan.entity;

import java.util.Date;

public class Auction {
    private Integer id;
    private Integer userId;
    private String uesrName;
    private Integer productId;
    private Date createTime;
    private Double price;

    public Auction() {
        super();
    }

    public String getUesrName() {
        return uesrName;
    }

    public void setUesrName(String uesrName) {
        this.uesrName = uesrName;
    }

    public Auction(Integer id, Integer userId, String uesrName, Integer productId, Date createTime, Double price) {

        this.id = id;
        this.userId = userId;
        this.uesrName = uesrName;
        this.productId = productId;
        this.createTime = createTime;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
