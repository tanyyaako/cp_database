package com.example.cp_database.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Клиент")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Имя", nullable = false)
    private String firstName;

    @Column(name = "Фамилия", nullable = false)
    private String lastName;

    @Column(name = "Адрес электронной почты")
    private String email;

    @Column(name = "Контактный номер")
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "ID сотрудника", referencedColumnName = "ID ")
    private Employee employee;

    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;


    }



    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
