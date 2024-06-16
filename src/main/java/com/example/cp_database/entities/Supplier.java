package com.example.cp_database.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Поставщик")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Название", nullable = false)
    private String name;

    @Column(name = "Адрес", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "ID склада", referencedColumnName = "ID", nullable = false)
    private Warehouse warehouse;

    @Column(name = "Котактный номер")
    private String contactNumber;

    public Supplier() {
    }

    public Supplier(String name, String contactNumber, Warehouse warehouse) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.warehouse = warehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
