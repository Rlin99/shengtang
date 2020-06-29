package club.banyuan;

public class Porder_Detail {
    private Integer id;
    private Integer porderId;
    private Integer productId;
    private Integer quantity;
    private Integer cost;

    @Override
    public String toString() {
        return "Porder_Detail{" +
                "id=" + id +
                ", porderId=" + porderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPorderId() {
        return porderId;
    }

    public void setPorderId(Integer porderId) {
        this.porderId = porderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
