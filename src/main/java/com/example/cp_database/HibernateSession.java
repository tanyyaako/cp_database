package com.example.cp_database;


import com.example.cp_database.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    public static SessionFactory sessionFactory() {
        return new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/CourseProject")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "admin")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
//                .setProperty("hibernate.hbm2ddl.auto", "create")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Delivery.class)
                .addAnnotatedClass(Supplier.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Warehouse.class)
                .addAnnotatedClass(Workshop.class)
                .buildSessionFactory();
    }
}
