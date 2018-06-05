package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String adress;
    String bankAccount;
    String sex;
    int age;
    String email;

    public Customer() {

    }

    public Customer(String name, String adress, String bankAccount, String sex, int age, String email) {
        this.name = name;
        this.adress = adress;
        this.bankAccount = bankAccount;
        this.sex = sex;
        this.age = age;
        this.email = email;
    }
}
