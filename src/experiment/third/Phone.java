package experiment.third;
/*
商品属性：商品id，品牌，价格，型号，库存
 */
public class Phone {
    private int id;
    private String brand;
    private double price;
    private String model;
    private int stock;

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public int getStock() {
        return stock;
    }
}