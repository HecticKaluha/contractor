package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Orderclass")
@Table(name = "orderclass")
public class Order implements Serializable {


    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String name;

    @Id
    private int orderId;

    private Date orderdate;

    @ElementCollection
    @Column(name="products")
    private List<Integer> products;

    boolean paid;

    int totalPrice;

    public Order() {
    }

    public Order(int orderid, List<Integer> products, String name){
        this.orderId = orderid;
        this.products = products;
        this.orderdate = new Date();
        this.paid = false;
        this.totalPrice = 0;
        this.name = name;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public void addProduct(int productNo) {
        this.products.add(productNo);
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
