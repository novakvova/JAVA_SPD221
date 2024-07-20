package org.example;

import org.example.entities.ClientEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(ClientEntity.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        ClientEntity entity = new ClientEntity();
        entity.setFirstName("Іван");
        entity.setLastName("Барабашка");
        entity.setPhone("+38 068 47 85 458");
        entity.setCar_model("Volkswagen Beetle A5");
        entity.setCar_year(2003);
        session.save(entity);

        transaction.commit();
        session.close();
    }
}