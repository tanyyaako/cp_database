package entities;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Доставка")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID поставщика", nullable = false)
    private Long supplierId;

    @Column(name = "Дата доставки", nullable = false)
    private Date deliveryDate;

    @Column(name = "Адрес доставки")
    private String deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "ID клиента", referencedColumnName = "ID")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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