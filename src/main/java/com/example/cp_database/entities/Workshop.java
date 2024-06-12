package entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Цех")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Контактный номер", nullable = false)
    private String contactNumber;

    @Column(name = "Адрес", nullable = false)
    private String address;

    @Column(name = "Начальник цеха", nullable = false)
    private String workshopManager;

    @ManyToOne
    @JoinColumn(name = "ID сотрудника", referencedColumnName = "ID ", nullable = false)
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkshopManager() {
        return workshopManager;
    }

    public void setWorkshopManager(String workshopManager) {
        this.workshopManager = workshopManager;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
