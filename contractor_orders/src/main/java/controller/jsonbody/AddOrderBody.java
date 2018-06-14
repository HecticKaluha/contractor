package controller.jsonbody;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddOrderBody implements Serializable {

    private int orderid;
    private Integer[] products;

    public AddOrderBody() {

    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Integer[] getProducts() {
        return products;
    }

    public void setProducts(Integer[] products) {
        this.products = products;
    }
}
