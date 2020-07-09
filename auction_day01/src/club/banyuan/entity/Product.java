package club.banyuan.entity;

import java.util.Date;

public class Product {
    private Integer id;
    private String name;
    private String describtion;
    private Date startTime;
    private Date finishTime;
    private Double startPrice;
    private Double basePrice;
    private String fileName;
    private Integer isSelled;

    public Product() {
        super();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describtion='" + describtion + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", startPrice=" + startPrice +
                ", basePrice=" + basePrice +
                ", fileName='" + fileName + '\'' +
                ", isSelled=" + isSelled +
                '}';
    }

    public Product(Integer id, String name, String describtion, Date startTime, Date finishTime, Double startPrice, Double basePrice, String fileName, Integer isSelled) {
        this.id = id;
        this.name = name;
        this.describtion = describtion;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.startPrice = startPrice;
        this.basePrice = basePrice;
        this.fileName = fileName;
        this.isSelled = isSelled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getIsSelled() {
        return isSelled;
    }

    public void setIsSelled(Integer isSelled) {
        this.isSelled = isSelled;
    }
}
