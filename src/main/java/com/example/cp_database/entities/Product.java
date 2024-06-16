package com.example.cp_database.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Продукт")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Наименование", nullable = false, length = 255)
    private String name;

    @Column(name = "Автор", nullable = false)
    private String author;

    @Column(name = "Наличие на складе")
    private String stockAvailability;

    @Column(name = "Тип продукции", nullable = false, length = 255)
    private String productType;

    @Column(name = "Цена за шт.", nullable = false)
    private int pricePerUnit;

    @Column(name = "Артикул", nullable = false)
    private int articleNumber;

    @ManyToOne
    @JoinColumn(name = "ID цеха", referencedColumnName = "ID", nullable = false)
    private Workshop workshop;

    @ManyToOne
    @JoinColumn(name = "ID склада", referencedColumnName = "ID", nullable = false)
    private Warehouse warehouse;

    public Product(String name, String productType, Warehouse warehouse) {
        this.name = name;
        this.productType = productType;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStockAvailability() {
        return stockAvailability;
    }

    public void setStockAvailability(String stockAvailability) {
        this.stockAvailability = stockAvailability;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
