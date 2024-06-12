package entities;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Склад")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Адрес")
    private String address;

    @Column(name = "Вместимость")
    private Integer capacity;

    @Column(name = "Дата последней инвертаризации")
    private Date lastInventoryDate;

    @Column(name = "Ответсвенный сотрудник")
    private String responsibleEmployee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Date getLastInventoryDate() {
        return lastInventoryDate;
    }

    public void setLastInventoryDate(Date lastInventoryDate) {
        this.lastInventoryDate = lastInventoryDate;
    }

    public String getResponsibleEmployee() {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(String responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
    }
}
