package com.example.cp_database.entities;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Доставка")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID поставщика", nullable = false, referencedColumnName = "ID")
    private Supplier supplier;

    @Column(name = "Дата доставки", nullable = false)
    private Date deliveryDate;

    @Column(name = "Адрес доставки")
    private String deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "ID клиента", referencedColumnName = "ID")
    private Client client;

    public Delivery(Long id, Supplier supplier, Date deliveryDate) {
        this.id = id;
        this.supplier = supplier;
        this.deliveryDate = deliveryDate;

    }

//    public Delivery(Long id, Date deliveryDate) {
//        this.id = id;
//        this.deliveryDate = deliveryDate;
//    }

//    public Delivery() {
//    }

    public Long getSupplierID(){
        return supplier.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}