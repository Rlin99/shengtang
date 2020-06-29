package club.banyuan;

import java.util.Date;

public class Porder {
    private Integer id;
    private Integer userId;
    private String loginName;
    private Date createTime;
    private Integer cost;

    public Porder() {
        this.id = null;
        this.userId = null;
        this.loginName = null;
        this.createTime = null;
        this.cost = null;
    }

    @Override
    public String toString() {
        return "Porder{" +
                "id=" + id +
                ", userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", createTime=" + createTime +
                ", cost=" + cost +
                '}';
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
