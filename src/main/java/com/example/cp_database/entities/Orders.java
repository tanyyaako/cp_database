package com.example.cp_database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Заказы")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID заказа")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID клиента")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "ID сотрудника")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "ID доставки")
    private Delivery delivery;

    public Orders(Client client, Delivery delivery, Employee employee, Long id) {
        this.client = client;
        this.delivery = delivery;
        this.employee = employee;
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
