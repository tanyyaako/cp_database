package entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Сотрудник")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID ")
    private Long id;

    @Column(name = "Имя", nullable = false, length = 255)
    private String firstName;

    @Column(name = "Фамилия", nullable = false, length = 255)
    private String lastName;

    @Column(name = "Дата трудоустрйства", nullable = false)
    private Date employmentDate;

    @Column(name = "Должность", nullable = false, length = 255)
    private String position;

    @Column(name = "Зарплата", nullable = false)
    private int salary;

    @Column(name = "Табельный номер", nullable = false)
    private int employeeNumber;

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

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
